package com.dbydd.micro_machinery.gui.ForgingAnvil;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.gui.ContainerBase;
import com.dbydd.micro_machinery.items.tools.ToolHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerElementryForgingAnvil extends ContainerBase {
    private TileEntityForgingAnvil te;

    public ContainerElementryForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileEntity) {
        this.te = tileEntity;
        this.player = player;
        IItemHandler input = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        IItemHandler output = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

        this.addSlotToContainer(new SlotItemHandler(input, 0, 52, 16));
        this.addSlotToContainer(new SlotItemHandler(input, 1, 132, 16) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem() instanceof ToolHammer;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(output, 0, 103, 55));

        this.drawInventory(8, 78);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
