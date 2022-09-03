package mfrf.dbydd.micro_machinery.gui.atomization;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
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

public class AtomizationContainer extends ContainerBase {

    private final TileAtomization tileEntity;

    public AtomizationContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.ATOMIZATION_CONTAINER.get(), id);
        this.tileEntity = ((TileAtomization) world.getTileEntity(pos));
        ItemStackHandler output = tileEntity.getOutput();
        this.addSlot(new SlotItemHandler(output, 0, 120, 46) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        drawInventory(8, 97, playerInventory);
    }

    public TileAtomization getTileEntity() {
        return tileEntity;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
