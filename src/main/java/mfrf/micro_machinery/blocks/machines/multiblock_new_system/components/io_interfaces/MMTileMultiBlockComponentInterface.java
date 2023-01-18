package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class MMTileMultiBlockComponentInterface extends BlockEntity {
    protected BlockPos mainPart = null;
    protected Vec3i key = Vec3i.NULL_VECTOR;

    public MMTileMultiBlockComponentInterface(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
        if (compound.contains("main")) {
            mainPart = NBTUtil.readBlockPos(compound.getCompound("main"));
        }
        key = mfrf.dbydd.micro_machinery.utils.NBTUtil.readVEC3I(compound.getCompound("key"));
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag write = super.write(compound);
        if (mainPart != null) {
            write.put("main", NBTUtil.writeBlockPos(mainPart));
        }
        write.put("key", mfrf.dbydd.micro_machinery.utils.NBTUtil.writeVEC3I(key));
        return write;
    }


    public void linkTo(BlockPos pos, World world, Vec3i key) {
        this.key = key;
        mainPart = pos;
        ((MMTileMainPartBase) world.getBlockEntity(mainPart)).linkComponent(this.pos, key);
        setChanged();
    }

    public void unLink() {
        mainPart = null;
        key = Vec3i.NULL_VECTOR;
        setChanged();
    }


}
