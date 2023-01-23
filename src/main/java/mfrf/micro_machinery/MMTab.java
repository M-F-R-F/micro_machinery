package mfrf.micro_machinery;

import mfrf.micro_machinery.registeried_lists.RegisteredItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MMTab extends CreativeModeTab {
    public MMTab() {
        super("MicroMachinery");
    }

    @Override
    public ItemStack m_6976_() {
        return new ItemStack(RegisteredItems.LOGO);
    }
}
