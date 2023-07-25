package mfrf.micro_machinery.worldgen;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

public class BlockListMatchTest extends RuleTest {

    @Override
    public boolean test(BlockState pState, RandomSource pRandom) {
        return false;
    }

    @Override
    protected RuleTestType<?> getType() {
        return RuleTestType.BLOCK_TEST;
    }
}
