package mfrf.micro_machinery.worldgen;

import com.mojang.datafixers.util.Pair;
import mfrf.micro_machinery.utils.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class VeinFeature extends Feature<VeinFeatureConfig> {

    public VeinFeature() {
        super(VeinFeatureConfig.CODEC);
        //todo 修改为structure
    }

    private boolean generateVein(WorldGenLevel worldIn, BlockPos pos, RandomSource rand, VeinFeatureConfig config) {
        int oreStratum = config.getOreStratum();
        int stoneHeight = config.getStoneHeight();
        int oreDepositHeight = config.getOreDepositHeight();
        int range = config.getRange();
        int veinHeight = config.getVeinHeight();
        double generateChancePerOre = config.getGenerateChancePerOre();
        RuleTest predicate = config.getPredicate();
        List<Pair<BlockState, Double>> blocks = config.getBlocks();
        for (int i = 0; i < oreStratum; i++) {
            for (int j = 0; j < oreDepositHeight; j++) {

                int x = pos.getX();
                int y = pos.getY() + j + (stoneHeight + oreDepositHeight) * i;
                int z = pos.getZ();
                int x1 = x + rand.nextInt(2 * range);
                int z1 = z + rand.nextInt(2 * range);
                int x2 = x1 + rand.nextInt(2 * range);
                int z2 = z1 + rand.nextInt(2 * range);
                //半径
                int radius = rand.nextInt(6) + range + (int) (0.8 * range * Math.sin(180 * ((double) (j + (stoneHeight + oreDepositHeight) * i) / (double) veinHeight)));

                BlockPos beginPos = new BlockPos(x, y, z);
                BlockPos secondPos = new BlockPos(x1, y, z1);
                BlockPos thirdPos = new BlockPos(x2, y, z2);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x, z, radius);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x1, z1, radius);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x2, z2, radius);
                return true;
            }
        }
        return false;
    }

    private void generateMiniVein(WorldGenLevel worldIn, RandomSource rand, double generateChancePerOre, RuleTest predicate, List<Pair<BlockState, Double>> oreGenList, int y, int x1, int z1, int radius) {
        for (int rx1 = x1 - radius; rx1 <= radius + x1; rx1++) {
            for (int rz1 = z1 - radius; rz1 <= radius + z1; rz1++) {
                BlockPos position = new BlockPos(rx1, y, rz1);
                if ((Math.pow((x1 - rx1), 2) + Math.pow((z1 - rz1), 2)) <= Math.pow((radius), 2)) {
                    BlockState blockState = worldIn.getChunk(position).getBlockState(position);
                    if (RandomUtils.outputBooleanByChance(rand::nextDouble, generateChancePerOre) && predicate.test(blockState, rand)) {
                        RandomUtils.setRandmonBlockByList(rand, oreGenList, blockState, state -> setBlock(worldIn, position, state));
                    }

                }
            }
        }
    }


    @Override
    public boolean place(FeaturePlaceContext<VeinFeatureConfig> pContext) {
        BlockPos pos = pContext.origin();
        RandomSource rand = pContext.random();
        WorldGenLevel level = pContext.level();
        VeinFeatureConfig config = pContext.config();
        int posX = pos.getX() + rand.nextInt(16);
        int posZ = pos.getZ() + rand.nextInt(16);
        int tempY = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, posX, posZ) - (config.getMinHeight() + config.getVeinHeight());
        if (tempY > 0) {
            int posY = config.getMinHeight() + rand.nextInt(tempY);
            return generateVein(level, new BlockPos(posX, posY, posZ), rand, config);
        }
        return true;
    }

}
