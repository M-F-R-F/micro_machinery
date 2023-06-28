package mfrf.micro_machinery.blocks.machines.single_block_machines.forge_anvil;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.enums.EnumAnvilType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof TileAnvil) {
            return ((TileAnvil) tileEntity).onActivated(state, worldIn, pos, player, handIn, hit);
        }
        return InteractionResult.PASS;
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileAnvil(anvilType, pPos, pState);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        if (direction == Direction.WEST || direction == Direction.EAST) {
            return ANVIL_WE;
        }
        return ANVIL_SN;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.WEST || direction == Direction.EAST) {
            return ANVIL_WE;
        }
        return ANVIL_SN;
    }
}
