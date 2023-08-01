package mfrf.micro_machinery.gui.klin;


import mfrf.micro_machinery.block.machines.single_block_machines.klin.TileKlin;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.item.MMCastBase;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class KlinContainer extends ContainerBase {
    private final TileKlin klin;

    public KlinContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(MMContainerTypes.KLINCONTAINER.get(), id);
        this.klin = (TileKlin) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = klin.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 3, 120, 50) {
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return false;
            }

            @Override
            public boolean isActive() {
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

    @Override
    public boolean stillValid(Player playerIn) {
        return this.klin.isUsableByPlayer(playerIn);
    }


}
