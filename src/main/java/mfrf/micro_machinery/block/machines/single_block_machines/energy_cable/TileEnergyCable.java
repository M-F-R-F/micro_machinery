package mfrf.micro_machinery.block.machines.single_block_machines.energy_cable;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.enums.EnumCableState;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEnergyCable extends MMTileBase implements IEnergyStorage {
    private int currentEnergy = 0;

    public TileEnergyCable(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_ENERGY_CABLE.get(), pos, state);
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public void load(CompoundTag compound) {
        currentEnergy = compound.getInt("current_energy");
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("current_energy", currentEnergy);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileEnergyCable tileEnergyCable) {
            if (world.getGameTime() % (Config.HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE.get() * 0.9) == 0) {
                tileEnergyCable.solveCable();
            }

            if (world.getGameTime() % (Config.HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE.get() * 1.1) == 0) {
                tileEnergyCable.solveOutput();
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == ForgeCapabilities.ENERGY) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap, side);
    }

    private ArrayList<Direction> getStateSide(EnumCableState state) {
        ArrayList<Direction> list = new ArrayList<>();
        BlockState blockState = getBlockState();
        for (Direction direction : Direction.values()) {
            if (blockState.getValue(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction)) == state) {
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
                BlockEntity tileEntity = level.getBlockEntity(getBlockPos().relative(direction));
                if (tileEntity instanceof TileEnergyCable tileEnergyCable) {
                    int currentEnergy = tileEnergyCable.getCurrentEnergy();
                    if (currentEnergy < this.currentEnergy) {
                        int difference = (this.currentEnergy - currentEnergy) / 2;
                        sum += tileEnergyCable.receiveEnergy(Math.min(i, difference), false);
                    }
                }
            }
            this.currentEnergy -= sum;
            setChanged();
        }
    }

    private void solveOutput() {
        ArrayList<Direction> outputSide = getStateSide(EnumCableState.CONNECT);
        if (!outputSide.isEmpty()) {
            int size = outputSide.size();
            int i = currentEnergy / size;
            AtomicInteger sum = new AtomicInteger();
            for (Direction direction : outputSide) {
                BlockEntity tileEntity = level.getBlockEntity(getBlockPos().relative(direction));
                if (tileEntity != null) {
                    LazyOptional<IEnergyStorage> capability = tileEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite());
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
            int i1 = sum.get();
            if (i1 > 0) {
                currentEnergy -= i1;
                setChanged();
            }
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
                setChanged();
                return maxReceive;
            } else {
                int returnValue = transfer - currentEnergy;
                currentEnergy = transfer;
                setChanged();
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
                setChanged();
                return maxExtract;
            } else {
                int currentEnergy = this.currentEnergy;
                this.currentEnergy = 0;
                setChanged();
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
        return getBlockState().getValue(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY).getTransfer();
    }
}
