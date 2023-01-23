package mfrf.micro_machinery.blocks.machines.single_block_machines.cutter;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.InteractionResult;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCutter extends MMBlockTileProviderBase {
    public static BooleanProperty WORKING = BooleanProperty.create("working");

    public BlockCutter(Properties properties) {
        super(properties, "cutter");
        registerDefaultState(getStateToRegistry().setValue(WORKING, false));
    }


    @Override
    public InteractionResult onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide()) {
            NetworkHooks.openGui((ServerPlayer) player, (TileCutter) worldIn.getBlockEntity(pos), (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(pos);
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(WORKING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileCutter();
    }
}
