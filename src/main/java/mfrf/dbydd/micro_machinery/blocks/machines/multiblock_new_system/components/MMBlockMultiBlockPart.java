package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MMBlockMultiBlockPart extends MMBlockTileProviderBase {

    public MMBlockMultiBlockPart(Properties properties, String name, boolean noItem) {
        super(properties, name, noItem);
    }

    public BlockState pack(World world, BlockPos pos, Direction direction) {
        BlockState replace = getDefaultState();

        CompoundNBT reserved = new CompoundNBT();

        CompoundNBT blockNBT = NBTUtil.writeBlockState(world.getBlockState(pos));
        reserved.put("block", blockNBT);

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            reserved.put("tile", tileEntity.serializeNBT());
        }

        world.setBlockState(pos, replace);
        ((MMTileMultiBlockPart) world.getTileEntity(pos)).setPacked(reserved);
        return replace;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MMTileMultiBlockPart();
    }
}
