package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class MMTileMultiBlockPart extends TileEntity {
    private CompoundNBT packed = new CompoundNBT();
    private BlockPos mainPart = null;


    public MMTileMultiBlockPart() {
        super(RegisteredTileEntityTypes.MULTI_BLOCK_PART.get());
    }

    public MMTileMultiBlockPart(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        packed = compound.getCompound("packed");
        if (compound.contains("main")) {
            mainPart = NBTUtil.readBlockPos(compound.getCompound("main"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        write.put("packed", packed);
        if (mainPart != null) {
            write.put("main", NBTUtil.writeBlockPos(mainPart));
        }
        return write;
    }

    public void setPacked(CompoundNBT nbt, BlockPos mainPart) {
        packed = nbt;
        this.mainPart = mainPart;
        ((MMTileMainPartBase) world.getTileEntity(mainPart)).link(this.pos);
        markDirty();
    }

    public CompoundNBT getPacked() {
        return packed;
    }

    public ActionResultType onBlockActivated(World worldIn, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return null;
    }

    public void onBlockHarvest(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        ((MMTileMainPartBase) worldIn.getTileEntity(mainPart)).onBlockHarvest(worldIn, pos, player, state);
    }
}
