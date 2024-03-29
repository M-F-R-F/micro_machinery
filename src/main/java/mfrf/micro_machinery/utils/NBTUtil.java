package mfrf.micro_machinery.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class NBTUtil {

    public static CompoundTag getBlockPackNBT(Level world, BlockPos pos) {
        CompoundTag packedNBT = new CompoundTag();

        BlockState blockState = world.getBlockState(pos);
        packedNBT.put("block_state_nbt", NbtUtils.writeBlockState(blockState));

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity != null) {
            packedNBT.put("tile_packaged", tileEntity.saveWithFullMetadata());
        }

        return packedNBT;
    }

    public static CompoundTag writeVEC3I(Vec3i vec) {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("x", vec.getX());
        compoundNBT.putInt("y", vec.getY());
        compoundNBT.putInt("z", vec.getZ());
        return compoundNBT;
    }

    public static CompoundTag writeBlockPos(BlockPos vec) {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("x", vec.getX());
        compoundNBT.putInt("y", vec.getY());
        compoundNBT.putInt("z", vec.getZ());
        return compoundNBT;
    }

    public static Vec3i readVEC3I(CompoundTag nbt) {
        return new Vec3i(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }

    public static BlockPos readBlockPos(CompoundTag nbt) {
        return new BlockPos(nbt.getInt("X"), nbt.getInt("Y"), nbt.getInt("Z"));
    }


    public static Tag getOrCreateItemTag(ItemStack stack, String subName, Consumer<CompoundTag> defaultV) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (!orCreateTag.contains(subName)) {
            defaultV.accept(orCreateTag);
        }
        return orCreateTag.get(subName);
    }

}
