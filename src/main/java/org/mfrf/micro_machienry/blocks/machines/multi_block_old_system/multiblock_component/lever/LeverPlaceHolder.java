package org.mfrf.micro_machienry.blocks.machines.multi_block_old_system.multiblock_component.lever;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockPlaceHolder;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockRedStoneActiveable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class LeverPlaceHolder extends BlockPlaceHolder {

    public static final BooleanProperty ACTIVED = BooleanProperty.create("is_actived");

    public LeverPlaceHolder() {
        super("lever_place_holder");
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(IS_PLACEHOLDER, true).with(ACTIVED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ACTIVED);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isRemote()) {
            Boolean actived = !state.get(ACTIVED);
            worldIn.setBlockState(pos, state.with(ACTIVED, actived));
            TilePlaceHolder tileEntity = (TilePlaceHolder) worldIn.getTileEntity(pos);
            BlockPos mainPartPos = tileEntity.getMainPartPos();
            if(mainPartPos != null){
                TileEntity mainPart = worldIn.getTileEntity(mainPartPos);
                if(mainPart instanceof IMultiBlockRedStoneActiveable){
                    ((IMultiBlockRedStoneActiveable) mainPart).onActivated(actived);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
