package com.dbydd.micro_machinery.items.special;

import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class TestItem extends Item {
    public TestItem() {
        setUnlocalizedName("testitem");
        setRegistryName("testitem");
        ModItems.ITEMS.add(this);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        player.sendMessage(new TextComponentString(Blocks.STONE.getStateFromMeta(3) == player.getEntityWorld().getBlockState(pos) ? "true" : "false"));
        return super.onBlockStartBreak(itemstack, pos, player);
    }
}
