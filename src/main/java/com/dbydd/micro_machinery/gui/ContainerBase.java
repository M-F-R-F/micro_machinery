package com.dbydd.micro_machinery.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public abstract class ContainerBase extends Container {

    @Override
    public abstract boolean canInteractWith(EntityPlayer playerIn);

}
