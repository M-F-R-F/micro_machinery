package mfrf.micro_machinery.datagen;

import mfrf.micro_machinery.registry_lists.MMBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockPropertys extends BlockTagsProvider
    public BlockPropertys(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        List.of(MMBlocks.BLOCKBRONZE,MMBlocks.BLOCKALUMINUM,)// 这里填MMBlocks里的方块，可以填无限多个
                .stream().map(registryObjectRegistryObjectPair -> registryObjectRegistryObjectPair.getKey().getKey())
                .forEach(tag(BlockTags.NEEDS_IRON_TOOL)::add);
                            // 👆这里是工具等级
    }
}
