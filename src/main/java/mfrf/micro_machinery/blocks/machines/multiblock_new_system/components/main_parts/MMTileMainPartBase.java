package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMBlockMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.io_interfaces.MMTileMultiBlockComponentInterface;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MMTileMainPartBase extends MMTileMultiBlockPart {
    private ArrayList<BlockPos> partPoss = new ArrayList<>();
    protected HashMap<Vec3i, BlockPos> componentPoss = new HashMap<>();

    public MMTileMainPartBase(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void onBlockHarvest(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        for (BlockPos poss : componentPoss.values()) {
            ((MMTileMultiBlockComponentInterface) worldIn.getTileEntity(poss)).unLink();
        }
        for (BlockPos poss : partPoss) {
            MMBlockMultiBlockPart.unpack(worldIn, poss);
        }

        releaseDataOnUnpack(worldIn, pos);

    }

    /**
     * function:release data, example:items
     */
    protected abstract void releaseDataOnUnpack(World world, BlockPos breakPos);


    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
        ListTag components = compound.getList("components", Constants.NBT.TAG_COMPOUND);
        for (INBT component : components) {
            readKV(componentPoss, (CompoundTag) component);
        }
        ListTag parts = compound.getList("parts", Constants.NBT.TAG_COMPOUND);
        for (INBT part : parts) {
            partPoss.add(NBTUtil.readBlockPos((CompoundTag) part));
        }

    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag write = super.write(compound);
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
        return write;
    }

    private CompoundTag writeKV(Vec3i key, BlockPos value) {
        CompoundTag CompoundTag = new CompoundTag();
        CompoundTag.put("key", mfrf.dbydd.micro_machinery.utils.NBTUtil.writeVEC3I(key));
        CompoundTag.put("value", NBTUtil.writeBlockPos(value));
        return CompoundTag;
    }

    private void readKV(HashMap<Vec3i, BlockPos> store, CompoundTag nbt) {
        store.put(mfrf.dbydd.micro_machinery.utils.NBTUtil.readVEC3I(nbt.getCompound("key")), NBTUtil.readBlockPos(nbt.getCompound("value")));
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
