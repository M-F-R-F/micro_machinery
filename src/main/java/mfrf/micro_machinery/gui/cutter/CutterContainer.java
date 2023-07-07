package mfrf.micro_machinery.gui.cutter;

import mfrf.micro_machinery.block.machines.single_block_machines.cutter.TileCutter;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.item.SawBladeBase;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class CutterContainer extends ContainerBase {
    private final TileCutter tileEntity;

    public CutterContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(MMContainerTypes.CUTTER_CONTAINER.get(), id);
        this.tileEntity = (TileCutter) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = tileEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 40));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 101, 40));
        this.addSlot(new SlotItemHandler(tileEntity.getSawBladeHandler(), 0, 125, 40) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof SawBladeBase;
            }
        });
        drawInventory(7, 96, Container);
    }

    public TileCutter getBlockEntity() {
        return tileEntity;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

}
