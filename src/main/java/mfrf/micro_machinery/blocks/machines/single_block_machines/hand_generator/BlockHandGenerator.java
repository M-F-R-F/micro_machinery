package mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHandGenerator extends MMBlockTileProviderBase {

    public BlockHandGenerator(Properties properties) {
        super(properties, "hand_generator");
    }

    @Override
    public InteractionResult onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide() && handIn == InteractionHand.MAIN_HAND) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            Direction direction = Direction.fromAngle(state.getValue(FACING).getHorizontalAngle() - 90);
            if (tileEntity instanceof TileHandGenerator && hit.getFace() == direction) {
                ((TileHandGenerator) tileEntity).OnActivated(direction.getOpposite());
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileHandGenerator();
    }
}
