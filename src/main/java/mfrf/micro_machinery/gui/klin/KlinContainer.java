package mfrf.micro_machinery.gui.klin;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.items.MMCastBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class KlinContainer extends ContainerBase {
    private final IIntArray intArray;
    private final TileKlin klin;

    public KlinContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, IIntArray intArray) {
        super(RegisteredContainerTypes.KLINCONTAINER.get(), id);
        this.intArray = intArray;
        trackIntArray(this.intArray);
        this.klin = (TileKlin) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = klin.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlot(new SlotItemHandler(itemHandler, 3, 120, 50){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new SlotItemHandler(itemHandler, 4, 120, 24){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem() instanceof MMCastBase;
            }
        });
        drawInventory(0, 84, playerInventory);
    }

    public TileKlin getKlin() {
        return klin;
    }

    public IIntArray getIntArray() {
        return intArray;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return this.klin.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

}
