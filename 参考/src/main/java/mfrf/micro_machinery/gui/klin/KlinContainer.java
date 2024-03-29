package mfrf.micro_machinery.gui.klin;

import mfrf.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.items.MMCastBase;
import mfrf.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class KlinContainer extends ContainerBase {
    private final DataSlot intArray;
    private final TileKlin klin;

    public KlinContainer(int id, Inventory Container, BlockPos pos, Level world, DataSlot intArray) {
        super(RegisteredContainerTypes.KLINCONTAINER.get(), id);
        this.intArray = intArray;//todo fix data slot
        addDataSlot(this.intArray);
        this.klin = (TileKlin) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = klin.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 3, 120, 50) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(itemHandler, 4, 120, 24) {
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return stack.getItem() instanceof MMCastBase;
            }
        });
        drawInventory(0, 84, Container);
    }

    public TileKlin getKlin() {
        return klin;
    }

    public DataSlot getIntArray() {
        return intArray;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.klin.isUsableByPlayer(playerIn);
    }


}
