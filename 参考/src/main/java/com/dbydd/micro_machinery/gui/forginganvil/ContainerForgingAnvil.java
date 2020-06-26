package com.dbydd.micro_machinery.gui.forginganvil;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerForgingAnvil extends ContainerBase {
    private int forgetime, level;
    private TileEntityForgingAnvil tileentity;

    public ContainerForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileentity) {
        super();

        this.tileentity = tileentity;
        this.player = player;
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 52, 16));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 132, 16));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 103, 55) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        this.drawInventory(8, 78);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener iContainerListener : this.listeners) {

            if (this.level != this.tileentity.getField("level"))
                iContainerListener.sendWindowProperty(this, 0, this.tileentity.getField("level"));
            if (this.forgetime != this.tileentity.getField("forgetime"))
                iContainerListener.sendWindowProperty(this, 1, this.tileentity.getField("forgetime"));
        }

        this.level = this.tileentity.getField("level");
        this.forgetime = this.tileentity.getField("forgetime");
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
        return tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id, data);
    }
}
