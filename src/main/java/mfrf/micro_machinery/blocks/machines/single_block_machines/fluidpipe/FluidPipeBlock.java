package mfrf.micro_machinery.blocks.machines.single_block_machines.fluidpipe;

import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.enums.EnumFluidPipeState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.Shapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class FluidPipeBlock extends MMBlockBase {
    //todo clear blockItem by shift+rightClick
    public static final Map<Direction, VoxelShape> DIRECTION_VOXEL_SHAPE_MAP = new HashMap<>();
    public static final Map<Direction, EnumProperty<EnumFluidPipeState>> DIRECTION_ENUM_PROPERTY_MAP = new HashMap<>();
    public static final EnumProperty<EnumFluidPipeState> UP_ISCONNECTED = EnumProperty.create("up_connect", EnumFluidPipeState.class);
    public static final EnumProperty<EnumFluidPipeState> DOWN_ISCONNECTED = EnumProperty.create("down_connect", EnumFluidPipeState.class);
    public static final EnumProperty<EnumFluidPipeState> SOUTH_ISCONNECTED = EnumProperty.create("south_connect", EnumFluidPipeState.class);
    public static final EnumProperty<EnumFluidPipeState> NORTH_ISCONNECTED = EnumProperty.create("north_connect", EnumFluidPipeState.class);
    public static final EnumProperty<EnumFluidPipeState> WEST_ISCONNECTED = EnumProperty.create("west_connect", EnumFluidPipeState.class);
    public static final EnumProperty<EnumFluidPipeState> EAST_ISCONNECTED = EnumProperty.create("east_connect", EnumFluidPipeState.class);
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");

    public static final VoxelShape CENTER_SHAPE = Block.box(5.5, 5.5, 5.5, 10.5, 10.5, 10.5);
    public static final VoxelShape NORTH_SHAPE = Block.box(6, 6, 0, 10, 10, 6);
    public static final VoxelShape EAST_SHAPE = Block.box(10, 6, 6, 16, 10, 10);
    public static final VoxelShape SOUTH_SHAPE = Block.box(6, 6, 10, 10, 10, 16);
    public static final VoxelShape WEST_SHAPE = Block.box(0, 6, 6, 6, 10, 10);
    public static final VoxelShape UP_SHAPE = Block.box(6, 10, 6, 10, 16, 10);
    public static final VoxelShape DOWN_SHAPE = Block.box(6, 0, 6, 10, 6, 10);

    public FluidPipeBlock(Properties properties, String name) {
        super(properties, name);
        this.registerDefaultState(this.getStateDefinition().any().setValue(UP_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE).setValue(DOWN_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE).setValue(SOUTH_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE
        ).setValue(NORTH_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE).setValue(WEST_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE).setValue(EAST_ISCONNECTED, EnumFluidPipeState.AUTO_FALSE).setValue(BLOCKED, false));
    }

    @Override
    public boolean hasBlockEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getState(context.getWorld(), context.getPos());
    }

    public BlockState getState(World world, BlockPos pos) {
        BlockState defaultState = defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos.m_142300_ = pos.m_142300_(direction);
            BlockState neighborState = world.getBlockState.m_142300_)
            if (neighborState.getBlock() instanceof FluidPipeBlock) {
                defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumFluidPipeState.AUTO_TRUE);
            } else {
                BlockEntity tileEntity = world.getBlockEntity.m_142300_)
                if (tileEntity != null && tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction.getOpposite()).isPresent()) {
                    defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumFluidPipeState.AUTO_CONNECTED);
                }
            }
        }
        return defaultState;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        //todo 管道等级
        //todo 重写
//        builder.add(CABLE_MATERIAL_ENUM_PROPERTY);
        builder.add(UP_ISCONNECTED);
        builder.add(DOWN_ISCONNECTED);
        builder.add(SOUTH_ISCONNECTED);
        builder.add(NORTH_ISCONNECTED);
        builder.add(WEST_ISCONNECTED);
        builder.add(EAST_ISCONNECTED);
        builder.add(BLOCKED);

        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.UP, UP_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.DOWN, DOWN_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.SOUTH, SOUTH_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.NORTH, NORTH_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.WEST, WEST_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.EAST, EAST_ISCONNECTED);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.UP, UP_SHAPE);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.DOWN, DOWN_SHAPE);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.SOUTH, SOUTH_SHAPE);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.NORTH, NORTH_SHAPE);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.WEST, WEST_SHAPE);
        DIRECTION_VOXEL_SHAPE_MAP.put(Direction.EAST, EAST_SHAPE);
        super.fillStateContainer(builder);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FluidPipeTile(pPos, pState)
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        if (!world.isClientSide() && world instanceof World) {
            BlockEntity tileEntityNeighbor = world.getBlockEntity(neighbor);
            Direction facingFromVector = Direction.getFacingFromVector(neighbor.getX() - pos.getX(), neighbor.getY() - pos.getY(), neighbor.getZ() - pos.getZ());
            EnumProperty<EnumFluidPipeState> enumPipeStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);
            EnumFluidPipeState currentValue = state.getValue(enumPipeStateEnumProperty);

            if (!(currentValue == EnumFluidPipeState.CLOSE || currentValue == EnumFluidPipeState.OPEN)) {
                if (world.getBlockState(neighbor).getBlock() instanceof FluidPipeBlock) {
                    if (currentValue != EnumFluidPipeState.AUTO_TRUE) {
                        setStateAndUpdateNeighbor((World) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_TRUE));
//                        ((World) world).setBlockState(pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_TRUE));
                    }
                } else if (tileEntityNeighbor != null) {
                    if (tileEntityNeighbor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facingFromVector.getOpposite()).isPresent()) {
                        if (currentValue != EnumFluidPipeState.AUTO_CONNECTED) {
                            setStateAndUpdateNeighbor((World) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_CONNECTED));
                        }
                    }
                } else {
                    if (currentValue != EnumFluidPipeState.AUTO_FALSE) {
                        setStateAndUpdateNeighbor((World) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_FALSE));
                    }
                }
            }

        }
        super.onNeighborChange(state, world, pos, neighbor);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        worldIn.notifyNeighborsOfStateChange(pos, state.getBlock());
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    private boolean setStateAndUpdateNeighbor(World world, BlockPos pos, BlockState state) {
        boolean b = world.setBlockState(pos, state, 22);
        return b;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape shape = CENTER_SHAPE;

        for (Map.Entry<Direction, EnumProperty<EnumFluidPipeState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
            EnumFluidPipeState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
            if (enumCableState != EnumFluidPipeState.AUTO_FALSE && enumCableState != EnumFluidPipeState.CLOSE) {
                shape = Shapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
            }
        }


        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape shape = CENTER_SHAPE;

        for (Map.Entry<Direction, EnumProperty<EnumFluidPipeState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
            EnumFluidPipeState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
            if (enumCableState != EnumFluidPipeState.AUTO_FALSE && enumCableState != EnumFluidPipeState.CLOSE) {
                shape = Shapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
            }
        }


        return shape;
    }
}

