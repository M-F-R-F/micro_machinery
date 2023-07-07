package mfrf.micro_machinery.gui.electrolysis;


import mfrf.micro_machinery.block.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolysisContainer extends ContainerBase {
    private final TileElectrolysis electrolysis;

    public ElectrolysisContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(MMContainerTypes.ELECTROLYSIS_CONTAINER.get(), id);
        this.electrolysis = ((TileElectrolysis) world.getBlockEntity(pos));
        ItemStackHandler items = electrolysis.getItems();
        this.addSlot(new SlotItemHandler(items, TileElectrolysis.Slot.INPUT.getIndex(), 47, 34));
        this.addSlot(new SlotItemHandler(items, TileElectrolysis.Slot.OUTPUT.getIndex(), 107, 35));
        drawInventory(0, 84, Container);
    }

    public TileElectrolysis getElectrolysis() {
        return electrolysis;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.electrolysis.isUsableByPlayer(playerIn);
    }

}
