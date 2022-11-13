package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.conveyor_belt.BlockConveyorBelt;
import mfrf.dbydd.micro_machinery.enums.EnumConveyorConnectState;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    private static void readMultiBlockOld(ItemUseContext context) {
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
////            readMultiBlockOld(context);
////            HashMap<String, MultiblockStructureMaps.StructureMap> structures = MultiblockStructureMaps.getStructures();
//            BlockPos pos = context.getPos();
//            BlockState blockState = world.getBlockState(pos);
//            if (blockState.getBlock() instanceof MMBlockMultiBlockPart) {
//                MMTileMultiBlockPart tileEntity = (MMTileMultiBlockPart) world.getTileEntity(pos);
////                context.getPlayer().sendMessage(new StringTextComponent(tileEntity.getPacked().toString()));
//                try {
//                    context.getPlayer().sendMessage(new StringTextComponent(
//                            tileEntity.getClass().getField("mainPart").toString()
//                    ));
//                } catch (NoSuchFieldException e) {
//                }
//            }

            // 获取点击的那一格的blockstate
            BlockState blockState = world.getBlockState(context.getPos());

            //确认是传送带方块
            if (blockState.getBlock() instanceof BlockConveyorBelt) {

                world.setBlockState(context.getPos(),
                        blockState
                                .with(BlockConveyorBelt.FACING, Direction.SOUTH)
                                .with(BlockConveyorBelt.OUT_STATE, EnumConveyorConnectState.CONNECTED)
                                .with(BlockConveyorBelt.BACK_STATE, true)
                                .with(BlockConveyorBelt.LEFT_STATE, true)
                                .with(BlockConveyorBelt.RIGHT_STATE, true)
                        // 以上五项就是对应的五个property,括号中左边的参数是索引，右边的是值
                        //想要看不同的blockstate只要赋值就行
                );

            }
        }
        return ActionResultType.SUCCESS;
    }
}
