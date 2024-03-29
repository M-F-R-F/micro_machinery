package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.electrolysis;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockElectrolysis extends MMBlockTileProviderBase {
    public BlockElectrolysis(Properties properties) {
        super(properties, "electrolysis");
        this.setDefaultState(getStateToRegistry());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileElectrolysis();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (TileElectrolysis) worldIn.getTileEntity(pos), (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(pos);
            });
        }
        return ActionResultType.SUCCESS;
    }
}
