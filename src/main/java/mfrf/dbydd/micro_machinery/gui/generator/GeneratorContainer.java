package mfrf.dbydd.micro_machinery.gui.generator;

import mfrf.dbydd.micro_machinery.blocks.machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GeneratorContainer extends ContainerBase {
    private final TileGenerator generator;
    public GeneratorContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(Registered_ContainerTypes.GENERATOR.get(), id);
        this.generator = (TileGenerator) world.getTileEntity(pos);
        ItemStackHandler itemHandler = generator.getFuelHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 40));
        drawInventory(0, 96, playerInventory);
    }

    public TileGenerator getGenerator() {
        return generator;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.generator.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
