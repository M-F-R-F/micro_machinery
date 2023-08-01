package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTextures extends BlockModelProvider {

    public BlockTextures(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MicroMachinery.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
