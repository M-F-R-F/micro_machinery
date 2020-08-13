package com.dbydd.micro_machinery.blocks.mathines.forge_anvil;

import com.dbydd.micro_machinery.blocks.mathines.MMBlockTileProviderBase;
import com.dbydd.micro_machinery.enums.EnumAnvilType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAnvil extends MMBlockTileProviderBase {

    private final EnumAnvilType anvilType;

    public BlockAnvil(Properties properties, String name, EnumAnvilType anvilType) {
        super(properties, name);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
        this.anvilType = anvilType;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileAnvil){
            return ((TileAnvil)tileEntity).onActivated(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.PASS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileAnvil(anvilType);
    }
}
