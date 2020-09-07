package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.MMBlockBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockEnergyCable extends MMBlockBase {
    public static final EnumProperty<EnumCableMaterial> CABLE_MATERIAL_ENUM_PROPERTY = EnumProperty.create("material", EnumCableMaterial.class);
    public static final Map<Direction, EnumProperty<EnumCableState>> DIRECTION_ENUM_PROPERTY_MAP = new HashMap<>();
    public static final EnumProperty<EnumCableState> UP_ISCONNECTED = EnumProperty.create("up_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> DOWN_ISCONNECTED = EnumProperty.create("down_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> SOUTH_ISCONNECTED = EnumProperty.create("south_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> NORTH_ISCONNECTED = EnumProperty.create("north_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> WEST_ISCONNECTED = EnumProperty.create("west_connect", EnumCableState.class);
    public static final EnumProperty<EnumCableState> EAST_ISCONNECTED = EnumProperty.create("east_connect", EnumCableState.class);

    public BlockEnergyCable(Properties properties, String name, EnumCableMaterial material) {
        super(properties, name);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.UP, UP_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.DOWN, DOWN_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.SOUTH, SOUTH_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.NORTH, NORTH_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.WEST, WEST_ISCONNECTED);
        DIRECTION_ENUM_PROPERTY_MAP.put(Direction.EAST, EAST_ISCONNECTED);
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
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState defaultState = this.getDefaultState();
        for (Direction direction : Direction.values()) {
            BlockPos offset = pos.offset(direction);
            if (world.getBlockState(offset).getBlock() == this) {
                defaultState.with(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CABLE);
            } else {
                TileEntity tileEntity = world.getTileEntity(offset);
                if (tileEntity != null && tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).isPresent()) {
                    defaultState.with(DIRECTION_ENUM_PROPERTY_MAP.get(direction), EnumCableState.CONNECT);
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

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEnergyCable) {
                TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                tileEnergyCable.notifyStateUpdate(state, worldIn);
            }
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEnergyCable) {
                TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                tileEnergyCable.notifyBreak(state, worldIn);
            }
        }
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        if (!world.isRemote() && world instanceof World) {
            boolean changed = false;
            TileEntity tileEntityNeighbor = world.getTileEntity(neighbor);
            Direction facingFromVector = Direction.getFacingFromVector(neighbor.getX() - pos.getX(), neighbor.getY() - pos.getY(), neighbor.getZ() - pos.getZ());
            EnumProperty<EnumCableState> enumCableStateEnumProperty = DIRECTION_ENUM_PROPERTY_MAP.get(facingFromVector);

            if (tileEntityNeighbor instanceof TileEnergyCable) {
                if (state.get(enumCableStateEnumProperty) != EnumCableState.CABLE) {
                    setStateNoUpdateNeighbor((World) world, pos, state.with(enumCableStateEnumProperty, EnumCableState.CABLE));
                    changed = true;
                }
            } else if (tileEntityNeighbor != null) {
                if (tileEntityNeighbor.getCapability(CapabilityEnergy.ENERGY, facingFromVector.getOpposite()).isPresent()) {
                    if (state.get(enumCableStateEnumProperty) != EnumCableState.CONNECT) {
                        setStateNoUpdateNeighbor((World) world, pos, state.with(enumCableStateEnumProperty, EnumCableState.CONNECT));
                        changed = true;
                    }
                }
            } else {
                if (state.get(enumCableStateEnumProperty) != EnumCableState.EMPTY) {
                    setStateNoUpdateNeighbor((World) world, pos, state.with(enumCableStateEnumProperty, EnumCableState.EMPTY));
                    changed = true;
                }
            }

            if (changed) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    tileEnergyCable.notifyStateUpdate(state, (World) world);
                }
            }
        }
    }

    private boolean setStateNoUpdateNeighbor(World world, BlockPos pos, BlockState state) {
        return world.setBlockState(pos, state, 22);
    }

}
