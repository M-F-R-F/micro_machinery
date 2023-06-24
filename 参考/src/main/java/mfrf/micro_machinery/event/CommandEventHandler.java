package mfrf.micro_machinery.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import mfrf.micro_machinery.command.ReadMultiBlockCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class CommandEventHandler {

    @SubscribeEvent
    public static void onServerStaring(ServerStartingEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getServer().getCommands().getDispatcher();
        LiteralCommandNode<CommandSourceStack> cmd = dispatcher.register(
                Commands.literal("micro_machinery").then(
                        Commands.literal("readMultiBlock")
                                .requires((commandSource) -> {
                                    return commandSource.hasPermission(0);
                                })
                                .executes(ReadMultiBlockCommand.Instance)
                )
        );
        dispatcher.register(Commands.literal("microm").redirect(cmd));
    }
}
