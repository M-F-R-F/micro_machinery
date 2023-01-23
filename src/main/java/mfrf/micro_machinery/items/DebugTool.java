//package mfrf.micro_machinery.items;
//
//import mfrf.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
//import net.minecraft.item.ItemUseContext;
//import net.minecraft.item.Items;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.NBTUtil;
//import net.minecraft.util.Hand;
//import net.minecraft.util.InteractionResult;
//import net.minecraft.util.text.StringTextComponent;
//import net.minecraft.world.World;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.entity.BlockEntity;
//
//import java.util.function.Consumer;
//
//public class DebugTool extends MMItemBase {
//    public DebugTool() {
//        super("debug_tool");
//    }
//
//    private static void readMultiBlockOld(ItemUseContext context) {
//        CompoundTag clickedPos = context.getItem().getChildTag("clickedPos");
//
//        if (clickedPos == null) {
//            clickedPos = new CompoundTag();
//        }
//
//        ItemStack heldItem = context.getPlayer().getItemInHand(Hand.OFF_HAND);
//
//        if (!heldItem.isEmpty() && heldItem.getItem() == Items.APPLE) {
//            context.getPlayer().displayClientMessage(new StringTextComponent(DeprecatedMultiBlockStructureMaps.getStructureMaps().toString()));
//        }
//
//        if (!heldItem.isEmpty() && heldItem.getItem() == Items.STICK) {
//            CompoundTag writeBlock = new CompoundTag();
//            writeBlock.put("pos", NBTUtil.writeBlockPos(context.getPos()));
//            writeBlock.putInt("direction", context.getFace().getIndex());
//            clickedPos.put("active_block", writeBlock);
//        } else if (context.getPlayer().isSneaking()) {
//            clickedPos.put("pos1", NBTUtil.writeBlockPos(context.getPos()));
//        } else {
//            clickedPos.put("pos2", NBTUtil.writeBlockPos(context.getPos()));
//        }
//
//        context.getItem().setTagInfo("clickedPos", clickedPos);
//
//        context.getPlayer().displayClientMessage(new StringTextComponent(clickedPos.toString()));
//    }
//
//    private static void readBlockEntity(ItemUseContext context, World world, Consumer<BlockEntity> consumer) {
//        BlockEntity tileEntity = world.getBlockEntity(context.getPos());
//        if (tileEntity != null) {
//            consumer.accept(tileEntity);
//        }
//    }
//
//    @Override
//    public InteractionResult onItemUse(ItemUseContext context) {
//        World world = context.getWorld();
//        if (!world.isClientSide()) {
//            readMultiBlockOld(context);
////            HashMap<String, MultiblockStructureMaps.StructureMap> structures = MultiblockStructureMaps.getStructures();
////            BlockPos pos = context.getPos();
////            BlockState blockState = world.getBlockState(pos);
////            if (blockState.getBlock() instanceof MMBlockMultiBlockPart) {
////                MMTileMultiBlockPart tileEntity = (MMTileMultiBlockPart) world.getBlockEntity(pos);
//////                context.getPlayer().displayClientMessage(new StringTextComponent(tileEntity.getPacked().toString()));
////                try {
////                    context.getPlayer().displayClientMessage(new StringTextComponent(
////                            tileEntity.getClass().getField("mainPart").toString()
////                    ));
////                } catch (NoSuchFieldException e) {
////                }
////            }
//
//            // 获取点击的那一格的blockstate
////            BlockState blockState = world.getBlockState(context.getPos());
//
////            //确认是传送带方块
////            if (blockState.getBlock() instanceof BlockConveyorBelt) {
////
////                world.setBlockState(context.getPos(),
////                        blockState
////                                .setValue(BlockConveyorBelt.FACING, Direction.SOUTH)
////                                .setValue(BlockConveyorBelt.OUT_STATE, EnumConveyorConnectState.CONNECTED)
////                                .setValue(BlockConveyorBelt.BACK_STATE, true)
////                                .setValue(BlockConveyorBelt.LEFT_STATE, true)
////                                .setValue(BlockConveyorBelt.RIGHT_STATE, true)
////                        // 以上五项就是对应的五个property,括号中左边的参数是索引，右边的是值
////                        //想要看不同的blockstate只要赋值就行
////                );
////
////            }
//        }
//        return InteractionResult.SUCCESS;
//    }
//}
