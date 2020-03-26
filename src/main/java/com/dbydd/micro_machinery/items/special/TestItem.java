package com.dbydd.micro_machinery.items.special;

import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.worldgen.VeinGenerator;
import com.dbydd.micro_machinery.worldgen.predicates.StonePredicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.Random;

public class TestItem extends Item {
    public TestItem() {
        setUnlocalizedName("testitem");
        setRegistryName("testitem");
        ModItems.ITEMS.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        VeinGenerator generator = new VeinGenerator(0.8d, 0.2d, 1, 4, 2, 2, 45, 70, ModGenerators.testOreGen, new StonePredicate(), OreGenEvent.GenerateMinable.EventType.CUSTOM);
        generator.generate(worldIn, new Random(worldIn.getSeed()), pos);
        return EnumActionResult.SUCCESS;
    }

    private EnumActionResult geneateTest(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //参数：矿脉最大半径R(5到R之间的随机数)，矿脉生成概率P，矿物层数OreStratum，矿层高度OreDepositHeight，石层高度StoneHeight，矿层矿物种类，矿层矿物比重，矿脉最低高度minHeight，矿脉最高高度maxHeight,总高度veinHeight
        int range = 8;
        int oreStratum = 5;
        int oreDepositHeight = 4;
        int stoneHeight = 2;
        int minHeight = 64;
        int maxHeight = 72;
        int veinHeight = (oreDepositHeight + stoneHeight) * oreStratum - stoneHeight;

        for (int i = 0; i < oreStratum; i++) {
            for (int j = 0; j < oreDepositHeight; j++) {

                int x = pos.getX();
                int y = pos.getY() + j + (stoneHeight + oreDepositHeight) * i;
                int z = pos.getZ();

                //半径
                int radius = (4 + range) + (int) (range * Math.sin(180 * ((double) (j + (stoneHeight + oreDepositHeight) * i) / (double) veinHeight)));


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