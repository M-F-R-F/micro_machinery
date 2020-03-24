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
        //参数：矿脉最大半径R(5到R之间的随机数)，矿脉生成概率P，矿物层数OreStratum，矿层高度OreDepositHeight，石层高度StoneHeight，矿层矿物种类，矿层矿物比重，矿脉最低高度minHeight，矿脉最高高度maxHeight,总高度veinHeight
        int R = 8;
        int OS = 5;
        int ODH = 4;
        int SH = 2;
        int minH = 64;
        int maxH = 72;
        int veinHeight = (ODH + SH) * OS - SH;

        for (int i = 0; i < OS; i++) {
            for (int j = 0; j < ODH; j++) {

                int x = pos.getX();
                int y = pos.getY() + j + (SH + ODH) * i;
                int z = pos.getZ();

                //半径
                int radius = (4 + R) + (int) (R * Math.sin(180 * ((double) (j + (SH + ODH) * i) / (double) veinHeight)));


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
        }
        return EnumActionResult.SUCCESS;
    }
}