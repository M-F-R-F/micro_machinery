package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine;

import mfrf.micro_machinery.block.machines.MMTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.List;

public abstract class MainTile extends MMTileBase {
    private List<BlockPos> delegate_poss = new ArrayList<>();

    public MainTile(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putLongArray("delegate_poss", delegate_poss.stream().mapToLong(BlockPos::asLong).toArray());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        for (long delegatePoss : pTag.getLongArray("delegate_poss")) {
            delegate_poss.add(BlockPos.of(delegatePoss));
        }
    }

    public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos worldPosition);

    public void destoy() {
        delegate_poss.forEach(pos -> level.removeBlock(pos, false));
        level.removeBlock(worldPosition, true);
    }

    public void link(BlockPos worldPosition) {
        delegate_poss.add(worldPosition);
    }
}
