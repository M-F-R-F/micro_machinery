package com.dbydd.micro_machinery.util.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import javax.swing.plaf.basic.BasicComboBoxUI;

public class ContainerElementaryKlin extends Container {

    public ContainerElementaryKlin(EntityPlayer player) {
        super();
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
