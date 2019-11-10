package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerElementaryKlin extends ContainerBase {

    private ItemStackHandler items = new ItemStackHandler(4);

    public ContainerElementaryKlin(EntityPlayer player) {
        super();
        //Add the fuxking slot.
        this.addSlotToContainer(new SlotItemHandler(items, 0, 40, 24));
        this.addSlotToContainer(new SlotItemHandler(items, 1, 40, 50));
        this.addSlotToContainer(new SlotItemHandler(items, 2, 80, 50));
        this.addSlotToContainer(new SlotItemHandler(items, 3, 120, 50));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return null;
    }
}
