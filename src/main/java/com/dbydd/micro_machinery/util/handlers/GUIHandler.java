package com.dbydd.micro_machinery.util.handlers;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.ForgingAnvil.ContainerElementryForgingAnvil;
import com.dbydd.micro_machinery.gui.ForgingAnvil.GUIElementryForgingAnvil;
import com.dbydd.micro_machinery.gui.Klin.ContainerElementaryKlin;
import com.dbydd.micro_machinery.gui.Klin.GuiElementaryKlin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_Klin)
            return new ContainerElementaryKlin(player, (TileEntityKlin) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_ForgingAnvil)
            return new ContainerElementryForgingAnvil(player, (TileEntityForgingAnvil) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_Klin)
            return new GuiElementaryKlin(player, (TileEntityKlin) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_ForgingAnvil)
            return new GUIElementryForgingAnvil(player, (TileEntityForgingAnvil) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
