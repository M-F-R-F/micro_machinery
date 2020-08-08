package com.dbydd.micro_machinery.blocks.mathines.generator;

import com.dbydd.micro_machinery.blocks.mathines.MMBlockTileProviderBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.blockplacer.BlockPlacer;

import javax.annotation.Nullable;

public class BlockGenerator extends MMBlockTileProviderBase {
    public static final BooleanProperty ISBURNING = BooleanProperty.create("isburning");

    public BlockGenerator() {
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(3.0f), "generator");
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ISBURNING, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ISBURNING);
        super.fillStateContainer(builder);
    }

    public static void setIsburning(boolean isburning, World world, BlockPos pos){
        world.setBlockState(pos, world.getBlockState(pos).with(ISBURNING, isburning), 3);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileGenerator();
    }
}
