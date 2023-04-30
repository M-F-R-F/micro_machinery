package mfrf.micro_machinery.worldgen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.stream.Collectors;

public class Predicates {
    public static final List<ResourceLocation>
            OVERWORLD = List.of(Tags.Blocks.STONE, Tags.Blocks.COBBLESTONE, Tags.Blocks.END_STONES, Tags.Blocks.NETHERRACK).stream().map(TagKey::location).collect(Collectors.toList()),
            NETHER = List.of(Tags.Blocks.NETHERRACK).stream().map(TagKey::location).collect(Collectors.toList()),
            END = List.of(Tags.Blocks.END_STONES).stream().map(TagKey::location).collect(Collectors.toList());

}
