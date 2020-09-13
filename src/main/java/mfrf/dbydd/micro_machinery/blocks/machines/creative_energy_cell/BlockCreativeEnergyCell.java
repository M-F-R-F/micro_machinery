package mfrf.dbydd.micro_machinery.blocks.machines.creative_energy_cell;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockCreativeEnergyCell extends MMBlockTileProviderBase {

    public BlockCreativeEnergyCell() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(-1), "creative_energy_cell");
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileCreativeEnergyCell();
    }
}
