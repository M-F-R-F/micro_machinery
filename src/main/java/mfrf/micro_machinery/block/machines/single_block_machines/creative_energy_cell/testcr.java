package mfrf.micro_machinery.block.machines.single_block_machines.creative_energy_cell;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class testcr extends Block implements EntityBlock {
    public static final String ID = "testcr";

    public testcr(){
        super(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new TileTestcr(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type){
        if(level.isClientSide){
            return null;
        } else {
            return (lvl, pos, st, be) ->{
                if(be instanceof TileTestcr testcr){
                    testcr.tickServer();
                }
            };
        }
    }
}
