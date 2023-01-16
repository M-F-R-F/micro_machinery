package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.test;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class TestMainPart extends MMBlockMainPartBase {
    public TestMainPart() {
        super(Properties.create(Material.ROCK), "test", true, "testS");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TestTileMainPart();
    }
}
