package mfrf.micro_machinery.gui.weld;

import mfrf.micro_machinery.blocks.machines.single_block_machines.weld.TileWeld;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class WeldContainer extends ContainerBase {
    private final TileWeld weld;

    public WeldContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(RegisteredContainerTypes.WELD_CONTAINER.get(), id);
        this.weld = (TileWeld) world.getBlockEntity(pos);
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
        drawInventory(8, 96, Container);
    }

    public TileWeld getWeld() {
        return weld;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return weld.isUsableByPlayer(playerIn);
    }


}
