package mfrf.dbydd.micro_machinery.blocks.machines.weld;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileWeld extends MMTileBase implements ITickableTileEntity, INamedContainerProvider {
    private ItemStackHandler input = new ItemStackHandler(6);
    private ItemStackHandler output = new ItemStackHandler(1);
    private IntegerContainer progress = new IntegerContainer();
    private FEContainer feContainer = new FEContainer(0, 80000) {
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
            return this.minus(80, false);
        }
    };
    private ResourceLocation recipe = null;


    public TileWeld() {
        super(RegisteredTileEntityTypes.TILE_WELD.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {

        }
    }

    @Override
    public void read(CompoundNBT compound) {
        input.deserializeNBT(compound.getCompound("input"));
        output.deserializeNBT(compound.getCompound("output"));
        progress.deserializeNBT(compound.getCompound("progress"));
        feContainer.deserializeNBT(compound.getCompound("fe_container"));
        if (compound.contains("recipe")) {
            recipe = ResourceLocation.tryCreate(compound.getString("recipe"));
        }
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("input", input.serializeNBT());
        write.put("output", output.serializeNBT());
        write.put("progress", progress.serializeNBT());
        write.put("fe_container", feContainer.serializeNBT());
        if (recipe != null) {
            write.putString("recipe", recipe.toString());
        }
        return write;
    }

    public ItemStackHandler getInput() {
        return input;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui.name.weld");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }
}
