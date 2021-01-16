package mfrf.dbydd.micro_machinery.gui.blast_furnace;

import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlastFurnaceContainer extends ContainerBase {
    public BlastFurnaceContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(Registered_ContainerTypes.BLAST_FURNACE_CONTAINER.get(), id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
