package mfrf.micro_machinery.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class MMBlockBase extends Block {
    public static final Properties DEFAULT_PROPERTIES = Properties.of().sound(SoundType.STONE);

    public MMBlockBase(Properties properties) {
        super(properties);
    }

    public MMBlockBase() {
        super(DEFAULT_PROPERTIES);
    }
}
