package org.mfrf.micro_machienry.blocks.machines.multi_block_old_system.multi_block_main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps.MultiBlockPosBox;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.BlockNode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public abstract class MMMultiBlockTileMainPartBase extends MMTileBase {
    protected CompoundNBT compoundBlockReplaced = null;
    protected boolean breaking = false;

    public MMMultiBlockTileMainPartBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (compoundBlockReplaced != null) {
            compound.put("tile_replaced", compoundBlockReplaced);
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("tile_replaced")) {
            compoundBlockReplaced = compound.getCompound("tile_replaced");
        }

    }

    protected abstract MultiBlockPosBox getMap();

    public void onBreak(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        if (!worldIn.isRemote()) {
            if (!breaking) {
                breaking = true;

                MultiBlockPosBox map = getMap();

                for (BlockNode blocknode : map.getBlockNodes()) {
                    BlockPos blockPos = this.pos.add(blocknode.getPos());
                    TileEntity tileEntity = world.getTileEntity(blockPos);
                    if (tileEntity instanceof TilePlaceHolder) {
                        CompoundNBT compoundNBT = ((TilePlaceHolder) tileEntity).getPackedNBT();
                        world.setBlockState(blockPos, NBTUtil.readBlockState(compoundNBT.getCompound("block_state_nbt")), 3);
                        if (compoundNBT.contains("tile_packaged")) {
                            world.getTileEntity(blockPos).read(compoundNBT.getCompound("tile_packaged"));
                        }
                    }
                }

                worldIn.setBlockState(this.pos, NBTUtil.readBlockState(compoundBlockReplaced.getCompound("block_state_nbt")));
                if (compoundBlockReplaced.contains("tile_packaged")) {
                    world.getTileEntity(this.pos).read(compoundBlockReplaced.getCompound("tile_packaged"));
                }

                worldIn.getBlockState(pos).getBlock().onBlockHarvested(worldIn, pos, state, player);
            }
        }
    }

//    public void onAccessoryUse(BlockPos worldPos, BlockPos relativePosWithOutDirection, World world) {
//
//    }

    public void saveBlockBeenReplaced(CompoundNBT compoundNBT) {
        compoundBlockReplaced = compoundNBT;
    }

    public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos pos);

    public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, BlockPos pos);
}
