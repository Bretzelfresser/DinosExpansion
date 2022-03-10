package com.renatiux.dinosexpansion.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tribes.TribeUtils;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.server.command.EnumArgument;

public class TribeCommand {

    public TribeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("tribe").executes(TribeCommand::helpTribe).then(Commands.argument("action", EnumArgument.enumArgument(FirstArgument.class))));
    }

    private static int helpTribe(CommandContext<CommandSource> source){
        CommandSource source1 = source.getSource();
        source1.sendFeedback(new StringTextComponent("create - ").appendSibling(new TranslationTextComponent(withPrefix("create"))), true);
        source1.sendFeedback(new StringTextComponent("add - ").appendSibling(new TranslationTextComponent(withPrefix("add"))), true);
        source1.sendFeedback(new StringTextComponent("remove - ").appendSibling(new TranslationTextComponent(withPrefix("remove"))), true);
        source1.sendFeedback(new StringTextComponent("delete - ").appendSibling(new TranslationTextComponent(withPrefix("delete"))), true);
        return 1;
    }

    public static int firstArgument(CommandContext<CommandSource> source){
        FirstArgument arguemnt = source.getArgument("action", FirstArgument.class);
        return 1;
    }

    private static String withPrefix(String string){
        return Dinosexpansion.MODID + ".tribe." + string;
    }

    private enum FirstArgument{
        CREATE,
        ADD,
        REMOVE,
        DELETE;
    }
}
