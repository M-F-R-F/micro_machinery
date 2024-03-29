package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockMainPartBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBlastFurnace extends MMMultiBlockMainPartBase {

    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
        this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(IS_PLACEHOLDER, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
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
            if (tileEntity instanceof TileBlastFurnace) {
                TileBlastFurnace blastFurnace = (TileBlastFurnace) tileEntity;
                NetworkHooks.openGui((ServerPlayerEntity) player, blastFurnace, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(blastFurnace.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return super.getShape(state, worldIn, pos, context);
    }
}
