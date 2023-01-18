package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.lever;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockPlaceHolder;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockRedStoneActiveable;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class LeverPlaceHolder extends BlockPlaceHolder {

    public static final BooleanProperty ACTIVED = BooleanProperty.create("is_actived");

    public LeverPlaceHolder() {
        super("lever_place_holder");
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.SOUTH).setValue(IS_PLACEHOLDER, true).setValue(ACTIVED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ACTIVED);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isRemote()) {
            Boolean actived = !state.getValue(ACTIVED);
            worldIn.setBlock(pos, state.setValue(ACTIVED, actived));
            TilePlaceHolder tileEntity = (TilePlaceHolder) worldIn.getBlockEntity(pos);
            BlockPos mainPartPos = tileEntity.getMainPartPos();
            if(mainPartPos != null){
                BlockEntity mainPart = worldIn.getBlockEntity(mainPartPos);
                if(mainPart instanceof IMultiBlockRedStoneActiveable){
                    ((IMultiBlockRedStoneActiveable) mainPart).onActivated(actived);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
