package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.block.MMBlockBase;
import mfrf.micro_machinery.item.MMItemBase;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTextures extends BlockModelProvider {

    public BlockTextures(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MicroMachinery.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
