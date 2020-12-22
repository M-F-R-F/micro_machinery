package mfrf.dbydd.micro_machinery.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IMultiBlockMainPart {

    void addDelegate(BlockPos pos);

    void onBreak(World worldIn, BlockPos pos, PlayerEntity player, BlockState state);
}
