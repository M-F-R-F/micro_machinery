package mfrf.dbydd.micro_machinery.blocks.machines.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.utils.VoxelShapeUtil;
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
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockLathe extends MMMultiBlockBase {

    public static final VoxelShape LATHE_SHAPE1 = Block.makeCuboidShape(0, 0, 0, 15, 4, 15);
    public static final VoxelShape LATHE_SHAPE2 = Block.makeCuboidShape(0, 4, 11, 16, 16, 15);
    public static final VoxelShape LATHE_SHAPE3 = Block.makeCuboidShape(2, 2, 15, 14, 14, 16);
    public static final VoxelShape LATHE_SHAPE4 = Block.makeCuboidShape(2, 4, 2, 14, 14, 11);


    public BlockLathe(Properties properties, String name) {
        super(properties, name, true,false);
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
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        Boolean isPlaceHolder = state.get(IS_PLACEHOLDER);
        if(isPlaceHolder){
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateY()), false, player);
        }else {
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateYCCW()), false, player);
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        Boolean isPlaceHolder = state.get(IS_PLACEHOLDER);
        if(isPlaceHolder){
            worldIn.destroyBlock(pos.offset(state.get(FACING).rotateY()), false);
        }else {
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
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(state.get(IS_PLACEHOLDER) ? pos.offset(state.get(FACING).rotateY()) : pos);
            if(tileEntity instanceof TileLathe) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileLathe)tileEntity, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileEntity.getPos());
                });
            }
        }
        return ActionResultType.SUCCESS;
    }
}
