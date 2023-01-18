package mfrf.micro_machinery.blocks.machines.multi_block_old_system;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class TilePlaceHolder extends MMTileBase {
    private Optional<BlockPos> mainPartPos = Optional.empty();
    private CompoundTag packedNBT = null;

    public TilePlaceHolder() {
        super(RegisteredBlockEntityTypes.TILE_PLACEHOLDER.get());
    }

    public TilePlaceHolder(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public BlockPos getMainPartPos() {
        return mainPartPos.orElseGet(() -> {
            BlockState blockState = level.getBlockState(getBlockPos());
            this.mainPartPos = Optional.ofNullable(((MMMultiBlockHolderBase) blockState.getBlock()).getMainPartPos(blockState, getBlockPos()));
            setChanged();
            return mainPartPos.get();
        });
    }

    public void setMainPartPos(BlockPos mainPartPos) {
        this.mainPartPos = Optional.of(mainPartPos);
        setChanged();
    }

    public CompoundTag getPackedNBT() {
        return packedNBT;
    }

    public void setPackedNBT(CompoundTag packedNBT) {
        this.packedNBT = packedNBT;
        setChanged();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return ((MMMultiBlockTileMainPartBase) level.getBlockEntity(getMainPartPos())).getCapability(cap, getBlockPos());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ((MMMultiBlockTileMainPartBase) level.getBlockEntity(getMainPartPos())).getCapability(cap, side, getBlockPos());
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        if (packedNBT != null) {
            compound.put("packed_nbt", packedNBT);
        }
        if (mainPartPos != null) {
            compound.put("main_part_pos", NBTUtil.writeBlockPos(getMainPartPos()));
        }
    }

    @Override
    public void load(CompoundTag compound) {
        if (compound.contains("packed_nbt")) {
            packedNBT = compound.getCompound("packed_nbt");
        }
        if (compound.contains("main_part_pos")) {
            mainPartPos = Optional.of(NBTUtil.readBlockPos(compound.getCompound("main_part_pos")));
        }
        super.load(compound);
    }

    public ActionResultType onBlockActivated(Level worldIn, Player player, Hand handIn, BlockRayTraceResult hit) {
        BlockState blockState = worldIn.getBlockState(getMainPartPos());
        return blockState.getBlock().onBlockActivated(blockState, worldIn, getMainPartPos(), player, handIn, hit);
    }

    public void onBlockHarvest(Level worldIn, BlockPos pos, Player player, BlockState state) {
        MMMultiBlockTileMainPartBase tileEntity = (MMMultiBlockTileMainPartBase) worldIn.getBlockEntity(getMainPartPos());
        tileEntity.onBreak(worldIn, pos, player, state);
    }

    protected MMMultiBlockTileMainPartBase getMainPart() {
        if (mainPartPos != null) {
            return (MMMultiBlockTileMainPartBase) level.getBlockEntity(getMainPartPos());
        }
        return null;
    }

    protected <T> LazyOptional<T> getMainPartCapability(@Nonnull Capability<T> cap) {
        MMMultiBlockTileMainPartBase mainPart = getMainPart();
        return mainPart == null ? LazyOptional.empty() : mainPart.getCapability(cap);
    }
}
