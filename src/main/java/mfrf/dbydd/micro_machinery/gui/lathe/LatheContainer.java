package mfrf.dbydd.micro_machinery.gui.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class LatheContainer extends ContainerBase {
    private final TileLathe lathe;

    public LatheContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.LATHE_CONTAINER.get(), id);
        this.lathe = (TileLathe) world.getTileEntity(pos);
        ItemStackHandler itemHander = lathe.getItemHander();
        this.addSlot(new SlotItemHandler(itemHander, 0, 28, 54));
        this.addSlot(new SlotItemHandler(itemHander, 1, 132, 54) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        drawInventory(8, 101, playerInventory);
    }

    public TileLathe getLathe() {
        return lathe;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return lathe.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
