package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
        if (main_pos == null) return LazyOptional.empty();
        AtomicReference<LazyOptional<T>> ret = new AtomicReference<>();
        assertNonNullMainPart().ifPresent(m -> ret.set(m.getCapability(cap, side, worldPosition)));
        return ret.get();
    }

    public void destoy() {
        ((MainTile) level.getBlockEntity(main_pos)).destoy();
    }

    protected void destroySelf() {
        level.setBlock(worldPosition, Blocks.AIR.defaultBlockState(),3);
    }

    public void link(BlockPos main_pos) {
        ((MainTile) level.getBlockEntity(main_pos)).link(worldPosition);
        this.main_pos = main_pos;
        setChanged();
    }

    protected Optional<MainTile> assertNonNullMainPart() {
        MainTile blockEntity = (MainTile) level.getBlockEntity(main_pos);
        if (blockEntity == null) {
            destroySelf();
            return Optional.empty();
        } else return Optional.of(blockEntity);
    }
}
