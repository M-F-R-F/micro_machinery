package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerElementaryKlin extends ContainerBase {
    private TileEntityKlin tileentity;
    private int melttime, currentmelttimeTime, burnTime, currentBurnTime;

    public ContainerElementaryKlin(EntityPlayer player, TileEntityKlin tileEntity) {
        super();

        this.tileentity = tileEntity;
        IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//        Add the fuxking slot.
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 3, 120, 50));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 143));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener iContainerListener : this.listeners) {

            if (this.melttime != this.tileentity.getField(0))
                iContainerListener.sendWindowProperty(this, 2, this.tileentity.getField(0));
            if (this.burnTime != this.tileentity.getField(2))
                iContainerListener.sendWindowProperty(this, 0, this.tileentity.getField(2));
            if (this.currentBurnTime != this.tileentity.getField(3))
                iContainerListener.sendWindowProperty(this, 1, this.tileentity.getField(3));
            if (this.currentmelttimeTime != this.tileentity.getField(1))
                iContainerListener.sendWindowProperty(this, 3, this.tileentity.getField(1));
        }

        this.melttime = this.tileentity.getField(0);
        this.burnTime = this.tileentity.getField(2);
        this.currentBurnTime = this.tileentity.getField(3);
        this.currentmelttimeTime = this.tileentity.getField(1);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id, data);
    }

}
