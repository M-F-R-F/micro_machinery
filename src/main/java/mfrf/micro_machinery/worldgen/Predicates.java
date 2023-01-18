package mfrf.micro_machinery.worldgen;

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.text.html.HTML;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum Predicates implements Predicate<BlockState> {
    OVERWORLD(blockState -> !(blockState.isIn(Tags.Blocks.NETHERRACK) || blockState.isIn(Tags.Blocks.END_STONES)) && blockState.isIn(Tags.Blocks.STONE) || blockState.isIn(Tags.Blocks.COBBLESTONE)),
    NETHER(blockState -> blockState.isIn(Tags.Blocks.NETHERRACK)),
    END(blockState -> blockState.isIn(Tags.Blocks.END_STONES));

    private final Predicate<BlockState> predicate;

    Predicates(Predicate<BlockState> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(BlockState block) {
        return predicate.test(block);
    }

}
