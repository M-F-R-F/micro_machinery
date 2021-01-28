package mfrf.dbydd.micro_machinery.gui.electrolysis;

import mfrf.dbydd.micro_machinery.gui.ContainerBase;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_ContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ElectrolysisContainer extends ContainerBase {
    public ElectrolysisContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world) {
        super(Registered_ContainerTypes.ELECTROLYSIS_CONTAINER.get(), id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
