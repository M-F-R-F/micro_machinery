package mfrf.dbydd.micro_machinery.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockAccessory;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    public static JsonObject readMultiBlock(BlockPos pos1, BlockPos pos2, BlockPos activeBlockPos, World world) {
        BlockPos posLower;
        BlockPos posHigher;

        if (pos1.getY() <= pos2.getY() && pos1.getX() <= pos2.getX() && pos1.getZ() <= pos2.getZ()) {
            posLower = pos1;
            posHigher = pos2;
        } else {
            posLower = pos2;
            posHigher = pos1;
        }

        int offsetX = posHigher.getX() - posLower.getX();
        int offsetY = posHigher.getY() - posLower.getY();
        int offsetZ = posHigher.getZ() - posLower.getZ();

        BlockPos relativeActivePos = activeBlockPos.add(-posLower.getX(), -posLower.getY(), -posLower.getZ());

        BlockState[][][] states = new BlockState[Math.abs(offsetX)][Math.abs(offsetY)][Math.abs(offsetZ)];
        ArrayList<BlockPos> accessoryBlocks = new ArrayList<>();

        for (int i = 0; i < offsetX; i++) {
            for (int i1 = 0; i1 < offsetY; i1++) {
                for (int i2 = 0; i2 < offsetZ; i2++) {

                    BlockState blockState = world.getBlockState(posLower.add(i, i1, i2));

                    if (blockState.getBlock() instanceof IMultiBlockAccessory) {
                        accessoryBlocks.add(new BlockPos(i, i1, i2));
                    }

                    states[i][i1][i2] = blockState;
                }
            }
        }


        JsonObject json = new JsonObject();

        JsonArray jsonX = new JsonArray();
        for (int x = 0; x < states.length; x++) {
            JsonArray jsonY = new JsonArray();
            for (int y = 0; y < states[x].length; y++) {
                JsonArray jsonZ = new JsonArray();
                for (int z = 0; z < states[y].length; z++) {
                    jsonZ.add(NBTUtil.writeBlockState(states[x][y][z]).toString());
                }
                jsonY.add(jsonZ);
            }
            jsonX.add(jsonY);
        }

        JsonArray accessoryArray = new JsonArray();
        for (BlockPos accessoryBlock : accessoryBlocks) {
            accessoryArray.add(NBTUtil.writeBlockPos(accessoryBlock).toString());
        }

        json.add("structure", jsonX);
        json.add("accessory", accessoryArray);
        json.addProperty("activeBlock", NBTUtil.writeBlockPos(relativeActivePos).toString());

        return json;
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
            if (!heldItem.isEmpty() && heldItem.getItem() == Items.STICK) {
                clickedPos.put("active_block", NBTUtil.writeBlockPos(context.getPos()));
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
