package mfrf.dbydd.micro_machinery.blocks.machines.electrolysis;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockElectrolysis extends MMBlockTileProviderBase {
    public BlockElectrolysis() {
        super(Block.Properties.create(Material.IRON), "electrolysis");
        this.setDefaultState(getStateToRegistry());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileElectrolysis();
    }
}
