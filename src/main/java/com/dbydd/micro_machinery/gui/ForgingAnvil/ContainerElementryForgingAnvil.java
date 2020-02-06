package com.dbydd.micro_machinery.gui.ForgingAnvil;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerElementryForgingAnvil {
    private TileEntityForgingAnvil te;

    public ContainerElementryForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileEntity) {
        this.te = tileEntity;
    }
}
