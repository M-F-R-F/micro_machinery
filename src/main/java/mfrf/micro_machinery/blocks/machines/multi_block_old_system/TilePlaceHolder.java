package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class TilePlaceHolder extends MMTileBase {
    private Optional<BlockPos> mainPartPos = Optional.empty();
    private CompoundNBT packedNBT = null;

    public TilePlaceHolder() {
        super(RegisteredTileEntityTypes.TILE_PLACEHOLDER.get());
    }

    public TilePlaceHolder(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public BlockPos getMainPartPos() {
        return mainPartPos.orElseGet(() -> {
            BlockState blockState = world.getBlockState(pos);
            this.mainPartPos = Optional.ofNullable(((MMMultiBlockHolderBase) blockState.getBlock()).getMainPartPos(blockState, pos));
            markDirty();
            return mainPartPos.get();
        });
    }

    public void setMainPartPos(BlockPos mainPartPos) {
        this.mainPartPos = Optional.of(mainPartPos);
        markDirty();
    }

    public CompoundNBT getPackedNBT() {
        return packedNBT;
    }

    public void setPackedNBT(CompoundNBT packedNBT) {
        this.packedNBT = packedNBT;
        markDirty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return ((MMMultiBlockTileMainPartBase) world.getTileEntity(getMainPartPos())).getCapability(cap, pos);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ((MMMultiBlockTileMainPartBase) world.getTileEntity(getMainPartPos())).getCapability(cap, side, pos);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (packedNBT != null) {
            compound.put("packed_nbt", packedNBT);
        }
        if (mainPartPos != null) {
            compound.put("main_part_pos", NBTUtil.writeBlockPos(getMainPartPos()));
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        if (compound.contains("packed_nbt")) {
            packedNBT = compound.getCompound("packed_nbt");
        }
        if (compound.contains("main_part_pos")) {
            mainPartPos = Optional.of(NBTUtil.readBlockPos(compound.getCompound("main_part_pos")));
        }
        super.read(compound);
    }

    public ActionResultType onBlockActivated(World worldIn, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        BlockState blockState = worldIn.getBlockState(getMainPartPos());
        return blockState.getBlock().onBlockActivated(blockState, worldIn, getMainPartPos(), player, handIn, hit);
    }

    public void onBlockHarvest(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        MMMultiBlockTileMainPartBase tileEntity = (MMMultiBlockTileMainPartBase) worldIn.getTileEntity(getMainPartPos());
        tileEntity.onBreak(worldIn, pos, player, state);
    }

    protected MMMultiBlockTileMainPartBase getMainPart() {
        if (mainPartPos != null) {
            return (MMMultiBlockTileMainPartBase) world.getTileEntity(getMainPartPos());
        }
        return null;
    }

    protected <T> LazyOptional<T> getMainPartCapability(@Nonnull Capability<T> cap) {
        MMMultiBlockTileMainPartBase mainPart = getMainPart();
        return mainPart == null ? LazyOptional.empty() : mainPart.getCapability(cap);
    }
}
