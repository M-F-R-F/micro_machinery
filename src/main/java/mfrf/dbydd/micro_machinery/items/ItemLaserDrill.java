package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.utils.EnergyItemHandler;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class ItemLaserDrill extends MMItemBase {

    public ItemLaserDrill() {
        super(new Properties().group(Micro_Machinery.MMTAB).maxStackSize(1), "laser_drill");
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (!stack.isEmpty())
            return new EnergyItemHandler(stack, new FEContainer(0, Config.LASER_DRILL_ENERGY_CAP.get()) {
                @Override
                protected int selfSubtractValue() {
                    return Config.LASER_DRILL_ENERGY_CONSUME.get();
                }

                @Override
                public boolean canExtract() {
                    return false;
                }

                @Override
                public boolean canReceive() {
                    return true;
                }

            });
        return null;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (heldItem.getItem() instanceof ItemLaserDrill) {
            heldItem.getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
                //todo render laser
            });
        }

        return ActionResult.resultPass(heldItem);
    }
}
