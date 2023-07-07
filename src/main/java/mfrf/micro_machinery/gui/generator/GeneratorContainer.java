package mfrf.micro_machinery.gui.generator;


import mfrf.micro_machinery.block.machines.single_block_machines.generator.TileGenerator;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registry_lists.MMContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GeneratorContainer extends ContainerBase {
    private final TileGenerator generator;
    public GeneratorContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(MMContainerTypes.GENERATOR.get(), id);
        this.generator = (TileGenerator) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = generator.getFuelHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 40));
        drawInventory(0, 96, Container);
    }

    public TileGenerator getGenerator() {
        return generator;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.generator.isUsableByPlayer(playerIn);
    }


}
