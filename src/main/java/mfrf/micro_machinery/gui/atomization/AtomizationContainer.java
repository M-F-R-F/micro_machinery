package mfrf.micro_machinery.gui.atomization;

import mfrf.micro_machinery.blocks.machines.single_block_machines.atomization.TileAtomization;
import mfrf.micro_machinery.gui.ContainerBase;
import mfrf.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AtomizationContainer extends ContainerBase {

    private final TileAtomization tileEntity;

    public AtomizationContainer(int id, Inventory Container, BlockPos pos, Level world) {
        super(RegisteredContainerTypes.ATOMIZATION_CONTAINER.get(), id);
        this.tileEntity = ((TileAtomization) world.getBlockEntity(pos));
        ItemStackHandler output = tileEntity.getOutput();
        this.addSlot(new SlotItemHandler(output, 0, 120, 46) {

            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });

        drawInventory(8, 97, Container);
    }

    public TileAtomization getBlockEntity() {
        return tileEntity;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }


}
