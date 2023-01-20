package mfrf.micro_machinery.gui.generator;

import mfrf.micro_machinery.blocks.machines.single_block_machines.generator.TileGenerator;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GeneratorContainer extends ContainerBase {
    private final TileGenerator generator;
    public GeneratorContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.GENERATOR.get(), id);
        this.generator = (TileGenerator) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = generator.getFuelHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 40));
        drawInventory(0, 96, playerInventory);
    }

    public TileGenerator getGenerator() {
        return generator;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return this.generator.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
