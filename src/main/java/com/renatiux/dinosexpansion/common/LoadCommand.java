package com.renatiux.dinosexpansion.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.renatiux.dinosexpansion.util.interfaces.SaveDataProvider;
import com.renatiux.dinosexpansion.world.ExampleData;
import com.renatiux.dinosexpansion.world.SaveData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class LoadCommand {

    public LoadCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("load").executes(LoadCommand::load));
    }

    public static int load(CommandContext<CommandSource> source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getSource().asPlayer();
        ServerWorld world = player.getServerWorld();
        SaveData data = SaveData.getData(world);
        for(SaveDataProvider<?> provider : data.getData()){
            if (provider instanceof ExampleData){
                source.getSource().sendFeedback(new StringTextComponent((((ExampleData) provider).getName())), false);
            }
        }
        return 1;
    }

    static int save(CommandContext<CommandSource> source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getSource().asPlayer();
        ServerWorld world = player.getServerWorld();
        SaveData.putData(new ExampleData(source.getArgument("name", String.class)), world);
        source.getSource().sendFeedback(new StringTextComponent("saved: " + source.getArgument("name", String.class) + " to world save data"), false);
        return 1;
    }

    public static class SaveCommand{
        public SaveCommand(CommandDispatcher<CommandSource> dispatcher){
            dispatcher.register(Commands.literal("save").then(Commands.argument("name", StringArgumentType.string()).executes(LoadCommand::save)));
        }
    }
}
