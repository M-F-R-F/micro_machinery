package mfrf.micro_machinery.gui.centrifuge;

import mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge.TileCentrifuge;
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

public class CentrifugeContainer extends ContainerBase {
    public TileCentrifuge tileCentrifuge;

    public CentrifugeContainer(int windowId, PlayerInventory inv, BlockPos readBlockPos, World world) {
        super(RegisteredContainerTypes.CENTRIFUGE_CONTAINER.get(), windowId);
        this.tileCentrifuge = ((TileCentrifuge) world.getBlockEntity(readBlockPos));
        final ItemStackHandler input = tileCentrifuge.getInput();
        final ItemStackHandler output = tileCentrifuge.getOutput();

        this.addSlot(new SlotItemHandler(input, 0, 80, 18) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new SlotItemHandler(output, 0, 56, 59) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(output, 1, 80, 59) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(output, 2, 104, 59) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(output, 3, 68, 80) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(output, 4, 92, 80) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        drawInventory(8, 105, inv);
    }

    public TileCentrifuge getTileCentrifuge() {
        return tileCentrifuge;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return tileCentrifuge.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player p_82846_1_, int p_82846_2_) {
        return ItemStack.EMPTY;
    }
}
