package com.dbydd.micro_machinery.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public abstract class ContainerBase extends Container {
    /**
     * Determines whether supplied player can use this container
     *
     * @param playerIn
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
