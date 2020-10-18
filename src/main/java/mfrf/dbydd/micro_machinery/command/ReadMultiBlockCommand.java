package mfrf.dbydd.micro_machinery.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mfrf.dbydd.micro_machinery.items.DebugTool;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Hand;

public class ReadMultiBlockCommand implements Command<CommandSource> {
    public static ReadMultiBlockCommand Instance = new ReadMultiBlockCommand();

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayerEntity = context.getSource().asPlayer();
        ItemStack heldItem = serverPlayerEntity.getHeldItem(Hand.MAIN_HAND);
        if (!heldItem.isEmpty() && heldItem.getItem() instanceof DebugTool) {
            CompoundNBT clickedPos = heldItem.getChildTag("clickedPos");
            if (clickedPos != null) {
                if (clickedPos.contains("pos1") && clickedPos.contains("pos2") && clickedPos.contains("active_block")) {
                    DebugTool.readMultiBlock(NBTUtil.readBlockPos(clickedPos.getCompound("pos1")), NBTUtil.readBlockPos(clickedPos.getCompound("pos2")), NBTUtil.readBlockPos(clickedPos.getCompound("active_block")), context.getSource().getWorld());
                }
            }
        }
        return 0;
    }
}
