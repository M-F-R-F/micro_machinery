package mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileHandGenerator extends MMTileBase implements ITickableBlockEntity {
    private FEContainer container = new FEContainer(0, 40) {
        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }

        @Override
        public int selfAdd() {
            return add(40, false);
        }
    };
    private IntegerContainer progress = new IntegerContainer(0, 40);

    public TileHandGenerator() {
        super(RegisteredBlockEntityTypes.TILE_HAND_GENERATOR.get());
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    @Override
    public void read(CompoundTag compound) {
        container.deserializeNBT(compound.getCompound("energy_container"));
        progress.deserializeNBT(compound.getCompound("progress"));
        super.read(compound);
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        compound.put("energy_container", container.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        Direction direction = Direction.fromAngle(getBlockState().get(BlockHandGenerator.FACING).getHorizontalAngle() + 90f);
        if (cap == CapabilityEnergy.ENERGY && side == direction) {
            return LazyOptional.of(() -> container).cast();
        }
        return super.getCapability(cap, side);
    }

    public void OnActivated(Direction outPutDirection) {
        if (!world.isRemote() && progress.atMinValue()) {
            container.selfAdd();
            if (outPutDirection != null) {
                container = pushEnergyToDirection(outPutDirection, container);
                progress.selfAdd();
                markDirty2();
            }
            markDirty2();
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (!progress.atMinValue()) {
                progress.selfAdd();
                markDirty2();
            }

            if (progress.atMaxValue()) {
                progress = new IntegerContainer(0, 40);
                markDirty2();
            }
        }
    }
}
