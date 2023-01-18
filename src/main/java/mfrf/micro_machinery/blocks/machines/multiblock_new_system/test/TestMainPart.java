package mfrf.micro_machinery.blocks.machines.multiblock_new_system.test;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class TestMainPart extends MMBlockMainPartBase {
    public TestMainPart() {
        super(Properties.create(Material.STONE), "test", true, "testS");
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TestTileMainPart();
    }
}
