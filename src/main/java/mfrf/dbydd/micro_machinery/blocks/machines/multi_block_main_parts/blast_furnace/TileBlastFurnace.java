package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class TileBlastFurnace extends MMMultiBlockTileMainPartBase implements INamedContainerProvider, ITickableTileEntity {

    private ItemStackHandler ItemHandler = new ItemStackHandler(3);
    private IntegerContainer progressContainer = new IntegerContainer();
    private IntegerContainer heatHandler = new IntegerContainer();

    public TileBlastFurnace() {
        super(Registered_Tileentitie_Types.TILE_BLAST_FURNACE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("items",ItemHandler.serializeNBT());
        compound.put("progress",progressContainer.serializeNBT());
        compound.put("heat",heatHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ItemHandler.deserializeNBT(compound.getCompound("items"));
        progressContainer.deserializeNBT(compound.getCompound("progress"));
        heatHandler.deserializeNBT(compound.getCompound("heat"));
    }

    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.BLAST_FURNACE.getNameTextComponent();
    }

    @Override
    public void tick() {

    }

}
