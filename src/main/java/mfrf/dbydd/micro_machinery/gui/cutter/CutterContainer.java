package mfrf.dbydd.micro_machinery.gui.cutter;

import mfrf.dbydd.micro_machinery.blocks.machines.cutter.TileCutter;
import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.items.SawBladeBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class CutterContainer extends ContainerBase {
    private final TileCutter tileEntity;

    public CutterContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(RegisteredContainerTypes.CUTTER_CONTAINER.get(), id);
        this.tileEntity = (TileCutter) world.getTileEntity(pos);
        ItemStackHandler itemHandler = tileEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 47, 70));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 101, 40));
        this.addSlot(new SlotItemHandler(tileEntity.getSawBladeHandler(), 0, 125, 40) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem().getItem() instanceof SawBladeBase;
            }
        });
        drawInventory(7, 96, playerInventory);
    }

    public TileCutter getTileEntity() {
        return tileEntity;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }
}
