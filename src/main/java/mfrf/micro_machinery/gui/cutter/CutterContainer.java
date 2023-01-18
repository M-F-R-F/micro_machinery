package mfrf.micro_machinery.gui.cutter;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.cutter.TileCutter;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.items.SawBladeBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class CutterContainer extends ContainerBase {
    private final TileCutter tileEntity;

    public CutterContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.CUTTER_CONTAINER.get(), id);
        this.tileEntity = (TileCutter) world.getBlockEntity(pos);
        ItemStackHandler itemHandler = tileEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 40));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 101, 40));
        this.addSlot(new SlotItemHandler(tileEntity.getSawBladeHandler(), 0, 125, 40) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem().getItem() instanceof SawBladeBase;
            }
        });
        drawInventory(7, 96, playerInventory);
    }

    public TileCutter getBlockEntity() {
        return tileEntity;
    }

    @Override
    public boolean canInteractWith(Player playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(Player p_82846_1_, int p_82846_2_) {
        return ItemStack.EMPTY;
    }
}
