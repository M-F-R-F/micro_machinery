package mfrf.dbydd.micro_machinery.gui.weld;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
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

public class WeldContainer extends ContainerBase {
    private final TileWeld weld;

    public WeldContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.WELD_CONTAINER.get(), id);
        this.weld = (TileWeld) world.getTileEntity(pos);
        ItemStackHandler input = weld.getInput();
        ItemStackHandler output = weld.getOutput();
        this.addSlot(new SlotItemHandler(input, 0, 26, 32));
        this.addSlot(new SlotItemHandler(input, 1, 44, 32));
        this.addSlot(new SlotItemHandler(input, 2, 62, 32));
        this.addSlot(new SlotItemHandler(input, 3, 26, 50));
        this.addSlot(new SlotItemHandler(input, 4, 44, 50));
        this.addSlot(new SlotItemHandler(input, 5, 62, 50));
        this.addSlot(new SlotItemHandler(output, 0, 116, 40) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        drawInventory(8, 96, playerInventory);
    }

    public TileWeld getWeld() {
        return weld;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return weld.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
