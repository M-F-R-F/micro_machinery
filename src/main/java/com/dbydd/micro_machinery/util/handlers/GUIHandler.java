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

    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_Klin)
            return new ContainerElementaryKlin(player, (TileEntityKlin) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_ForgingAnvil)
            return new ContainerElementryForgingAnvil(player, (TileEntityForgingAnvil) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    /**
     * Returns a Container to be displayed to the user. On the client side, this
     * needs to return a instance of GuiScreen On the server side, this needs to
     * return a instance of Container
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
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
