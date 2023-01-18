package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class MMBlockMainPartBase extends MMBlockMultiBlockPart {
    public static HashMap<String, MMBlockMultiBlockPart> MAP = new HashMap<>();

    public MMBlockMainPartBase(Properties properties, String name, boolean noItem, String structureID) {
        super(properties, name, noItem);
        MAP.put(structureID, this);
    }

    @Nullable
    @Override
    public abstract BlockEntity createBlockEntity(BlockState state, IBlockReader world);
}
