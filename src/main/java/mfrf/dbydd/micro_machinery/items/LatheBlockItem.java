package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;

public class LatheBlockItem extends BlockItem {

    public LatheBlockItem() {
        super(RegisteredBlocks.LATHE, new Item.Properties().group(Micro_Machinery.MMTAB));
    }

    @Override
    protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
        boolean b = super.canPlace(p_195944_1_, p_195944_2_);
        BlockState blockState = p_195944_1_.getWorld().getBlockState(p_195944_1_.getPos().offset(p_195944_1_.getPlacementHorizontalFacing().rotateY()));
        return  b && blockState.isReplaceable(p_195944_1_);
    }
}
