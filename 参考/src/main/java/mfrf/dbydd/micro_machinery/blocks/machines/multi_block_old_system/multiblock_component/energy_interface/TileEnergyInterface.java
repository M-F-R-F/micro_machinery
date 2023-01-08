package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.energy_interface;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileEnergyInterface extends TilePlaceHolder implements IEnergyStorage, ITickableTileEntity {
    public boolean canReceive;
    public boolean canExtract;

    public TileEnergyInterface() {
        super(RegisteredTileEntityTypes.TILE_ENERGY_INTERFACE.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        canReceive = compound.getBoolean("can_receive");
        canExtract = compound.getBoolean("can_extract");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("can_receive", canReceive);
        compound.putBoolean("can_extract", canExtract);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && side == world.getBlockState(pos).get(BlockHolderEnergyInterfaceInput.FACING)) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return getMainPartEnergyCap().receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return getMainPartEnergyCap().extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return getMainPartEnergyCap().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return getMainPartEnergyCap().getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return canExtract && getMainPartEnergyCap().canExtract();
    }

    @Override
    public boolean canReceive() {
        return canReceive && getMainPartEnergyCap().canReceive();
    }

    private IEnergyStorage getMainPartEnergyCap() {
        return getMainPartCapability(CapabilityEnergy.ENERGY).orElse(new IEnergyStorage() {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                return 0;
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                return 0;
            }

            @Override
            public int getEnergyStored() {
                return 0;
            }

            @Override
            public int getMaxEnergyStored() {
                return 0;
            }

            @Override
            public boolean canExtract() {
                return false;
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        });
    }

    @Override
    public void tick() {
        if (world.isRemote()) {
            if (canOutPut()) {
                getFacingEnergyCapability().ifPresent(iEnergyStorage -> {
                    int i = iEnergyStorage.receiveEnergy(getEnergyStored(), true);
                    iEnergyStorage.receiveEnergy(extractEnergy(i, false), false);
                    markDirty();
                });
            }
        }
    }

    private LazyOptional<IEnergyStorage> getFacingEnergyCapability() {
        TileEntity tileEntity = world.getTileEntity(pos.offset(world.getBlockState(pos).get(BlockHolderEnergyInterfaceInput.FACING)));
        if (tileEntity != null) {
            return tileEntity.getCapability(CapabilityEnergy.ENERGY);
        }
        return LazyOptional.empty();
    }

    private boolean canOutPut() {
        AtomicBoolean returnValue = new AtomicBoolean(false);
        if (canExtract) {
            getFacingEnergyCapability().ifPresent(iEnergyStorage -> returnValue.set(iEnergyStorage.canReceive()));
        }
        return returnValue.get();
    }
}
