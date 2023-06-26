package org.mfrf.micro_machienry.gui.electrolysis;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.electrolysis.TileElectrolysis;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolysisContainer extends ContainerBase {
    private final TileElectrolysis electrolysis;

    public ElectrolysisContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.ELECTROLYSIS_CONTAINER.get(), id);
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

    @Override
    public ItemStack transferStackInSlot(PlayerEntity p_82846_1_, int p_82846_2_) {
        return ItemStack.EMPTY;
    }
}
