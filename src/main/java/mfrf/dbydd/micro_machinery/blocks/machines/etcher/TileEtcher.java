package mfrf.dbydd.micro_machinery.blocks.machines.etcher;

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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEtcher extends MMTileBase implements ITickableTileEntity {
    private FEContainer feContainer = new FEContainer(0, 320000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    };
    private ItemStackHandler input = new ItemStackHandler(1);
    private ItemStackHandler output = new ItemStackHandler(1);
    private IntegerContainer progress = new IntegerContainer();

    public TileEtcher() {
        super(Registered_Tileentitie_Types.TILE_ETCHER.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        feContainer.deserializeNBT(compound.getCompound("energy"));
        input.deserializeNBT(compound.getCompound("input"));
        input.deserializeNBT(compound.getCompound("output"));
        progress.deserializeNBT(compound.getCompound("progress"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("energy", feContainer.serializeNBT());
        compound.put("input", input.serializeNBT());
        compound.put("output", input.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> output).cast();
        }

        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == getBlockState().get(BlockEtcher.FACING).getOpposite() && cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> feContainer).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {

    }
}
