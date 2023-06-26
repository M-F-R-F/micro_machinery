package org.mfrf.micro_machienry.gui.klin;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.items.MMCastBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class KlinContainer extends ContainerBase {
    private final IIntArray intArray;
    private final TileKlin klin;

    public KlinContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, IIntArray intArray) {
        super(RegisteredContainerTypes.KLINCONTAINER.get(), id);
        this.intArray = intArray;
        trackIntArray(this.intArray);
        this.klin = (TileKlin) world.getTileEntity(pos);
        ItemStackHandler itemHandler = klin.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 3, 120, 50){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(itemHandler, 4, 120, 24){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem() instanceof MMCastBase;
            }
        });
        drawInventory(0, 84, playerInventory);
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
