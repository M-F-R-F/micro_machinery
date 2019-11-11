package com.dbydd.micro_machinery.gui.Klin;

import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerElementaryKlin extends ContainerBase {

    private ItemStackHandler items = new ItemStackHandler(5);

    public ContainerElementaryKlin(EntityPlayer player) {
        super();
        //Add the fuxking slot.
        this.addSlotToContainer(new SlotItemHandler(items, 0, 40, 24));
        this.addSlotToContainer(new SlotItemHandler(items, 1, 40, 50));
        this.addSlotToContainer(new SlotItemHandler(items, 2, 80, 50));
        this.addSlotToContainer(new SlotItemHandler(items, 3, 120, 50));
        this.addSlotToContainer(new SlotItemHandler(items, 4, 152, 65));
        for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
    }

        for (int i = 0; i < 9; ++i)
    {
        this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 143));
    }
};
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return null;
    }
}
