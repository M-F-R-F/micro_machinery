package mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class BlockHandGenerator extends MMBlockTileProviderBase {

    public BlockHandGenerator(Properties properties) {
        super(properties, "hand_generator");
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
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
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileHandGenerator(pPos, pState)
    }
}
