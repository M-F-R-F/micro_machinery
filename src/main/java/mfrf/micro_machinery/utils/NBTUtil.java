package mfrf.micro_machinery.utils;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class NBTUtil {

    public static CompoundTag getBlockPackNBT(World world, BlockPos pos) {
        CompoundTag packedNBT = new CompoundTag();

        BlockState blockState = world.getBlockState(pos);
        packedNBT.put("block_state_nbt", net.minecraft.nbt.NBTUtil.writeBlockState(blockState));

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            packedNBT.put("tile_packaged", tileEntity.write(new CompoundTag()));
        }

        return packedNBT;
    }

    public static CompoundTag writeVEC3I(Vec3i vec) {
        CompoundTag CompoundTag = new CompoundTag();
        CompoundTag.putInt("x", vec.getX());
        CompoundTag.putInt("y", vec.getY());
        CompoundTag.putInt("z", vec.getZ());
        return CompoundTag;
    }

    public static Vec3i readVEC3I(CompoundTag nbt) {
        return new Vec3i(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }

    public static INBT getOrCreateItemTag(ItemStack stack, String subName, Consumer<CompoundTag> defaultV) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (!orCreateTag.contains(subName)) {
            defaultV.accept(orCreateTag);
        }
        return orCreateTag.get(subName);
    }

}
