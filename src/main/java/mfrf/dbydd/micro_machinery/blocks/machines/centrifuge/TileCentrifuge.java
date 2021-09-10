package mfrf.dbydd.micro_machinery.blocks.machines.centrifuge;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.centrifuge.CentrifugeContainer;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileCentrifuge extends MMTileBase implements ITickableTileEntity, INamedContainerProvider {
    public FEContainer feContainer = new FEContainer(0, 40000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }

        @Override
        public int selfSubtract() {
            int current = getCurrent();
            if (current >= 128) {
                minus(128, false);
                markDirty();
                return current;
            } else {
                return -1;
            }
        }
    };
    public ItemStackHandler input = new ItemStackHandler(1);
    public ItemStackHandler output = new ItemStackHandler(5);

    public TileCentrifuge() {
        super(RegisteredTileEntityTypes.TILE_CENTRIFUGE.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = super.serializeNBT();
        compoundNBT.put("fe_container", feContainer.serializeNBT());
        compoundNBT.put("input", input.serializeNBT());
        compoundNBT.put("output", output.serializeNBT());
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        feContainer.deserializeNBT(nbt.getCompound("fe_container"));
        input.deserializeNBT(nbt.getCompound("input"));
        output.deserializeNBT(nbt.getCompound("output"));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && isBackDirection(side)) {
            return LazyOptional.of(() -> feContainer).cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("centrifuge");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new CentrifugeContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }
}