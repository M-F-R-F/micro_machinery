package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.MMTileMultiBlockPart;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

public class MMTileMainPartBase extends MMTileMultiBlockPart {
    private ArrayList<BlockPos> partPoss = new ArrayList<>();
    private ArrayList<BlockPos> componentPoss = new ArrayList<>();

    public MMTileMainPartBase(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void onBlockHarvest(World worldIn, BlockPos pos, PlayerEntity player, BlockState state) {
        //todo onbreak
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ListNBT components = compound.getList("components", Constants.NBT.TAG_COMPOUND);
        for (INBT component : components) {
            componentPoss.add(NBTUtil.readBlockPos((CompoundNBT) component));
        }
        ListNBT parts = compound.getList("parts", Constants.NBT.TAG_COMPOUND);
        for (INBT part : parts) {
            partPoss.add(NBTUtil.readBlockPos((CompoundNBT) part));
        }

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        ListNBT components = new ListNBT();
        for (BlockPos componentPos : componentPoss) {
            components.add(NBTUtil.writeBlockPos(componentPos));
        }
        ListNBT parts = new ListNBT();
        for (BlockPos partPos : partPoss) {
            parts.add(NBTUtil.writeBlockPos(partPos));
        }
        write.put("components", components);
        write.put("parts", parts);
        return write;
    }

    public void link(BlockPos pos) {
        partPoss.add(pos);
        markDirty();
    }


    public void linkComponent(BlockPos pos) {
        componentPoss.add(pos);
    }
}
