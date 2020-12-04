package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockMainPart;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public abstract class MMMultiBlockTileMainPartBase extends MMTileBase implements IMultiBlockMainPart {
    protected CompoundNBT compoundNBTTileReplaced = null;
    protected ArrayList<BlockPos> blockPlaceHolderList = null;

    public MMMultiBlockTileMainPartBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (compoundNBTTileReplaced != null) {
            compound.put("tile_replaced", compoundNBTTileReplaced);
        }
        if (blockPlaceHolderList != null) {
            ListNBT listNBT = new ListNBT();
            for (BlockPos blockPos : blockPlaceHolderList) {
                CompoundNBT compoundNBT = new CompoundNBT();
                compoundNBT.putInt("x", blockPos.getX());
                compoundNBT.putInt("y", blockPos.getY());
                compoundNBT.putInt("z", blockPos.getZ());

                listNBT.add(compound);
            }
            compound.put("block_place_holders", listNBT);
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("tile_replaced")) {
            compoundNBTTileReplaced = compound.getCompound("tile_replaced");
        }
        if (compound.contains("block_place_holders")) {
            ListNBT block_place_holders = (ListNBT) compound.get("block_place_holders");
            for (INBT block_place_holder : block_place_holders) {
                CompoundNBT blockPlaceHolder = (CompoundNBT) block_place_holder;
                if (blockPlaceHolderList == null) {
                    blockPlaceHolderList = new ArrayList<>();
                }

                blockPlaceHolderList.add(new BlockPos(blockPlaceHolder.getInt("x"), blockPlaceHolder.getInt("y"), blockPlaceHolder.getInt("z")));
            }
        }

    }

    @Override
    public void addDelegate(BlockPos pos) {
        if (blockPlaceHolderList == null) {
            blockPlaceHolderList = new ArrayList<>();
        }
        blockPlaceHolderList.add(pos);
        markDirty();
    }

    @Override
    public void onBreak(World worldIn, BlockPos pos, PlayerEntity player, BlockState state, ItemStack stack) {
        for (BlockPos blockPos : blockPlaceHolderList) {
            CompoundNBT compoundNBT = ((TilePlaceHolder) world.getTileEntity(blockPos)).getPackedNBT();
            world.setBlockState(blockPos, NBTUtil.readBlockState(compoundNBT.getCompound("block_state_nbt")));
            if (compoundNBT.contains("tile_packaged")) {
                world.getTileEntity(blockPos).read(compoundNBT.getCompound("tile_packaged"));
            }
        }
    }

    public void saveTileBeenReplaced(CompoundNBT compoundNBT) {
        compoundNBTTileReplaced = compoundNBT;
    }
}
