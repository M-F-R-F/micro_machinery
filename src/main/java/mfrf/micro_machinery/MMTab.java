package mfrf.micro_machinery;

import mfrf.micro_machinery.registeried_lists.RegisteredItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.item.ItemStack;

public class MMTab extends ItemGroup {
    public MMTab() {
        super("MicroMachinery");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisteredItems.LOGO);
    }
}
