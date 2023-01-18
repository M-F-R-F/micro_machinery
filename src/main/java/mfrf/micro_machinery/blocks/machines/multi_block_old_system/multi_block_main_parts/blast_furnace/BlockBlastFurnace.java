package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockMainPartBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBlastFurnace extends MMMultiBlockMainPartBase {

    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(IS_PLACEHOLDER, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }

    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileBlastFurnace();
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, Player player) {
        ((MMMultiBlockTileMainPartBase) worldIn.getBlockEntity(pos)).onBreak(worldIn, pos, player, state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof TileBlastFurnace) {
                TileBlastFurnace blastFurnace = (TileBlastFurnace) tileEntity;
                NetworkHooks.openGui((ServerPlayer) player, blastFurnace, (PacketBuffer packerBuffer) -> {
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
