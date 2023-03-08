package mfrf.micro_machinery.blocks.machines.single_block_machines.generator;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.utils.MathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.Shapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockGenerator extends MMBlockTileProviderBase {
    public static final VoxelShape GENERATOR_SHAPE1 = Block.box(0, 0, 0, 16, 4, 15);
    public static final VoxelShape GENERATOR_SHAPE2 = Block.box(0, 4, 11, 16, 16, 15);
    public static final VoxelShape GENERATOR_SHAPE3 = Block.box(2, 2, 15, 14, 14, 16);
    public static final VoxelShape GENERATOR_SHAPE4 = Block.box(2, 4, 2, 14, 14, 11);
    public static final VoxelShape SHAPE = Shapes.or(GENERATOR_SHAPE1, GENERATOR_SHAPE2, GENERATOR_SHAPE3, GENERATOR_SHAPE4);
    public static final BooleanProperty ISBURNING = BooleanProperty.create("isburning");

    public BlockGenerator(Properties properties) {
        super(properties, "generator");
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(ISBURNING, false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return makeShape(state.getValue(FACING));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return makeShape(state.getValue(FACING));
    }

    private VoxelShape makeShape(Direction direction) {
//        return Shapes.or(MathUtil.rotateDirection(GENERATOR_SHAPE1, direction), MathUtil.rotateDirection(GENERATOR_SHAPE2, direction),MathUtil.rotateDirection(GENERATOR_SHAPE3, direction),MathUtil.rotateDirection(GENERATOR_SHAPE4, direction));
        return MathUtil.VoxelShapeRotateDirection(SHAPE, direction);
    }

    public static void setIsburning(boolean isburning, World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).setValue(ISBURNING, isburning), 3);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ISBURNING);
        super.fillStateContainer(builder);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide() && handIn == InteractionHand.MAIN_HAND) {
            ItemStack heldItem = player.getItemInHand(handIn);
            TileGenerator tileGenerator = (TileGenerator) worldIn.getBlockEntity(pos);
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof BucketItem item) {
                if (item.getFluid() == Fluids.WATER) {
                    LazyOptional<IFluidHandler> capability = tileGenerator.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
                    capability.ifPresent(iFluidHandler -> {
                        int fill = iFluidHandler.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.SIMULATE);
                        if (fill != 0) {
                            iFluidHandler.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                            tileGenerator.markDirty2();
                            if (!player.isCreative()) {
                                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                            }
                        }
                    });
                }
            } else {
                NetworkHooks.openGui((ServerPlayer) player, tileGenerator, (FriendlyByteBuf packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileGenerator.getPos());
                });
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileGenerator(pPos, pState)
    }
}
