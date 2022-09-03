package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    private static void readMultiBlock(ItemUseContext context) {
        CompoundNBT clickedPos = context.getItem().getChildTag("clickedPos");

        if (clickedPos == null) {
            clickedPos = new CompoundNBT();
        }

        ItemStack heldItem = context.getPlayer().getHeldItem(Hand.OFF_HAND);

        if (!heldItem.isEmpty() && heldItem.getItem() == Items.APPLE) {
            context.getPlayer().sendMessage(new StringTextComponent(DeprecatedMultiBlockStructureMaps.getStructureMaps().toString()));
        }

        if (!heldItem.isEmpty() && heldItem.getItem() == Items.STICK) {
            CompoundNBT writeBlock = new CompoundNBT();
            writeBlock.put("pos", NBTUtil.writeBlockPos(context.getPos()));
            writeBlock.putInt("direction", context.getFace().getIndex());
            clickedPos.put("active_block", writeBlock);
        } else if (context.getPlayer().isSneaking()) {
            clickedPos.put("pos1", NBTUtil.writeBlockPos(context.getPos()));
        } else {
            clickedPos.put("pos2", NBTUtil.writeBlockPos(context.getPos()));
        }

        context.getItem().setTagInfo("clickedPos", clickedPos);

        context.getPlayer().sendMessage(new StringTextComponent(clickedPos.toString()));
    }

    private static void readTileEntity(ItemUseContext context, World world, Consumer<TileEntity> consumer) {
        TileEntity tileEntity = world.getTileEntity(context.getPos());
        if (tileEntity != null) {
            consumer.accept(tileEntity);
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
            readMultiBlock(context);
        }
        return ActionResultType.SUCCESS;
    }
}
