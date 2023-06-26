package org.mfrf.micro_machienry.blocks.machines.single_block_machines.klin;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockKlin extends MMBlockTileProviderBase {
    protected static final BooleanProperty BURNING = BooleanProperty.create("burning");
    public static final VoxelShape KLIN_SHAPE1 = Block.makeCuboidShape(1, 0, 1, 15, 9, 15);
    public static final VoxelShape KLIN_SHAPE2 = Block.makeCuboidShape(2, 9, 2, 14, 15, 14);
    public static final VoxelShape KLIN_SHAPE3 = Block.makeCuboidShape(3, 15, 3, 13, 18, 13);
    public static final VoxelShape KLIN_SHAPE4 = Block.makeCuboidShape(4, 18, 4, 12, 20, 12);
    private static final VoxelShape KLIN_SHAPE = VoxelShapes.or(KLIN_SHAPE1, KLIN_SHAPE2, KLIN_SHAPE3, KLIN_SHAPE4);

    public BlockKlin(Properties properties) {
        super(properties, "klin");
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(BURNING, false));
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        BlockState state = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, RegisteredBlocks.KLIN.getDefaultState().with(FACING, state.get(FACING)).with(BURNING, true), 3);
        } else {
            worldIn.setBlockState(pos, RegisteredBlocks.KLIN.getDefaultState().with(FACING, state.get(FACING)).with(BURNING, false), 3);
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BURNING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            TileKlin tileKlin = (TileKlin) worldIn.getTileEntity(pos);
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem.getItem() instanceof BucketItem) {
                Fluid fluid = ((BucketItem) heldItem.getItem()).getFluid();
                tileKlin.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.EXECUTE);

            } else {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileKlin, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileKlin.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
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
