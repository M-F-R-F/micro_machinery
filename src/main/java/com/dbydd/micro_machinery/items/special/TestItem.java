package com.dbydd.micro_machinery.items.special;

import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem() {
        setUnlocalizedName("testitem");
        setRegistryName("testitem");
        ModItems.ITEMS.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int veinHeight = 5;
        int middleY = Math.round(veinHeight / 2);
        for (int i = 0 - middleY; i <= veinHeight; i++) {
            int x = pos.getX();
            int y = pos.getY() + i;
            int z = pos.getZ();
            int radius = veinHeight - (Math.abs(i));
            BlockPos beginPos = new BlockPos(x, y, z);
            for (int rx = x - radius; rx <= radius + x; rx++) {
                for (int rz = z - radius; rz <= radius + z; rz++) {
                    BlockPos position = new BlockPos(rx, y, rz);
                    if ((Math.pow((x - rx), 2) + Math.pow((z - rz), 2)) <= Math.pow((radius), 2)) {
                        player.getEntityWorld().setBlockState(position, Blocks.STONE.getDefaultState(), 2);
                    }
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
