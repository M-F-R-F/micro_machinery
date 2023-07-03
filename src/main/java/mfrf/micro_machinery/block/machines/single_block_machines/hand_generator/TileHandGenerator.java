package mfrf.micro_machinery.block.machines.single_block_machines.hand_generator;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileHandGenerator extends MMTileBase {
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

    public TileHandGenerator(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_HAND_GENERATOR.get(), pos, state);
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    @Override
    public void load(CompoundTag compound) {
        container.deserializeNBT(compound.getCompound("energy_container"));
        progress.deserializeNBT(compound.getCompound("progress"));
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("energy_container", container.serializeNBT());
        pTag.put("progress", progress.serializeNBT());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        Direction direction = Direction.fromYRot(getBlockState().getValue(BlockHandGenerator.FACING).toYRot() + 90f);
        if (cap == ForgeCapabilities.ENERGY && side == direction) {
            return LazyOptional.of(() -> container).cast();
        }
        return super.getCapability(cap, side);
    }

    public void OnActivated(Direction outPutDirection) {
        if (!world.isClientSide() && progress.atMinValue()) {
            container.selfAdd();
            if (outPutDirection != null) {
                container = pushEnergyToDirection(outPutDirection, container);
                progress.selfAdd();
                markDirty2();
            }
            markDirty2();
        }
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileHandGenerator generator) {
            if (!generator.progress.atMinValue()) {
                generator.progress.selfAdd();
                generator.markDirty2();
            }

            if (generator.progress.atMaxValue()) {
                generator.progress = new IntegerContainer(0, 40);
                generator.markDirty2();
            }
        }
    }
}
