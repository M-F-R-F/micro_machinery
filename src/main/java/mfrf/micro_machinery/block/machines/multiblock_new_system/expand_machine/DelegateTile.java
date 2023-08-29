package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DelegateTile extends MMTileBase {
    public BlockPos main_pos = null;

    public DelegateTile(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.DELEGATE_TILE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (main_pos != null) {
            pTag.putLong("main_pos", main_pos.asLong());
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("main_pos")) {
            main_pos = BlockPos.of(pTag.getLong("main_pos"));
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return main_pos == null ? LazyOptional.empty() : ((MainTile) level.getBlockEntity(main_pos)).getCapability(cap, side, worldPosition);
    }

    public void destoy() {
        ((MainTile) level.getBlockEntity(main_pos)).destoy();
    }

    public void link(BlockPos main_pos) {
        ((MainTile) level.getBlockEntity(main_pos)).link(worldPosition);
        this.main_pos = main_pos;
        setChanged();
    }
}
