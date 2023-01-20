package mfrf.micro_machinery.blocks.machines.single_block_machines.atomization;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.InteractionResult;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockAtomization extends MMBlockTileProviderBase {

    public BlockAtomization(Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Override
    public @org.jetbrains.annotations.Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileAtomization();
    }

    @Override
    public InteractionResult onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            NetworkHooks.openGui((ServerPlayer) player, (TileAtomization) worldIn.getBlockEntity(pos), (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(pos);
            });
        }
        return InteractionResult.SUCCESS;
    }
}
