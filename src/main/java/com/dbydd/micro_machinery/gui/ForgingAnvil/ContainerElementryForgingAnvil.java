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
    private TileEntityForgingAnvil tileentity;

    public ContainerElementryForgingAnvil(EntityPlayer player, TileEntityForgingAnvil tileEntity) {
        this.tileentity = tileEntity;
        this.player = player;
        IItemHandler input = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        IItemHandler output = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

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
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUsableByPlayer(playerIn);
    }
}
