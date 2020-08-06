package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.gui.ContainerBase;
import com.dbydd.micro_machinery.registery_lists.Registered_ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class KlinContainer extends ContainerBase {
    private final IIntArray intArray;
    private final TileKlin klin;

    public KlinContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, IIntArray intArray) {
        super(Registered_ContainerTypes.KLINCONTAINER.get(), id);
        this.intArray = intArray;
        trackIntArray(this.intArray);
        this.klin = (TileKlin) world.getTileEntity(pos);
        ItemStackHandler itemHandler = klin.getItemHandler();
        drawInventory(0, 84, playerInventory);
        this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 3, 120, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 4, 120, 24));
    }

    public TileKlin getKlin() {
        return klin;
    }

    public IIntArray getIntArray() {
        return intArray;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.klin.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }

}
