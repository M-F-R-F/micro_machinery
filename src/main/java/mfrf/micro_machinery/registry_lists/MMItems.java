package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.items.MMItemBase;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMItems {
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MicroMachinery.MODID);

    public static final RegistryObject<MMItemBase>
            ICON = ITEM_REGISTER.register("icon", MMItemBase::new);

    public static class TAB {
        public static final DeferredRegister<CreativeModeTab> TAG_REGISER = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MicroMachinery.MODID);
        public static final RegistryObject<CreativeModeTab> ICON_TAB = TAG_REGISER.register(MicroMachinery.MODID, CreativeModeTab.builder().icon(ICON.get()::getDefaultInstance)::build);

    }
}
