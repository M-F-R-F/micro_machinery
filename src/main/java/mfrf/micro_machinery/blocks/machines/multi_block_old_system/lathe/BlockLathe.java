package mfrf.micro_machinery.blocks.machines.multi_block_old_system.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.utils.MathUtil;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockLathe extends MMMultiBlockHolderBase {
    public static final VoxelShape LATHE_SHAPE1 = Block.box(2, 0, 0, 12, 16, 16);
    public static final VoxelShape LATHE_SHAPE2 = Block.box(16, 16, 0, 0, 18, 16);
    public static final VoxelShape LATHE_SHAPE3 = Block.box(12, 1, 1, 15, 15, 15);
    public static final VoxelShape LATHE_SHAPE4 = Block.box(9, 18, 5, 15, 31, 11);
    public static final VoxelShape LATHE_SHAPE5 = Block.box(7, 18, 2, 14, 30, 14);
    public static final VoxelShape LATHE_SHAPE6 = Block.box(7, 18, 4, 0, 19, 12);

    public static final VoxelShape LATHE_SHAPE7 = Block.box(6, 0, 0, 1, 16, 16);
    public static final VoxelShape LATHE_SHAPE8 = Block.box(18, 10, 1, 6, 16, 16);
    public static final VoxelShape LATHE_SHAPE9 = Block.box(16, 16, 0, 0, 18, 16);
    public static final VoxelShape LATHE_SHAPE10 = Block.box(16, 18, 4, 3, 19, 12);

    public BlockLathe(BlockBehaviour.Properties properties, String name) {
        super(properties, name, true, true, false);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.SOUTH).setValue(IS_PLACEHOLDER, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        if (!state.getValue(IS_PLACEHOLDER)) {
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
    public void harvestBlock(World worldIn, Player player, BlockPos pos, BlockState state, BlockEntity te, ItemStack stack) {
        Boolean isPlaceHolder = state.getValue(IS_PLACEHOLDER);
        if (isPlaceHolder) {
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).rotateY()), false, player);
        } else {
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).rotateYCCW()), false, player);
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        Boolean isPlaceHolder = state.getValue(IS_PLACEHOLDER);
        if (isPlaceHolder) {
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).rotateY()), false);
        } else {
            worldIn.destroyBlock(pos.m_142300_(state.getValue(FACING).rotateYCCW()), false);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Direction direction = state.getValue(FACING);
        worldIn.setBlock(pos.m_142300_(direction.rotateYCCW()), defaultBlockState().setValue(FACING, direction).setValue(IS_PLACEHOLDER, true));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.getValue(IS_PLACEHOLDER)) {
            return holderShape(state.getValue(FACING));
        } else {
            return makeShape(state.getValue(FACING));
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.getValue(IS_PLACEHOLDER)) {
            return holderShape(state.getValue(FACING));
        } else {
            return makeShape(state.getValue(FACING));
        }
    }

    private VoxelShape makeShape(Direction direction) {
        return VoxelShapes.or(MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE1, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE2, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE3, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE4, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE5, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE6, direction));
    }

    private VoxelShape holderShape(Direction direction) {
        return VoxelShapes.or(MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE7, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE8, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE9, direction), MathUtil.VoxelShapeRotateDirection(LATHE_SHAPE10, direction));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(state.getValue(IS_PLACEHOLDER) ? pos.m_142300_(state.getValue(FACING).rotateY()) : pos);
            if (tileEntity instanceof TileLathe) {
                NetworkHooks.openGui((ServerPlayer) player, (TileLathe) tileEntity, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileEntity.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockPos getMainPartPos(BlockState state, BlockPos currentPos) {
        if (state.getValue(IS_PLACEHOLDER)) {
            return currentPos.m_142300_(state.getValue(FACING).rotateY());
        }
        return super.getMainPartPos(state, currentPos);
    }
}
