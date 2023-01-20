package mfrf.micro_machinery.gui.blast_furnace;

import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace.TileBlastFurnace;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class BlastFurnaceContainer extends ContainerBase {
    private TileBlastFurnace furnace;

    public BlastFurnaceContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.BLAST_FURNACE_CONTAINER.get(), id);
        this.furnace = (TileBlastFurnace) world.getBlockEntity(pos);

        ItemStackHandler itemHandler = furnace.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, TileBlastFurnace.slot.FUEL.getNum(), 56, 53));
        this.addSlot(new SlotItemHandler(itemHandler, TileBlastFurnace.slot.INPUT.getNum(), 56, 17));
        this.addSlot(new SlotItemHandler(itemHandler, TileBlastFurnace.slot.OUTPUT.getNum(), 116, 35) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        drawInventory(7, 84, playerInventory);
    }

    public TileBlastFurnace getFurnace() {
        return furnace;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return furnace.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
