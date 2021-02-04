package mfrf.dbydd.micro_machinery.gui.electrolysis;

import mfrf.dbydd.micro_machinery.blocks.machines.electrolysis.TileElectrolysis;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolysisContainer extends ContainerBase {
    private final TileElectrolysis electrolysis;

    public ElectrolysisContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(Registered_ContainerTypes.ELECTROLYSIS_CONTAINER.get(), id);
        this.electrolysis = ((TileElectrolysis) world.getTileEntity(pos));
        ItemStackHandler items = electrolysis.getItems();
        this.addSlot(new SlotItemHandler(items, TileElectrolysis.Slot.INPUT.getIndex(), 47, 34));
        this.addSlot(new SlotItemHandler(items, TileElectrolysis.Slot.OUTPUT.getIndex(), 107, 35));
        drawInventory(0, 84, playerInventory);
    }

    public TileElectrolysis getElectrolysis() {
        return electrolysis;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.electrolysis.isUsableByPlayer(playerIn);
    }
}
