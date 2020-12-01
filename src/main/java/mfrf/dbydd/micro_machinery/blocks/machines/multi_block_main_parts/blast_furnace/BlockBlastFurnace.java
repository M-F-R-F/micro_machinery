package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import mfrf.dbydd.micro_machinery.utils.MultiBlockStructureMaps;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.BlastFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockBlastFurnace extends MMMultiBlockBase {
    public BlockBlastFurnace(Properties properties) {
        super(properties, "blast_furnace", false, false, true);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBlastFurnace();
    }
}
