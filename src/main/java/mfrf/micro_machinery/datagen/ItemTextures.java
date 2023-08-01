package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTextures extends ItemModelProvider {
    public ItemTextures(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MicroMachinery.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        for (Item simpleItem : MMItemBase.simpleItems) {
//            String name = ForgeRegistries.ITEMS.getKey(simpleItem).getPath();
//            singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("items/" + name));
//        }
//        registerItemTexture(MMItems.WAFER.get());
    }

    private void registerItemTexture(Item item) {
        String name = ForgeRegistries.ITEMS.getKey(item).getPath();
        singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("items/" + name));
    }
}
