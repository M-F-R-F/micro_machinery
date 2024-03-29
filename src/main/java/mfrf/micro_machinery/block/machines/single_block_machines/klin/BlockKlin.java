package mfrf.micro_machinery.block.machines.single_block_machines.klin;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockKlin extends MMBlockTileProviderBase {
    protected static final BooleanProperty BURNING = BooleanProperty.create("burning");
    public static final VoxelShape KLIN_SHAPE1 = Block.box(1, 0, 1, 15, 9, 15);
    public static final VoxelShape KLIN_SHAPE2 = Block.box(2, 9, 2, 14, 15, 14);
    public static final VoxelShape KLIN_SHAPE3 = Block.box(3, 15, 3, 13, 18, 13);
    public static final VoxelShape KLIN_SHAPE4 = Block.box(4, 18, 4, 12, 20, 12);
    private static final VoxelShape KLIN_SHAPE = Shapes.or(KLIN_SHAPE1, KLIN_SHAPE2, KLIN_SHAPE3, KLIN_SHAPE4);

    public BlockKlin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(BURNING, false));
    }

    public static void setState(boolean active, Level worldIn, BlockPos pos) {
        BlockState state = worldIn.getBlockState(pos);
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if (active) {
            worldIn.setBlock(pos, MMBlocks.KLIN.getKey().get().defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(BURNING, true), 3);
        } else {
            worldIn.setBlock(pos, MMBlocks.KLIN.getKey().get().defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(BURNING, false), 3);
        }

        if (tileentity != null) {
            tileentity.clearRemoved();
            worldIn.setBlockEntity(tileentity);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BURNING);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            TileKlin tileKlin = (TileKlin) worldIn.getBlockEntity(pos);
            ItemStack heldItem = player.getItemInHand(handIn);
            if (heldItem.getItem() instanceof BucketItem) {
                Fluid fluid = ((BucketItem) heldItem.getItem()).getFluid();
                tileKlin.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.EXECUTE);

            } else {
                NetworkHooks.openScreen((ServerPlayer) player, tileKlin, (FriendlyByteBuf packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileKlin.getBlockPos());
                });
            }
        }
        return InteractionResult.SUCCESS;

    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_KLIN_TYPE.get(), pBlockEntityType, TileKlin::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileKlin(pPos, pState);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return KLIN_SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return KLIN_SHAPE;
    }

}
