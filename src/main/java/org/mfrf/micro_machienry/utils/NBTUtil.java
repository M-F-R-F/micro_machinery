package org.mfrf.micro_machienry.utils;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.function.Consumer;

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

    public static CompoundNBT writeVEC3I(Vec3i vec) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("x", vec.getX());
        compoundNBT.putInt("y", vec.getY());
        compoundNBT.putInt("z", vec.getZ());
        return compoundNBT;
    }

    public static Vec3i readVEC3I(CompoundNBT nbt) {
        return new Vec3i(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }

    public static INBT getOrCreateItemTag(ItemStack stack, String subName, Consumer<CompoundNBT> defaultV) {
        CompoundNBT orCreateTag = stack.getOrCreateTag();
        if (!orCreateTag.contains(subName)) {
            defaultV.accept(orCreateTag);
        }
        return orCreateTag.get(subName);
    }

}
