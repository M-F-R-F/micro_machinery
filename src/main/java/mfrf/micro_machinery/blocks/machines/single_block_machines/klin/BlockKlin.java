package mfrf.micro_machinery.blocks.machines.single_block_machines.klin;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockKlin extends MMBlockTileProviderBase {
    protected static final BooleanProperty BURNING = BooleanProperty.create("burning");
    public static final VoxelShape KLIN_SHAPE1 = Block.box(1, 0, 1, 15, 9, 15);
    public static final VoxelShape KLIN_SHAPE2 = Block.box(2, 9, 2, 14, 15, 14);
    public static final VoxelShape KLIN_SHAPE3 = Block.box(3, 15, 3, 13, 18, 13);
    public static final VoxelShape KLIN_SHAPE4 = Block.box(4, 18, 4, 12, 20, 12);
    private static final VoxelShape KLIN_SHAPE = VoxelShapes.or(KLIN_SHAPE1, KLIN_SHAPE2, KLIN_SHAPE3, KLIN_SHAPE4);

    public BlockKlin(Properties properties) {
        super(properties, "klin");
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(BURNING, false));
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        BlockState state = worldIn.getBlockState(pos);
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if (active) {
            worldIn.setBlock(pos, RegisteredBlocks.KLIN.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(BURNING, true), 3);
        } else {
            worldIn.setBlock(pos, RegisteredBlocks.KLIN.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(BURNING, false), 3);
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setBlockEntity(pos, tileentity);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BURNING);
    }

    @Override
    public boolean hasBlockEntity(BlockState state) {
        return true;
    }

    @Override
    public InteractionResult onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            TileKlin tileKlin = (TileKlin) worldIn.getBlockEntity(pos);
            ItemStack heldItem = player.getItemInHand(handIn);
            if (heldItem.getItem() instanceof BucketItem) {
                Fluid fluid = ((BucketItem) heldItem.getItem()).getFluid();
                tileKlin.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.EXECUTE);

            } else {
                NetworkHooks.openGui((ServerPlayer) player, tileKlin, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileKlin.getPos());
                });
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileKlin();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return KLIN_SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return KLIN_SHAPE;
    }
}
