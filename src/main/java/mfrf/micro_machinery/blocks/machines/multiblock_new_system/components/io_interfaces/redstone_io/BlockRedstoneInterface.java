package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.redstone_io;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMBlockMultiBlockComponentInterface;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRedstoneInterface extends MMBlockMultiBlockComponentInterface {
    public BlockRedstoneInterface(Properties properties, String name) {
        super(properties, name);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TileRedstoneInterface();
    }


    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote()) {
            int power = worldIn.getRedstonePowerFromNeighbors(pos);
            ((TileRedstoneInterface) worldIn.getBlockEntity(pos)).powerChange(power);
        }
    }
}
