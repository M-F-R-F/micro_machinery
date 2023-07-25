package mfrf.micro_machinery.block.machines.single_block_machines.energy_cable;

import mfrf.micro_machinery.block.MMBlockBase;
import mfrf.micro_machinery.block.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.enums.EnumCableMaterial;
import mfrf.micro_machinery.enums.EnumCableState;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockEnergyCable extends MMBlockBase implements EntityBlock {
    public static final Map<Direction, VoxelShape> DIRECTION_VOXEL_SHAPE_MAP = new HashMap<>();
    public static final Map<Direction, EnumProperty<EnumCableState>> DIRECTION_ENUM_PROPERTY_MAP = new HashMap<>();
    public static final EnumProperty<EnumCableMaterial> CABLE_MATERIAL_ENUM_PROPERTY = EnumProperty.create("material", EnumCableMaterial.class);
    public static final EnumProperty<EnumCableState> UP_ISCONNECTED = EnumProperty.create("up_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> DOWN_ISCONNECTED = EnumProperty.create("down_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> SOUTH_ISCONNECTED = EnumProperty.create("south_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> NORTH_ISCONNECTED = EnumProperty.create("north_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> WEST_ISCONNECTED = EnumProperty.create("west_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> EAST_ISCONNECTED = EnumProperty.create("east_connect", EnumCableState.class);

    public static final VoxelShape CENTER_SHAPE = Block.box(5.5, 5.5, 5.5, 10.5, 10.5, 10.5);
    public static final VoxelShape NORTH_SHAPE = Block.box(6, 6, 0, 10, 10, 6);
    public static final VoxelShape EAST_SHAPE = Block.box(10, 6, 6, 16, 10, 10);
    public static final VoxelShape SOUTH_SHAPE = Block.box(6, 6, 10, 10, 10, 16);
    public static final VoxelShape WEST_SHAPE = Block.box(0, 6, 6, 6, 10, 10);
    public static final VoxelShape UP_SHAPE = Block.box(6, 10, 6, 10, 16, 10);
    public static final VoxelShape DOWN_SHAPE = Block.box(6, 0, 6, 10, 6, 10);

    public BlockEnergyCable(Properties properties, EnumCableMaterial material) {
        super(properties);
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
        this.registerDefaultState(this.getStateDefinition().any().setValue(CABLE_MATERIAL_ENUM_PROPERTY, material).setValue(UP_ISCONNECTED, EnumCableState.EMPTY).setValue(DOWN_ISCONNECTED, EnumCableState.EMPTY).setValue(SOUTH_ISCONNECTED, EnumCableState.EMPTY
        ).setValue(NORTH_ISCONNECTED, EnumCableState.EMPTY).setValue(WEST_ISCONNECTED, EnumCableState.EMPTY).setValue(EAST_ISCONNECTED, EnumCableState.EMPTY));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getState(context.getLevel(), context.getClickedPos());
    }

    public BlockState getState(Level world, BlockPos pos) {
        BlockState defaultState = defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos offset = pos.relative(direction);
            BlockState neighborState = world.getBlockState(offset);
            if (neighborState.getBlock() instanceof BlockEnergyCable) {
                defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CABLE);
            } else {
                BlockEntity tileEntity = world.getBlockEntity(offset);
                if (tileEntity != null && tileEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).isPresent()) {
                    defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CONNECT);
                }
            }
        }
        return defaultState;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CABLE_MATERIAL_ENUM_PROPERTY);
        builder.add(UP_ISCONNECTED);
        builder.add(DOWN_ISCONNECTED);
        builder.add(SOUTH_ISCONNECTED);
        builder.add(NORTH_ISCONNECTED);
        builder.add(WEST_ISCONNECTED);
        builder.add(EAST_ISCONNECTED);
        super.createBlockStateDefinition(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileEnergyCable(pPos, pState);
    }


    private boolean setStateNoUpdateNeighbor(Level world, BlockPos pos, BlockState state) {
        return world.setBlock(pos, state, 22);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pLevel.setBlock(pPos, updatePostPlacement(pState, pLevel, pPos), 2);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Level worldIn, BlockPos currentPos) {
        if (!worldIn.isClientSide()) {
            for (Direction value : Direction.values()) {//todo test facingPos
                BlockPos facingPos = currentPos.relative(value);
                BlockEntity tileEntityNeighbor = worldIn.getBlockEntity(facingPos);
                Direction facingFromVector = Direction.getNearest(facingPos.getX() - currentPos.getX(), facingPos.getY() - currentPos.getY(), facingPos.getZ() - currentPos.getZ());
                EnumProperty<EnumCableState> enumCableStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);

                if (worldIn.getBlockState(facingPos).getBlock() instanceof BlockEnergyCable) {
                    if (stateIn.getValue(enumCableStateEnumProperty) != EnumCableState.CABLE) {
                        setStateNoUpdateNeighbor(worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.CABLE));
                    }
                } else if (tileEntityNeighbor != null) {
                    if (tileEntityNeighbor.getCapability(ForgeCapabilities.ENERGY, facingFromVector.getOpposite()).isPresent()) {
                        if (stateIn.getValue(enumCableStateEnumProperty) != EnumCableState.CONNECT) {
                            setStateNoUpdateNeighbor(worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.CONNECT));
                        }
                    }
                } else {
                    if (stateIn.getValue(enumCableStateEnumProperty) != EnumCableState.EMPTY) {
                        setStateNoUpdateNeighbor(worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.EMPTY));
                    }
                }
            }

        }

        return stateIn;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) TileHelper.createTicker(pLevel, MMBlockEntityTypes.TILE_ENERGY_CABLE.get(), pBlockEntityType, TileEnergyCable::tick);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = CENTER_SHAPE;

//        for (Map.Entry<Direction, EnumProperty<EnumCableState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
//            EnumCableState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
//            if (enumCableState != EnumCableState.EMPTY) {
//                shape = Shapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
//            }
//        }
//

        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = CENTER_SHAPE;

//        for (Map.Entry<Direction, EnumProperty<EnumCableState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
//            EnumCableState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
//            if (enumCableState != EnumCableState.EMPTY) {
//                shape = Shapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
//            }
//        }


        return shape;
    }
}
