package com.dbydd.micro_machinery.blocks.machines.energy_cable;

import com.dbydd.micro_machinery.blocks.MMBlockBase;
import com.dbydd.micro_machinery.enums.EnumCableMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockEnergyCable extends MMBlockBase {
    public static final EnumProperty<EnumCableMaterial> CABLE_MATERIAL_ENUM_PROPERTY = EnumProperty.create("material", EnumCableMaterial.class);
    public static final Map<Direction, BooleanProperty> BOOLEAN_PROPERTY_HASH_MAP = new HashMap<>();
    public static final BooleanProperty UP_ISCONNECTED = BooleanProperty.create("up_connect");
    public static final BooleanProperty DOWN_ISCONNECTED = BooleanProperty.create("down_connect");
    public static final BooleanProperty SOUTH_ISCONNECTED = BooleanProperty.create("south_connect");
    public static final BooleanProperty NORTH_ISCONNECTED = BooleanProperty.create("north_connect");
    public static final BooleanProperty WEST_ISCONNECTED = BooleanProperty.create("west_connect");
    public static final BooleanProperty EAST_ISCONNECTED = BooleanProperty.create("east_connect");

    public BlockEnergyCable(Properties properties, String name, EnumCableMaterial material) {
        super(properties, name);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.UP, UP_ISCONNECTED);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.DOWN, DOWN_ISCONNECTED);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.SOUTH, SOUTH_ISCONNECTED);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.NORTH, NORTH_ISCONNECTED);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.WEST, WEST_ISCONNECTED);
        BOOLEAN_PROPERTY_HASH_MAP.put(Direction.EAST, EAST_ISCONNECTED);
        this.setDefaultState(this.stateContainer.getBaseState().with(CABLE_MATERIAL_ENUM_PROPERTY, material).with(UP_ISCONNECTED, false).with(DOWN_ISCONNECTED, false).with(SOUTH_ISCONNECTED, false
        ).with(NORTH_ISCONNECTED, false).with(WEST_ISCONNECTED, false).with(EAST_ISCONNECTED, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockState state = this.getDefaultState();
            BlockPos pos = context.getPos();
            for (Direction direction : Direction.values()) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity != null) {
                    LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite());
                    if (capability.isPresent()) {
                        state.with(BOOLEAN_PROPERTY_HASH_MAP.get(direction), true);
                    }
                }
            }
        return state;
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
        return null;
        //todo
    }
}
