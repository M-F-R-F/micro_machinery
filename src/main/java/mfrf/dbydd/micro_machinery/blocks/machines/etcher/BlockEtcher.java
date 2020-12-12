package mfrf.dbydd.micro_machinery.blocks.machines.etcher;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockEtcher extends MMBlockTileProviderBase {

    public BlockEtcher() {
        super(Properties.create(Material.IRON), "etcher");
        this.setDefaultState(getStateToRegistry());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEtcher();
    }

}
