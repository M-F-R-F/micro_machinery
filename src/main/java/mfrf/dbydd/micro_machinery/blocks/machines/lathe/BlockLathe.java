package mfrf.dbydd.micro_machinery.blocks.machines.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.utils.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockLathe extends MMMultiBlockHolderBase {
    public static final VoxelShape LATHE_SHAPE1 = Block.makeCuboidShape(2, 0, 0, 12, 16, 16);
    public static final VoxelShape LATHE_SHAPE2 = Block.makeCuboidShape(16, 16, 0, 0, 18, 16);
    public static final VoxelShape LATHE_SHAPE3 = Block.makeCuboidShape(12, 1, 1, 15, 15, 15);
    public static final VoxelShape LATHE_SHAPE4 = Block.makeCuboidShape(9, 18, 5, 15, 31, 11);
    public static final VoxelShape LATHE_SHAPE5 = Block.makeCuboidShape(7, 18, 2, 14, 30, 14);
    public static final VoxelShape LATHE_SHAPE6 = Block.makeCuboidShape(7, 18, 4, 0, 19, 12);

    public static final VoxelShape LATHE_SHAPE7 = Block.makeCuboidShape(6, 0, 0, 1, 16, 16);
    public static final VoxelShape LATHE_SHAPE8 = Block.makeCuboidShape(18, 10, 1, 6, 16, 16);
    public static final VoxelShape LATHE_SHAPE9 = Block.makeCuboidShape(16, 16, 0, 0, 18, 16);
    public static final VoxelShape LATHE_SHAPE10 = Block.makeCuboidShape(16, 18, 4, 3, 19, 12);

    public BlockLathe(Properties properties, String name) {
        super(properties, name, true, true, false);
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(IS_PLACEHOLDER, false));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (!state.get(IS_PLACEHOLDER)) {
            return new TileLathe();
        } else {
            return new TilePlaceHolder();
        }
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        Boolean isPlaceHolder = state.get(IS_PLACEHOLDER);
        if (isPlaceHolder) {
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateY()), false, player);
        } else {
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateYCCW()), false, player);
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        Boolean isPlaceHolder = state.get(IS_PLACEHOLDER);
        if (isPlaceHolder) {
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateY()), false);
        } else {
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateYCCW()), false);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Direction direction = state.get(FACING);
        worldIn.setBlockState(pos.offset(direction.rotateYCCW()), getDefaultState().with(FACING, direction).with(IS_PLACEHOLDER, true));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(IS_PLACEHOLDER)) {
            return holderShape(state.get(FACING));
        } else {
            return makeShape(state.get(FACING));
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(IS_PLACEHOLDER)) {
            return holderShape(state.get(FACING));
        } else {
            return makeShape(state.get(FACING));
        }
    }

    private VoxelShape makeShape(Direction direction) {
        return VoxelShapes.or(MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE1, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE2, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE3, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE4, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE5, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE6, direction));
    }

    private VoxelShape holderShape(Direction direction) {
        return VoxelShapes.or(MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE7, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE8, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE9, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE10, direction));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(state.get(IS_PLACEHOLDER) ? pos.offset(state.get(FACING).rotateY()) : pos);
            if (tileEntity instanceof TileLathe) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileLathe) tileEntity, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileEntity.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockPos getMainPartPos(BlockState state, BlockPos currentPos) {
        if (state.get(IS_PLACEHOLDER)) {
            return currentPos.offset(state.get(FACING).rotateY());
        }
        return super.getMainPartPos(state, currentPos);
    }
}
