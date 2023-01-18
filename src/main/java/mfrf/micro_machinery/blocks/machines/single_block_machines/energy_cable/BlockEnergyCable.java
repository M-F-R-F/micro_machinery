package mfrf.micro_machinery.blocks.machines.single_block_machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockEnergyCable extends MMBlockBase {
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

    public BlockEnergyCable(Properties properties, String name, EnumCableMaterial material) {
        super(properties, name);
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
            BlockState neighborState = world.getBlockState.m_142300_);
            if (neighborState.getBlock() instanceof BlockEnergyCable) {
                defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CABLE);
            } else {
                BlockEntity tileEntity = world.getBlockEntity.m_142300_);
                if (tileEntity != null && tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).isPresent()) {
                    defaultState = defaultState.setValue(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CONNECT);
                }
            }
        }
        return defaultState;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CABLE_MATERIAL_ENUM_PROPERTY);
        builder.add(UP_ISCONNECTED);
        builder.add(DOWN_ISCONNECTED);
        builder.add(SOUTH_ISCONNECTED);
        builder.add(NORTH_ISCONNECTED);
        builder.add(WEST_ISCONNECTED);
        builder.add(EAST_ISCONNECTED);
        super.fillStateContainer(builder);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileEnergyCable();
    }

    private boolean setStateNoUpdateNeighbor(World world, BlockPos pos, BlockState state) {
        return world.setBlockState(pos, state, 22);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!worldIn.isRemote() && worldIn instanceof World) {

            BlockEntity tileEntityNeighbor = worldIn.getBlockEntity(facingPos);
            Direction facingFromVector = Direction.getFacingFromVector(facingPos.getX() - currentPos.getX(), facingPos.getY() - currentPos.getY(), facingPos.getZ() - currentPos.getZ());
            EnumProperty<EnumCableState> enumCableStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);

            if (worldIn.getBlockState(facingPos).getBlock() instanceof BlockEnergyCable) {
                if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.CABLE) {
                    setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.CABLE));
                }
            } else if (tileEntityNeighbor != null) {
                if (tileEntityNeighbor.getCapability(CapabilityEnergy.ENERGY, facingFromVector.getOpposite()).isPresent()) {
                    if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.CONNECT) {
                        setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.CONNECT));
                    }
                }
            } else {
                if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.EMPTY) {
                    setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.setValue(enumCableStateEnumProperty, EnumCableState.EMPTY));
                }
            }

        }

        return stateIn;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape shape = CENTER_SHAPE;

        for (Map.Entry<Direction, EnumProperty<EnumCableState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
            EnumCableState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
            if (enumCableState != EnumCableState.EMPTY) {
                shape = VoxelShapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
            }
        }


        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape shape = CENTER_SHAPE;

        for (Map.Entry<Direction, EnumProperty<EnumCableState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
            EnumCableState enumCableState = state.getValue(directionEnumPropertyEntry.getValue());
            if (enumCableState != EnumCableState.EMPTY) {
                shape = VoxelShapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
            }
        }


        return shape;
    }

}
