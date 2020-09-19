package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEnergyCable extends MMTileBase implements ITickableTileEntity, IEnergyStorage {
    private int currentEnergy = 0;

    public TileEnergyCable() {
        super(Registered_Tileentitie_Types.TILE_ENERGY_CABLE.get());
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public void read(CompoundNBT compound) {
        currentEnergy = compound.getInt("current_energy");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("current_energy", currentEnergy);
        return super.write(compound);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            solveCable();
            solveOutput();
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(cap == CapabilityEnergy.ENERGY){
            return LazyOptional.of(()->this).cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if(cap == CapabilityEnergy.ENERGY){
            return LazyOptional.of(()->this).cast();
        }
        return super.getCapability(cap, side);
    }

    private ArrayList<Direction> getStateSide(EnumCableState state) {
        ArrayList<Direction> list = new ArrayList<>();
        BlockState blockState = getBlockState();
        for (Direction direction : Direction.values()) {
            if (blockState.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction)) == state) {
                list.add(direction);
            }
        }
        return list;
    }

    private void solveCable() {
        ArrayList<Direction> cableSide = getStateSide(EnumCableState.CABLE);
        if (!cableSide.isEmpty()) {
            int i = currentEnergy / cableSide.size();
            int sum = 0;
            for (Direction direction : cableSide) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    int currentEnergy = tileEnergyCable.getCurrentEnergy();
                    if (currentEnergy < this.currentEnergy) {
                        int difference = (this.currentEnergy - currentEnergy)/2;
                        sum += tileEnergyCable.receiveEnergy(Math.min(i, difference), false);
                    }
                }
            }
            this.currentEnergy -= sum;
            markDirty();
        }
    }

    private void solveOutput() {
        ArrayList<Direction> outputSide = getStateSide(EnumCableState.CONNECT);
        if (!outputSide.isEmpty()) {
            int size = outputSide.size();
            int i = currentEnergy / size;
            AtomicInteger sum = new AtomicInteger();
            for (Direction direction : outputSide) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity != null) {
                    LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite());
                    capability.ifPresent(iEnergyStorage -> {
                        if (iEnergyStorage.canReceive()) {
                            int difference = iEnergyStorage.getMaxEnergyStored() - iEnergyStorage.getEnergyStored();
                            if (difference > 0) {
                                sum.addAndGet(iEnergyStorage.receiveEnergy(Math.min(difference, i), false));
                            }
                        }
                    });
                }
            }
            currentEnergy -= sum.get();
            markDirty();
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int transfer = getTransfer();
        if (simulate) {
            if (currentEnergy + maxReceive <= transfer) {
                return maxReceive;
            } else {
                return transfer - currentEnergy;
            }
        } else {
            if (currentEnergy + maxReceive <= transfer) {
                currentEnergy += maxReceive;
                markDirty();
                return maxReceive;
            } else {
                int returnValue = transfer - currentEnergy;
                currentEnergy = transfer;
                markDirty();
                return returnValue;
            }
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (simulate) {
            if (currentEnergy - maxExtract >= 0) {
                return maxExtract;
            } else {
                return currentEnergy;
            }
        } else {
            if (currentEnergy - maxExtract >= 0) {
                currentEnergy -= maxExtract;
                markDirty();
                return maxExtract;
            } else {
                int currentEnergy = this.currentEnergy;
                this.currentEnergy = 0;
                markDirty();
                return currentEnergy;
            }
        }
    }

    @Override
    public int getEnergyStored() {
        return currentEnergy;
    }

    @Override
    public int getMaxEnergyStored() {
        return getTransfer();
    }

    @Override
    public boolean canExtract() {
        return currentEnergy > 0;
    }

    @Override
    public boolean canReceive() {
        return currentEnergy < getTransfer();
    }

    private int getTransfer() {
        return getBlockState().get(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY).getTransfer();
    }
}
