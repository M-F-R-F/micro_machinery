package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTUtil {

    public static CompoundNBT getBlockPackNBT(World world, BlockPos pos) {
        CompoundNBT packedNBT = new CompoundNBT();

        BlockState blockState = world.getBlockState(pos);
        packedNBT.put("block_state_nbt", net.minecraft.nbt.NBTUtil.writeBlockState(blockState));

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            packedNBT.put("tile_packaged", tileEntity.write(new CompoundNBT()));
        }

        return packedNBT;
    }

}
