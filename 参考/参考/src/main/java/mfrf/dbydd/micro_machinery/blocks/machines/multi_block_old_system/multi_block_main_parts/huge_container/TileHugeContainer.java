package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.huge_container;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.HugeItemContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import mfrf.dbydd.micro_machinery.utils.DeprecatedMultiBlockStructureMaps.MultiBlockPosBox;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class TileHugeContainer extends MMMultiBlockTileMainPartBase {
    private HugeItemContainer container = new HugeItemContainer(Config.HUGE_CONTAINER_SLOT.get(), new IntegerContainer(0, Config.HUGE_CONTAINER_SLOT_STACK.get()));

    public TileHugeContainer() {
        super(RegisteredTileEntityTypes.TILE_HUGE_CONTAINER.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

    }

    @Override
    protected MultiBlockPosBox getMap() {
        return DeprecatedMultiBlockStructureMaps.getStructureMaps().get("huge_container").rotateTo(getBackDirection());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos pos) {
        return null;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, BlockPos pos) {
        return null;
    }

}
