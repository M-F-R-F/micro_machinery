package mfrf.micro_machinery.block.machines.multiblock_new_system.components.io_interfaces;

import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MMTileMultiBlockComponentInterface extends BlockEntity {
    protected BlockPos mainPart = null;
    protected Vec3i key = Vec3i.ZERO;

    public MMTileMultiBlockComponentInterface(BlockEntityType<?> tileEntityTypeIn, BlockState state, BlockPos pos) {
        super(tileEntityTypeIn, pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("main")) {
            mainPart = NBTUtil.readBlockPos(compound.getCompound("main"));
        }
        key = mfrf.micro_machinery.utils.NBTUtil.readVEC3I(compound.getCompound("key"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (mainPart != null) {
            pTag.put("main", NBTUtil.writeBlockPos(mainPart));
        }
        pTag.put("key", mfrf.micro_machinery.utils.NBTUtil.writeVEC3I(key));
    }

    public void linkTo(BlockPos pos, Level world, Vec3i key) {
        this.key = key;
        mainPart = pos;
        ((MMTileMainPartBase) world.getBlockEntity(mainPart)).linkComponent(this.getBlockPos(), key);
        setChanged();
    }

    public void unLink() {
        mainPart = null;
        key = Vec3i.ZERO;
        setChanged();
    }


}
