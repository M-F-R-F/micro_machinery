package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MMTab {
    public static final DeferredRegister<CreativeModeTab> TAB_REGISER = DeferredRegister
            .create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MicroMachinery.MODID);
    public static final RegistryObject<CreativeModeTab> ICON_TAB = TAB_REGISER.register(
            MicroMachinery.MODID, () ->
//                    CreativeModeTab.builder().icon(MMItems.ICON.get()::getDefaultInstance).build());
                    CreativeModeTab.builder().icon(() -> new ItemStack(Items.STICK)).title(Component.translatable("test")).build());

}
