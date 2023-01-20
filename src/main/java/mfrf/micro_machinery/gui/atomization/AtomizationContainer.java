package mfrf.micro_machinery.gui.atomization;

import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
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

public class AtomizationContainer extends ContainerBase {

    private final TileAtomization tileEntity;

    public AtomizationContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.ATOMIZATION_CONTAINER.get(), id);
        this.tileEntity = ((TileAtomization) world.getBlockEntity(pos));
        ItemStackHandler output = tileEntity.getOutput();
        this.addSlot(new SlotItemHandler(output, 0, 120, 46) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        drawInventory(8, 97, playerInventory);
    }

    public TileAtomization getBlockEntity() {
        return tileEntity;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
