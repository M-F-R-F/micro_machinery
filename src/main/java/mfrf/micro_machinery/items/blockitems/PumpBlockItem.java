package mfrf.micro_machinery.items.blockitems;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.core.Direction;

public class PumpBlockItem extends BlockItem {
    public PumpBlockItem() {
        super(RegisteredBlocks.PUMP, new Item.Properties().group(MicroMachinery.MMTAB));
    }

    @Override
    protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
        boolean b = super.canPlace(p_195944_1_, p_195944_2_);
        BlockState blockState1 = p_195944_1_.getWorld().getBlockState(p_195944_1_.getPos().m_142300_(p_195944_1_.getPlacementHorizontalFacing().rotateY()));
        BlockState blockState2 = p_195944_1_.getWorld().getBlockState(p_195944_1_.getPos().m_142300_(Direction.UP));
        return b && blockState1.isReplaceable(p_195944_1_) && blockState2.isReplaceable(p_195944_1_);
    }
}
