package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts;

import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MMTileMainPartBase extends MMTileMultiBlockPart {
    private ArrayList<BlockPos> partPoss = new ArrayList<>();
    protected HashMap<Vec3i, BlockPos> componentPoss = new HashMap<>();

    public MMTileMainPartBase(BlockEntityType<?> p_i48289_1_, BlockPos pos, BlockState state) {
        super(p_i48289_1_, pos, state);
    }

    @Override
    public void onBlockHarvest(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        for (BlockPos poss : componentPoss.values()) {
            ((MMTileMultiBlockComponentInterface) worldIn.getBlockEntity(poss)).unLink();
        }
        for (BlockPos poss : partPoss) {
            MMBlockMultiBlockPart.unpack(worldIn, poss);
        }

        releaseDataOnUnpack(worldIn, pos);
    }

    /**
     * function:release data, example:items
     */
    protected abstract void releaseDataOnUnpack(LevelAccessor world, BlockPos breakPos);

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        ListTag components = compound.getList("components", Tag.TAG_COMPOUND);
        for (Tag component : components) {
            readKV(componentPoss, (CompoundTag) component);
        }
        ListTag parts = compound.getList("parts", Tag.TAG_COMPOUND);
        for (Tag part : parts) {
            partPoss.add(NBTUtil.readBlockPos((CompoundTag) part));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag write) {
        super.saveAdditional(write);
        ListTag components = new ListTag();
        for (Map.Entry<Vec3i, BlockPos> componentPos : componentPoss.entrySet()) {
            components.add(writeKV(componentPos.getKey(), componentPos.getValue()));
        }
        ListTag parts = new ListTag();
        for (BlockPos partPos : partPoss) {
            parts.add(NBTUtil.writeBlockPos(partPos));
        }
        write.put("components", components);
        write.put("parts", parts);
    }

    private CompoundTag writeKV(Vec3i key, BlockPos value) {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.put("key", NBTUtil.writeVEC3I(key));
        compoundNBT.put("value", NBTUtil.writeBlockPos(value));
        return compoundNBT;
    }

    private void readKV(HashMap<Vec3i, BlockPos> store, CompoundTag nbt) {
        store.put(NBTUtil.readVEC3I(nbt.getCompound("key")), NBTUtil.readBlockPos(nbt.getCompound("value")));
    }

    public void link(BlockPos pos) {
        partPoss.add(pos);
        setChanged();
    }


    public void linkComponent(BlockPos pos, Vec3i key) {
        componentPoss.put(key, pos);
    }

    public abstract void redstoneSignalChange(int changed, Vec3i key);
}
