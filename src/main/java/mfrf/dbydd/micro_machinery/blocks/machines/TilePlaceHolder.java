package mfrf.dbydd.micro_machinery.blocks.machines;

import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockMainPart;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class TilePlaceHolder extends MMTileBase {
    private BlockPos mainPartPos = null;
    private CompoundNBT packedNBT = null;

    public TilePlaceHolder() {
        super(Registered_Tileentitie_Types.TILE_PLACEHOLDER.get());
    }

    public BlockPos getMainPartPos() {
        return mainPartPos;
    }

    public CompoundNBT getPackedNBT() {
        return packedNBT;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (packedNBT != null) {
            compound.put("packed_nbt", packedNBT);
        }
        if (mainPartPos != null) {
            compound.put("main_part_pos", NBTUtil.writeBlockPos(mainPartPos));
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        if (compound.contains("packed_nbt")) {
            packedNBT = compound.getCompound("packed_nbt");
        }
        if (compound.contains("main_part_pos")) {
            mainPartPos = NBTUtil.readBlockPos(compound.getCompound("main_part_pos"));
        }
        super.read(compound);
    }

    public void setMainPartPos(BlockPos mainPartPos) {
        this.mainPartPos = mainPartPos;
        markDirty();
    }

    public void setPackedNBT(CompoundNBT packedNBT) {
        this.packedNBT = packedNBT;
        markDirty();
    }

    public ActionResultType onBlockActivated(World worldIn, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return worldIn.getBlockState(mainPartPos).onBlockActivated(worldIn, player, handIn, hit);
    }

    public void onBlockHarvest(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        IMultiBlockMainPart tileEntity = (IMultiBlockMainPart) worldIn.getTileEntity(mainPartPos);
        tileEntity.onBreak(worldIn, pos, player, state);
        //todo nbt fix
    }
}
