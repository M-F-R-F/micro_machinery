package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.util.RandomUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;
import java.util.TreeMap;
import java.util.function.Predicate;

public class EndVeinGenerator extends WorldGenerator {
    private final Double veinGenChance;
    private final Double generateChancePerOre;
    private final int range;
    private final int oreStratum;
    private final int oreDepositHeight;
    private final int stoneHeight;
    private final int minHeight;
    private final int maxHeight;
    private final int veinHeight;
    private final TreeMap<Double, IBlockState> oreGenList;
    private final Predicate<IBlockState> predicate;

    public EndVeinGenerator(Double veinGenChance, Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, TreeMap<Double, IBlockState> oreGenList, Predicate<IBlockState> predicate) {
        this.veinGenChance = veinGenChance;
        this.generateChancePerOre = generateChancePerOre;
        this.range = range;
        this.oreStratum = oreStratum;
        this.oreDepositHeight = oreDepositHeight;
        this.stoneHeight = stoneHeight;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.oreGenList = oreGenList;
        this.predicate = predicate;
        this.veinHeight = (oreDepositHeight + stoneHeight) * oreStratum - stoneHeight;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (RandomUtils.outputBooleanByChance(rand, veinGenChance)) {
            int posX = position.getX() + rand.nextInt(16);
            int posZ = position.getZ() + rand.nextInt(16);
            int tempY = worldIn.getHeight(posX, posZ) - (minHeight + veinHeight);
            if (!(tempY < 0)) {
                int posY = minHeight + rand.nextInt(tempY);
                generateVein(worldIn, new BlockPos(posX, posY, posZ), rand);
            }

        }
        return true;
    }

    private void generateVein(World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < oreStratum; i++) {
            for (int j = 0; j < oreDepositHeight; j++) {

                int x = pos.getX();
                int y = pos.getY() + j + (stoneHeight + oreDepositHeight) * i;
                int z = pos.getZ();
                int x1 = x + rand.nextInt(2 * range) - range;
                int z1 = z + rand.nextInt(2 * range) - range;
                int x2 = x1 + rand.nextInt(2 * range) - range;
                int z2 = z1 + rand.nextInt(2 * range) - range;
                //半径
                int radius = rand.nextInt(6) + range + (int) (0.8 * range * Math.sin(180 * ((double) (j + (stoneHeight + oreDepositHeight) * i) / (double) veinHeight)));

                BlockPos beginPos = new BlockPos(x, y, z);
                BlockPos secondPos = new BlockPos(x1, y, z1);
                BlockPos thirdPos = new BlockPos(x2, y, z2);
                for (int rx = x - radius; rx <= radius + x; rx++) {
                    for (int rz = z - radius; rz <= radius + z; rz++) {
                        BlockPos position = new BlockPos(rx, y, rz);
                        if ((Math.pow((x - rx), 2) + Math.pow((z - rz), 2)) <= Math.pow((radius), 2)) {
                            if (RandomUtils.outputBooleanByChance(rand, generateChancePerOre) && predicate.test(worldIn.getBlockState(position))) {
                                worldIn.setBlockState(position, RandomUtils.outputRandmonBlockByList(rand, oreGenList));
                            }
                        }
                    }
                }
                for (int rx1 = x1 - radius; rx1 <= radius + x1; rx1++) {
                    for (int rz1 = z1 - radius; rz1 <= radius + z1; rz1++) {
                        BlockPos position = new BlockPos(rx1, y, rz1);
                        if ((Math.pow((x1 - rx1), 2) + Math.pow((z1 - rz1), 2)) <= Math.pow((radius), 2)) {
                            if (RandomUtils.outputBooleanByChance(rand, generateChancePerOre) && predicate.test(worldIn.getBlockState(position))) {
                                worldIn.setBlockState(position, RandomUtils.outputRandmonBlockByList(rand, oreGenList));
                            }
                        }
                    }
                }
                for (int rx2 = x2 - radius; rx2 <= radius + x2; rx2++) {
                    for (int rz2 = z2 - radius; rz2 <= radius + z2; rz2++) {
                        BlockPos position = new BlockPos(rx2, y, rz2);
                        if ((Math.pow((x2 - rx2), 2) + Math.pow((z2 - rz2), 2)) <= Math.pow((radius), 2)) {
                            if (RandomUtils.outputBooleanByChance(rand, generateChancePerOre) && predicate.test(worldIn.getBlockState(position))) {
                                worldIn.setBlockState(position, RandomUtils.outputRandmonBlockByList(rand, oreGenList));
                            }
                        }
                    }
                }
            }
        }
    }
}
