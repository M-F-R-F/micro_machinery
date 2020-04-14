package com.dbydd.micro_machinery.gui.firegenerator;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFireGenerator extends ContainerBase {
    private final int energyCapacity;
    private int energyStored = 0;
    private int maxBurnTime = 0;
    private int currentBurnTime = 0;
    private TileEntityFireGenerator tileentity;

    public ContainerFireGenerator(EntityPlayer player, TileEntityFireGenerator tileentity) {
        this.tileentity = tileentity;
        this.player = player;
        this.energyCapacity = tileentity.getMaxEnergyCapacity();

        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.addSlotToContainer(new SlotItemHandler(handler, 0, 47, 40));
        this.drawInventory(8, 96);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener iContainerListener : this.listeners) {

            if (this.energyStored != this.tileentity.getField(0))
                iContainerListener.sendWindowProperty(this, 0, this.tileentity.getField(0));
            if (this.maxBurnTime != this.tileentity.getField(1))
                iContainerListener.sendWindowProperty(this, 1, this.tileentity.getField(1));
            if (this.currentBurnTime != this.tileentity.getField(2))
                iContainerListener.sendWindowProperty(this, 2, this.tileentity.getField(2));
        }

        this.energyStored = this.tileentity.getField(0);
        this.maxBurnTime = this.tileentity.getField(1);
        this.currentBurnTime = this.tileentity.getField(2);

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileentity.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id, data);
    }
}
