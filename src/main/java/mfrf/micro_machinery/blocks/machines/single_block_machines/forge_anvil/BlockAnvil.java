package mfrf.micro_machinery.blocks.machines.single_block_machines.forge_anvil;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.enums.EnumAnvilType;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
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

import javax.annotation.Nullable;

public class BlockAnvil extends MMBlockTileProviderBase {

    public final VoxelShape ANVIL_SN;
    public final VoxelShape ANVIL_WE;
    private final EnumAnvilType anvilType;

    public BlockAnvil(Properties properties, String name, EnumAnvilType anvilType, int height) {
        super(properties, name);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
        this.anvilType = anvilType;
        this.ANVIL_SN = Block.box(0, 0, 3, 16, height, 13);
        this.ANVIL_WE = Block.box(3, 0, 0, 13, height, 16);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof TileAnvil) {
            return ((TileAnvil) tileEntity).onActivated(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.PASS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileAnvil(anvilType);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.WEST || direction == Direction.EAST) {
            return ANVIL_WE;
        }
        return ANVIL_SN;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.WEST || direction == Direction.EAST) {
            return ANVIL_WE;
        }
        return ANVIL_SN;
    }
}
