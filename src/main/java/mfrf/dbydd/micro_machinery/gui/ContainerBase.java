package mfrf.dbydd.micro_machinery.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public abstract class ContainerBase extends Container {

    protected ContainerBase(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public abstract boolean canInteractWith(PlayerEntity playerIn);

    protected void drawInventory(int x, int y, IInventory inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, y + 59));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, 9 + i * 9 + j, 8 + j * 18, y + i * 18));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity p_82846_1_, int p_82846_2_) {
     return ItemStack.EMPTY;
    }
}
