package mfrf.micro_machinery.gui.centrifuge;

import mfrf.micro_machinery.block.machines.single_block_machines.centrifuge.TileCentrifuge;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class CentrifugeContainer extends ContainerBase {
    public TileCentrifuge tileCentrifuge;

    public CentrifugeContainer(int windowId, Inventory inv, BlockPos readBlockPos, Level world) {
        super(MMContainerTypes.CENTRIFUGE_CONTAINER.get(), windowId);
        this.tileCentrifuge = ((TileCentrifuge) world.getBlockEntity(readBlockPos));
        final ItemStackHandler input = tileCentrifuge.getInput();
        final ItemStackHandler output = tileCentrifuge.getOutput();

        this.addSlot(new SlotItemHandler(input, 0, 80, 18) {
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new SlotItemHandler(output, 0, 56, 59) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack);
            }
        });
        this.addSlot(new SlotItemHandler(output, 1, 80, 59) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack);
            }
        });
        this.addSlot(new SlotItemHandler(output, 2, 104, 59) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack);
            }
        });
        this.addSlot(new SlotItemHandler(output, 3, 68, 80) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack);
            }
        });
        this.addSlot(new SlotItemHandler(output, 4, 92, 80) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack);
            }
        });

        drawInventory(8, 105, inv);
    }

    public TileCentrifuge getTileCentrifuge() {
        return tileCentrifuge;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return tileCentrifuge.isUsableByPlayer(playerIn);
    }

}
