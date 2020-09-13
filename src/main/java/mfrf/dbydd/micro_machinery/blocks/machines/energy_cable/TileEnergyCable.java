package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TileEnergyCable extends MMTileBase implements ITickableTileEntity, IEnergyStorage {
    //    private final Map<Direction, EnumCableState> stateMap = new HashMap<>();
    private EnumCableMaterial material = EnumCableMaterial.NULL;
    private FEContainer container = new FEContainer(0, 0) {
        @Override
        public boolean canExtract() {
            return !atMinValue();
        }

        @Override
        public boolean canReceive() {
            return !atMaxValue();
        }
    };

    public TileEnergyCable() {
        super(Registered_Tileentitie_Types.TILE_ENERGY_CABLE.get());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap) {
        if (cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
//            this.historyValue = container.getCurrent();
            Map<Direction, EnumCableState> stateMap = getStateMap();
            List<Direction> cableSides = stateMap.entrySet().stream().filter(directionEnumCableStateEntry -> directionEnumCableStateEntry.getValue() == EnumCableState.CABLE).map(Map.Entry::getKey).collect(Collectors.toList());
            List<Direction> outPuts = stateMap.entrySet().stream().filter(directionEnumCableStateEntry -> directionEnumCableStateEntry.getValue() == EnumCableState.CONNECT).map(Map.Entry::getKey).collect(Collectors.toList());

            solveDifferenceEquation(cableSides);

            pushEnergy(outPuts);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("container", container.serializeNBT());
        compound.putString("material", material.getName());
//        for (Direction direction : Direction.values()) {
//            compound.putString(direction.toString() + "_state", stateMap.get(direction).name());
//        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        container.deserializeNBT(compound.getCompound("container"));
        material = EnumCableMaterial.fromName(compound.getString("material"));
//        stateMap.clear();
//        for (Direction direction : Direction.values()) {
//            stateMap.put(direction, EnumCableState.valueOf(compound.getString(direction.toString() + "_state")));
//        }
        super.read(compound);
    }

    public FEContainer getContainer() {
        return container;
    }

    public EnumCableMaterial getMaterial() {
        return material;
    }

    public void notifyStateUpdate(BlockState state, World world) {
//        stateMap.clear();
        if (this.material == EnumCableMaterial.NULL) {
            this.material = state.get(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY);
            this.container.setMax(material.getTransfer());
        }

//        for (Direction direction : Direction.values()) {
//            stateMap.put(direction, state.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction)));
//        }

        markDirty();
    }

    private Map<Direction, EnumCableState> getStateMap() {
        BlockState blockState = world.getBlockState(pos);
        Map<Direction, EnumCableState> stateMap = new HashMap<>();
        for (Direction value : Direction.values()) {
            stateMap.put(value, blockState.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(value)));
        }
        return stateMap;
    }

    private void solveDifferenceEquation(List<Direction> list) {
        if (!list.isEmpty() && !this.container.atMaxValue()) {
            int sum = 0;
            for (Direction direction : list) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    if(tileEnergyCable.getEnergyStored() >= tileEnergyCable.material.getTransfer()){
                        //todo fuck it
                        sum+=tileEnergyCable.material.getTransfer();
                    }else {
                        sum+=tileEnergyCable.getEnergyStored();
                    }
                }
            }

                this.container.add(sum / 6 - container.getCurrent(), false);
            markDirty();
        }
    }

    private void pushEnergy(List<Direction> list) {
        if (!list.isEmpty()) {

            int energyEachDirection = container.getCurrent() / list.size();

            int energyCost = list.stream().mapToInt(direction -> {
                AtomicInteger returnValue = new AtomicInteger(0);
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity != null) {
                    LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite());
                    capability.ifPresent(iEnergyStorage -> {
                        if (iEnergyStorage.canReceive()) {
                            returnValue.set(iEnergyStorage.receiveEnergy(energyEachDirection, false));
                        }
                    });
                }
                return returnValue.get();
            }).sum();

            container.add(-energyCost, false);
            markDirty();
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receiveEnergy = container.receiveEnergy(maxReceive, simulate);
        markDirty();
        return receiveEnergy;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractEnergy = container.extractEnergy(maxExtract, simulate);
        markDirty();
        return extractEnergy;
    }

    @Override
    public int getEnergyStored() {
        return container.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return container.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return container.canExtract();
    }

    @Override
    public boolean canReceive() {
        return container.canReceive();
    }
}
