package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps.MultiBlockPosBox;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.BlockNode;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public abstract class MMMultiBlockTileMainPartBase extends MMTileBase {
    protected CompoundTag compoundBlockReplaced = null;
    protected boolean breaking = false;

    public MMMultiBlockTileMainPartBase(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        if (compoundBlockReplaced != null) {
            compound.put("tile_replaced", compoundBlockReplaced);
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
        if (compound.contains("tile_replaced")) {
            compoundBlockReplaced = compound.getCompound("tile_replaced");
        }

    }

    protected abstract DeprecatedMultiBlockStructureMaps.MultiBlockPosBox getMap();

    public void onBreak(World worldIn, BlockPos pos, Player player, BlockState state) {
        if (!worldIn.isRemote()) {
            if (!breaking) {
                breaking = true;

                MultiBlockPosBox map = getMap();

                for (BlockNode blocknode : map.getBlockNodes()) {
                    BlockPos blockPos = this.pos.add(blocknode.getPos());
                    BlockEntity tileEntity = world.getBlockEntity(blockPos);
                    if (tileEntity instanceof TilePlaceHolder) {
                        CompoundTag compoundNBT = ((TilePlaceHolder) tileEntity).getPackedNBT();
                        world.setBlockState(blockPos, NBTUtil.readBlockState(compoundNBT.getCompound("block_state_nbt")), 3);
                        if (compoundNBT.contains("tile_packaged")) {
                            world.getBlockEntity(blockPos).read(compoundNBT.getCompound("tile_packaged"));
                        }
                    }
                }

                worldIn.setBlock(this.pos, NBTUtil.readBlockState(compoundBlockReplaced.getCompound("block_state_nbt")));
                if (compoundBlockReplaced.contains("tile_packaged")) {
                    world.getBlockEntity(this.pos).read(compoundBlockReplaced.getCompound("tile_packaged"));
                }

                worldIn.getBlockState(pos).getBlock().onBlockHarvested(worldIn, pos, state, player);
            }
        }
    }

//    public void onAccessoryUse(BlockPos worldPos, BlockPos relativePosWithOutDirection, World world) {
//
//    }

    public void saveBlockBeenReplaced(CompoundTag compoundNBT) {
        compoundBlockReplaced = compoundNBT;
    }

    public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos pos);

    public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, BlockPos pos);
}
