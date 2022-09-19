package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockRedstoneInterface extends MMBlockMultiBlockComponentInterface {
    public BlockRedstoneInterface(Properties properties, String name) {
        super(properties, name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileRedstoneInterface();
    }


    public void charged(int power) {
        //todo capability, function invoke
    }

}
