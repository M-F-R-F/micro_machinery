package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.utils.MultiBlockStructureMaps;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
//            TileEntity tileEntity = world.getTileEntity(context.getPos());
//            if(tileEntity instanceof TileLathe)
//                context.getPlayer().sendMessage(new StringTextComponent(((TileLathe)tileEntity).getActionContainer().getActionQueue().toString()));
            CompoundNBT clickedPos = context.getItem().getChildTag("clickedPos");

            if (clickedPos == null) {
                clickedPos = new CompoundNBT();
            }

            ItemStack heldItem = context.getPlayer().getHeldItem(Hand.OFF_HAND);

            if(!heldItem.isEmpty() && heldItem.getItem() == Items.APPLE){
                context.getPlayer().sendMessage(new StringTextComponent(MultiBlockStructureMaps.getStructureMaps().toString()));
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
        return ActionResultType.SUCCESS;
    }
}
