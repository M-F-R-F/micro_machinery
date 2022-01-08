package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBlastFurnace extends MMMultiBlockHolderBase {

    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0f;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBlastFurnace();
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        ((MMMultiBlockTileMainPartBase) worldIn.getTileEntity(pos)).onBreak(worldIn, pos, player, state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof TileBlastFurnace) {
                TileBlastFurnace blastFurnace = (TileBlastFurnace) tileEntity;
                NetworkHooks.openGui((ServerPlayerEntity) player, blastFurnace, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(blastFurnace.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }
}
