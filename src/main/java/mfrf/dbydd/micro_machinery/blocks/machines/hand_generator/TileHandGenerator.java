package mfrf.dbydd.micro_machinery.blocks.machines.hand_generator;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileHandGenerator extends MMTileBase implements ITickableTileEntity {
    private FEContainer container = new FEContainer(0, 16) {
        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }

        @Override
        public int self_add() {
            return add(4, false);
        }
    };
    private IntegerContainer progress = new IntegerContainer(0, 20);

    public TileHandGenerator() {
        super(Registered_Tileentitie_Types.TILE_HAND_GENERATOR.get());
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    @Override
    public void read(CompoundNBT compound) {
        container.deserializeNBT(compound.getCompound("energy_container"));
        progress.deserializeNBT(compound.getCompound("progress"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energy_container", container.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && side == Direction.fromAngle(getBlockState().get(BlockHandGenerator.FACING).getHorizontalAngle() - 90)) {
            return LazyOptional.of(() -> container).cast();
        }
        return super.getCapability(cap, side);
    }

    public void OnActivated(Direction outPutDirection) {
        if (!world.isRemote() && progress.atMinValue()) {
            container.self_add();
            if (outPutDirection != null) {
                container = pushEnergyToDirection(outPutDirection, container);
                progress.self_add();
                markDirty2();
            }
            markDirty2();
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (!progress.atMinValue()) {
                progress.self_add();
                markDirty2();
            }

            if (progress.atMaxValue()) {
                progress = new IntegerContainer(0, 20);
                markDirty2();
            }
        }
    }
}
