package mfrf.dbydd.micro_machinery.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import mfrf.dbydd.micro_machinery.command.ReadMultiBlockCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.command.arguments.SuggestionProviders;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class CommandEventHandler {

    @SubscribeEvent
    public static void onServerStaring(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        LiteralCommandNode<CommandSource> cmd = dispatcher.register(
                Commands.literal("micro_machinery").then(
                        Commands.literal("readMultiBlock")
                                .requires((commandSource) -> {
                                    return commandSource.hasPermissionLevel(0);
                                })
                                .executes(ReadMultiBlockCommand.Instance)
                )
        );
        dispatcher.register(Commands.literal("microm").redirect(cmd));
    }
}
