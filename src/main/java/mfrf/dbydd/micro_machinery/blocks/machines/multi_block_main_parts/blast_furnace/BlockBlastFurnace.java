package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockBlastFurnace extends MMMultiBlockBase {

    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBlastFurnace();
    }
}
