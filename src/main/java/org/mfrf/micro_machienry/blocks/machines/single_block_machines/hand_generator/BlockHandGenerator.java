package org.mfrf.micro_machienry.blocks.machines.single_block_machines.hand_generator;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHandGenerator extends MMBlockTileProviderBase {

    public BlockHandGenerator(Properties properties) {
        super(properties, "hand_generator");
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote() && handIn == Hand.MAIN_HAND) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            Direction direction = Direction.fromAngle(state.get(FACING).getHorizontalAngle() - 90);
            if (tileEntity instanceof TileHandGenerator && hit.getFace() == direction) {
                ((TileHandGenerator) tileEntity).OnActivated(direction.getOpposite());
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileHandGenerator();
    }
}
