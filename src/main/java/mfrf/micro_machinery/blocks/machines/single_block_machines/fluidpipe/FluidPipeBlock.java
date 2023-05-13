package mfrf.micro_machinery.blocks.machines.single_block_machines.fluidpipe;

import mfrf.micro_machinery.blocks.MMBlockBase;
import mfrf.micro_machinery.enums.EnumFluidPipeState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class FluidPipeBlock extends MMBlockBase implements EntityBlock {
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

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getState(pContext.getLevel(), pContext.getClickedPos());
    }

    public BlockState getState(Level world, BlockPos pos) {
        BlockState defaultState = defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos offset = pos.m_142300_(direction);
            BlockState neighborState = world.getBlockState(offset);
            if (neighborState.getBlock() instanceof FluidPipeBlock) {
                defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumFluidPipeState.AUTO_TRUE);
            } else {
                BlockEntity tileEntity = world.getBlockEntity(offset);
                if (tileEntity != null && tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction.getOpposite()).isPresent()) {
                    defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumFluidPipeState.AUTO_CONNECTED);
                }
            }
        }
        return defaultState;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
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
        super.createBlockStateDefinition(builder);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FluidPipeTile(pPos, pState);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader world, BlockPos pos, BlockPos neighbor) {
        if (!world.isClientSide() && world instanceof Level) {
            BlockEntity tileEntityNeighbor = world.getBlockEntity(neighbor);
            Direction facingFromVector = Direction.getNearest(neighbor.getX() - pos.getX(), neighbor.getY() - pos.getY(), neighbor.getZ() - pos.getZ());
            EnumProperty<EnumFluidPipeState> enumPipeStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);
            EnumFluidPipeState currentValue = state.getValue(enumPipeStateEnumProperty);

            if (!(currentValue == EnumFluidPipeState.CLOSE || currentValue == EnumFluidPipeState.OPEN)) {
                if (world.getBlockState(neighbor).getBlock() instanceof FluidPipeBlock) {
                    if (currentValue != EnumFluidPipeState.AUTO_TRUE) {
                        setStateAndUpdateNeighbor((Level) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_TRUE));
//                        ((World) world).setBlockAndUpdate(pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_TRUE))
                    }
                } else if (tileEntityNeighbor != null) {
                    if (tileEntityNeighbor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facingFromVector.getOpposite()).isPresent()) {
                        if (currentValue != EnumFluidPipeState.AUTO_CONNECTED) {
                            setStateAndUpdateNeighbor((Level) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_CONNECTED));
                        }
                    }
                } else {
                    if (currentValue != EnumFluidPipeState.AUTO_FALSE) {
                        setStateAndUpdateNeighbor((Level) world, pos, state.setValue(enumPipeStateEnumProperty, EnumFluidPipeState.AUTO_FALSE));
                    }
                }
            }

        }
        super.onNeighborChange(state, world, pos, neighbor);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    private boolean setStateAndUpdateNeighbor(Level world, BlockPos pos, BlockState state) {
        boolean b = world.setBlock(pos, state, 22);
        return b;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

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
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

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

