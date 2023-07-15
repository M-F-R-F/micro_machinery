package mfrf.micro_machinery.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.ArrayList;
import java.util.List;

public class MMBlockBase extends Block {
    public static final Properties DEFAULT_PROPERTIES = Properties.of().sound(SoundType.STONE);
    public static final List<Block> simpleBlocks = new ArrayList<>();

    public MMBlockBase(Properties properties) {
        super(properties);
        simpleBlocks.add(this);
    }

    public MMBlockBase() {
        super(DEFAULT_PROPERTIES);
    }

    private String getLoc() {
        return "";
    }
}
