package mfrf.micro_machinery.gui;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nullable;

public abstract class ContainerBase extends AbstractContainerMenu {

    protected ContainerBase(@Nullable MenuType<?> type, int id) {
        super(type, id);
    }


    protected void drawInventory(int x, int y, Container inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, y + 59));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, 9 + i * 9 + j, 8 + j * 18, y + i * 18));
            }
        }
    }

}
