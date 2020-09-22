package mfrf.dbydd.micro_machinery.blocks.machines.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.gui.lathe.LatheContainer;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class TileLathe extends MMTileBase implements INamedContainerProvider {
    private FEContainer FEContainer = new FEContainer(0, 25600) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    };
    private IntegerContainer wasteMaterialValueConatiner = new IntegerContainer(0, 100);
    private ItemStackHandler itemHander = new ItemStackHandler(2);

    public TileLathe() {
        super(Registered_Tileentitie_Types.TILE_LATHE.get());
    }

    public mfrf.dbydd.micro_machinery.utils.FEContainer getFEContainer() {
        return FEContainer;
    }

    public IntegerContainer getWasteMaterialValueConatiner() {
        return wasteMaterialValueConatiner;
    }

    public ItemStackHandler getItemHander() {
        return itemHander;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fe_container", FEContainer.serializeNBT());
        compound.put("waste_material_container", wasteMaterialValueConatiner.serializeNBT());
        compound.put("item_handler", itemHander.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        FEContainer.deserializeNBT(compound.getCompound("fe_container"));
        wasteMaterialValueConatiner.deserializeNBT(compound.getCompound("waste_material_container"));
        itemHander.deserializeNBT(compound.getCompound("item_handler"));
        super.read(compound);
    }

    @Override
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.LATHE.getNameTextComponent();
    }

    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new LatheContainer(sycID, inventory, this.pos, this.world);
    }

    public enum WasteValueEachButtom {
        DRILLING(5),
        TURNING(10),
        MILLING(7),
        GRINDING(2),
        PLANING(4),
        BORING(3);

        private final int wasteValue;

        WasteValueEachButtom(int wasteValue) {
            this.wasteValue = wasteValue;
        }

        public int getWasteValue() {
            return wasteValue;
        }
    }
}
