package mfrf.micro_machinery.worldgen;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public enum Predicates implements Predicate<BlockState> {
    OVERWORLD(blockState -> !(blockState.is(Tags.Blocks.NETHERRACK) || blockState.is(Tags.Blocks.END_STONES)) && blockState.is(Tags.Blocks.STONE) || blockState.is(Tags.Blocks.COBBLESTONE)),
    NETHER(blockState -> blockState.is(Tags.Blocks.NETHERRACK)),
    END(blockState -> blockState.is(Tags.Blocks.END_STONES));

    private final Predicate<BlockState> predicate;

    Predicates(Predicate<BlockState> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(BlockState block) {
        return predicate.test(block);
    }

}
