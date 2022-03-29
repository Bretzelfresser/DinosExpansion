package com.renatiux.dinosexpansion.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tribes.Tribe;
import com.renatiux.dinosexpansion.util.TribeUtils;
import com.renatiux.dinosexpansion.world.TribeSaveData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class TribeCommand {

    public TribeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("tribe").executes(TribeCommand::helpTribe)
                .then(Commands.literal("create").then(Commands.argument("tribename", StringArgumentType.string()).executes(TribeCommand::createTribe)))
                .then(Commands.literal("delete").then(Commands.argument("tribename", StringArgumentType.string()).requires(TribeCommand::canExecuteRemove).executes(TribeCommand::removeTribe)))
                .then(Commands.literal("add").then(Commands.argument("player", EntityArgument.player()).then(Commands.argument("tribename", StringArgumentType.string()).requires(TribeCommand::canAdd).executes(TribeCommand::addMember))))
                .then(Commands.literal("remove").then(Commands.argument("player", EntityArgument.player()).then(Commands.argument("tribename", StringArgumentType.string()).requires(TribeCommand::canRemove).executes(TribeCommand::removeMember))))
                .then(Commands.literal("list").executes(TribeCommand::list)));
    }

    protected static int helpTribe(CommandContext<CommandSource> source){
        CommandSource source1 = source.getSource();
        source1.sendFeedback(new StringTextComponent("create - ").appendSibling(new TranslationTextComponent(withPrefix("create"))), true);
        source1.sendFeedback(new StringTextComponent("add - ").appendSibling(new TranslationTextComponent(withPrefix("add"))), true);
        source1.sendFeedback(new StringTextComponent("remove - ").appendSibling(new TranslationTextComponent(withPrefix("remove"))), true);
        source1.sendFeedback(new StringTextComponent("delete - ").appendSibling(new TranslationTextComponent(withPrefix("delete"))), true);
        source1.sendFeedback(new StringTextComponent("list - ").appendSibling(new TranslationTextComponent(withPrefix("list"))), true);
        return 1;
    }

    protected static int createTribe(CommandContext<CommandSource> source) throws CommandSyntaxException {
        String tribeName = source.getArgument("tribename", String.class);
        ServerWorld world = source.getSource().asPlayer().getServerWorld();
        if (TribeUtils.tribeExists(tribeName, world)){
            source.getSource().sendFeedback(new TranslationTextComponent(withPrefix("exists")), true);
            return -1;
        }
        String result = TribeUtils.createTribe(tribeName, source.getSource().asPlayer(), world);
        source.getSource().sendFeedback(new TranslationTextComponent(result, tribeName), true);
        return 1;
    }

    protected static int removeTribe(CommandContext<CommandSource> context){
        String tribeName = context.getArgument("tribename", String.class);
        if (TribeUtils.removeTribe(context.getSource().getWorld(), tribeName)) {
            context.getSource().sendFeedback(new TranslationTextComponent(withPrefix("removed"), tribeName), true);
            return 1;
        }
        context.getSource().sendErrorMessage(new TranslationTextComponent(withPrefix("cant_remove")));
        return -1;
    }

    protected static int addMember(CommandContext<CommandSource> context) throws CommandSyntaxException {
        String tribeName = context.getArgument("tribename", String.class);
        PlayerEntity player = EntityArgument.getPlayer(context, "player");
        Tribe t = TribeUtils.getTribe(tribeName, context.getSource().getWorld());
        System.out.println(t);
        if (context.getSource().hasPermissionLevel(2) && t.forceAddPlayer(player)){
            context.getSource().sendFeedback(new TranslationTextComponent(withPrefix("added_member"), player.getDisplayName(), tribeName), true);
            return 1;
        }
        if (t.addPlayer(player, context.getSource().asPlayer())) {
            context.getSource().sendFeedback(new TranslationTextComponent(withPrefix("added_member"), player.getDisplayName(), tribeName), true);
            return 1;
        }
        context.getSource().sendErrorMessage(new TranslationTextComponent(withPrefix("cant_add")));
        return -1;
    }

    protected static int removeMember(CommandContext<CommandSource> context) throws CommandSyntaxException {
        String tribeName = context.getArgument("tribename", String.class);
        ServerPlayerEntity player = EntityArgument.getPlayer(context, "player");
        Tribe t = TribeUtils.getTribe(tribeName, context.getSource().getWorld());
        if (t != null && t.removePlayer(context.getSource().asPlayer(),player)){
            context.getSource().sendFeedback(new TranslationTextComponent(withPrefix("removed_member"), tribeName, player.getDisplayName()), true);
            return 1;
        }
        context.getSource().sendErrorMessage(new TranslationTextComponent(withPrefix("cant_remove")));
        return -1;
    }

    protected static int list(CommandContext<CommandSource> context){
        ServerWorld world = context.getSource().getWorld();
        TribeSaveData data = TribeSaveData.getData(world);
        context.getSource().sendFeedback(new TranslationTextComponent(withPrefix("tribe_list_header")), true);
        for (Tribe t : data.getTribes()){
            context.getSource().sendFeedback(new StringTextComponent(t.getName()), true);
        }
        return 1;
    }

    protected static boolean canExecuteRemove(CommandSource source){
        try {
            Tribe t = TribeUtils.getTribe(source.asPlayer(), source.getWorld());
            return source.hasPermissionLevel(2) || (t != null && t.hasMember(source.asPlayer()));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean canAdd(CommandSource source){
        if (source.hasPermissionLevel(2)) return true;
        try {
            Tribe t = TribeUtils.getTribe(source.asPlayer(), source.getWorld());
            if (t != null && t.canAddOtherPlayers(source.asPlayer())){
                return true;
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    protected static boolean canRemove(CommandSource source){
        if (source.hasPermissionLevel(2)) return true;
        try {
            Tribe t = TribeUtils.getTribe(source.asPlayer(), source.getWorld());
            if (t != null){
                return true;
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static String withPrefix(String string){
        return Dinosexpansion.MODID + ".tribe." + string;
    }
}
