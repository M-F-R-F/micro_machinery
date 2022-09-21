package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class MMBlockMainPartBase extends MMBlockMultiBlockPart {
    public static HashMap<String, MMBlockMultiBlockPart> MAP = new HashMap<>();

    public MMBlockMainPartBase(Properties properties, String name, boolean noItem, String structureID) {
        super(properties, name, noItem);
        MAP.put(structureID, this);
    }

    @Override
    public BlockState pack(World world, BlockPos pos, Direction direction,BlockPos mainpart) {
//        return super.pack(world, pos, direction)
//
        return null;
    }

    @Override
    public void unpack(CompoundNBT packedNBT, World world, BlockPos pos) {
        ((MMTileMainPartBase) world.getTileEntity(pos)).onUnpack();

    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}
