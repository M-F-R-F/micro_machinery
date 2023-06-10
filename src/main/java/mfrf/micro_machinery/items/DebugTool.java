package mfrf.micro_machinery.items;

import mfrf.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Consumer;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    private static void readMultiBlockOld(UseOnContext context) {
        CompoundTag clickedPos = context.getItemInHand().getTagElement("clickedPos");

        if (clickedPos == null) {
            clickedPos = new CompoundTag();
        }

        ItemStack heldItem = context.getPlayer().getItemInHand(InteractionHand.OFF_HAND);

        if (!heldItem.isEmpty() && heldItem.getItem() == Items.APPLE) {
            context.getPlayer().displayClientMessage(new TextComponent(DeprecatedMultiBlockStructureMaps.getStructureMaps().toString()), true);
        }

        if (!heldItem.isEmpty() && heldItem.getItem() == Items.STICK) {
            CompoundTag writeBlock = new CompoundTag();
            writeBlock.put("pos", NbtUtils.writeBlockPos(context.getClickedPos()));
            writeBlock.putInt("direction", context.getClickedFace().get3DDataValue());
            clickedPos.put("active_block", writeBlock);
        } else if (context.getPlayer().isShiftKeyDown()) {
            clickedPos.put("pos1", NbtUtils.writeBlockPos(context.getClickedPos()));
        } else {
            clickedPos.put("pos2", NbtUtils.writeBlockPos(context.getClickedPos()));
        }

        context.getItemInHand().addTagElement("clickedPos", clickedPos);

        context.getPlayer().displayClientMessage(new TextComponent(clickedPos.toString()), true);
    }

    private static void readBlockEntity(UseOnContext context, Level world, Consumer<BlockEntity> consumer) {
        BlockEntity tileEntity = world.getBlockEntity(context.getClickedPos());
        if (tileEntity != null) {
            consumer.accept(tileEntity);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (!world.isClientSide()) {
            readMultiBlockOld(context);
//            HashMap<String, MultiblockStructureMaps.StructureMap> structures = MultiblockStructureMaps.getStructures();
//            BlockPos pos = context.getPos();
//            BlockState blockState = world.getBlockState(pos);
//            if (blockState.getBlock() instanceof MMBlockMultiBlockPart) {
//                MMTileMultiBlockPart tileEntity = (MMTileMultiBlockPart) world.getBlockEntity(pos);
////                context.getPlayer().displayClientMessage(new StringTextComponent(tileEntity.getPacked().toString()));
//                try {
//                    context.getPlayer().displayClientMessage(new StringTextComponent(
//                            tileEntity.getClass().getField("mainPart").toString()
//                    ));
//                } catch (NoSuchFieldException e) {
//                }
//            }

            // 获取点击的那一格的blockstate
//            BlockState blockState = world.getBlockState(context.getPos());

//            //确认是传送带方块
//            if (blockState.getBlock() instanceof BlockConveyorBelt) {
//
//                world.setBlockState(context.getPos(),
//                        blockState
//                                .setValue(BlockConveyorBelt.FACING, Direction.SOUTH)
//                                .setValue(BlockConveyorBelt.OUT_STATE, EnumConveyorConnectState.CONNECTED)
//                                .setValue(BlockConveyorBelt.BACK_STATE, true)
//                                .setValue(BlockConveyorBelt.LEFT_STATE, true)
//                                .setValue(BlockConveyorBelt.RIGHT_STATE, true)
//                        // 以上五项就是对应的五个property,括号中左边的参数是索引，右边的是值
//                        //想要看不同的blockstate只要赋值就行
//                );
//
//            }
        }
        return InteractionResult.SUCCESS;
    }
}
