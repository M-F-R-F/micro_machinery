package mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
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

    public static final VoxelShape CENTER_SHAPE = Block.makeCuboidShape(5.5, 5.5, 5.5, 10.5, 10.5, 10.5);
    public static final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(6, 6, 0, 10, 10, 6);
    public static final VoxelShape EAST_SHAPE = Block.makeCuboidShape(10, 6, 6, 16, 10, 10);
    public static final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(6, 6, 10, 10, 10, 16);
    public static final VoxelShape WEST_SHAPE = Block.makeCuboidShape(0, 6, 6, 6, 10, 10);
    public static final VoxelShape UP_SHAPE = Block.makeCuboidShape(6, 10, 6, 10, 16, 10);
    public static final VoxelShape DOWN_SHAPE = Block.makeCuboidShape(6, 0, 6, 10, 6, 10);

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
        this.setDefaultState(this.stateContainer.getBaseState().with(CABLE_MATERIAL_ENUM_PROPERTY, material).with(UP_ISCONNECTED, EnumCableState.EMPTY).with(DOWN_ISCONNECTED, EnumCableState.EMPTY).with(SOUTH_ISCONNECTED, EnumCableState.EMPTY
        ).with(NORTH_ISCONNECTED, EnumCableState.EMPTY).with(WEST_ISCONNECTED, EnumCableState.EMPTY).with(EAST_ISCONNECTED, EnumCableState.EMPTY));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getState(context.getWorld(), context.getPos());
    }

    public BlockState getState(World world, BlockPos pos) {
        BlockState defaultState = getDefaultState();
        for (Direction direction : Direction.values()) {
            BlockPos offset = pos.offset(direction);
            BlockState neighborState = world.getBlockState(offset);
            if (neighborState.getBlock() instanceof BlockEnergyCable) {
                defaultState = defaultState.with(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CABLE);
            } else {
                TileEntity tileEntity = world.getTileEntity(offset);
                if (tileEntity != null && tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).isPresent()) {
                    defaultState = defaultState.with(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CONNECT);
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
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEnergyCable();
    }

    private boolean setStateNoUpdateNeighbor(World world, BlockPos pos, BlockState state) {
        return world.setBlockState(pos, state, 22);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!worldIn.isRemote() && worldIn instanceof World) {

            TileEntity tileEntityNeighbor = worldIn.getTileEntity(facingPos);
            Direction facingFromVector = Direction.getFacingFromVector(facingPos.getX() - currentPos.getX(), facingPos.getY() - currentPos.getY(), facingPos.getZ() - currentPos.getZ());
            EnumProperty<EnumCableState> enumCableStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);

            if (worldIn.getBlockState(facingPos).getBlock() instanceof BlockEnergyCable) {
                if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.CABLE) {
                    setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.with(enumCableStateEnumProperty, EnumCableState.CABLE));
                }
            } else if (tileEntityNeighbor != null) {
                if (tileEntityNeighbor.getCapability(CapabilityEnergy.ENERGY, facingFromVector.getOpposite()).isPresent()) {
                    if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.CONNECT) {
                        setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.with(enumCableStateEnumProperty, EnumCableState.CONNECT));
                    }
                }
            } else {
                if (stateIn.get(enumCableStateEnumProperty) != EnumCableState.EMPTY) {
                    setStateNoUpdateNeighbor((World) worldIn, currentPos, stateIn.with(enumCableStateEnumProperty, EnumCableState.EMPTY));
                }
            }

        }

        return stateIn;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        VoxelShape shape = CENTER_SHAPE;

        for (Map.Entry<Direction, EnumProperty<EnumCableState>> directionEnumPropertyEntry : DIRECTION_ENUM_PROPERTY_MAP.entrySet()) {
            EnumCableState enumCableState = state.get(directionEnumPropertyEntry.getValue());
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
            EnumCableState enumCableState = state.get(directionEnumPropertyEntry.getValue());
            if (enumCableState != EnumCableState.EMPTY) {
                shape = VoxelShapes.or(shape, DIRECTION_VOXEL_SHAPE_MAP.get(directionEnumPropertyEntry.getKey()));
            }
        }


        return shape;
    }

}
