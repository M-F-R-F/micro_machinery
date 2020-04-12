package com.dbydd.micro_machinery.util.handlers;

import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.firegenerator.ContainerFireGenerator;
import com.dbydd.micro_machinery.gui.firegenerator.GuiFireGenerator;
import com.dbydd.micro_machinery.gui.forginganvil.ContainerForgingAnvil;
import com.dbydd.micro_machinery.gui.forginganvil.GuiForgingAnvil;
import com.dbydd.micro_machinery.gui.klin.ContainerKlin;
import com.dbydd.micro_machinery.gui.klin.GuiKlin;
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
            return new ContainerKlin(player, (TileEntityKlin) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_ForgingAnvil)
            return new ContainerForgingAnvil(player, (TileEntityForgingAnvil) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_FireGenerator)
            return new ContainerFireGenerator(player, (TileEntityFireGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_Klin)
            return new GuiKlin(player, (TileEntityKlin) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_ForgingAnvil)
            return new GuiForgingAnvil(player, (TileEntityForgingAnvil) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_FireGenerator)
            return new GuiFireGenerator(player, (TileEntityFireGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
