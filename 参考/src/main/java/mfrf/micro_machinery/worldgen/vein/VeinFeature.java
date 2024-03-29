package mfrf.micro_machinery.worldgen.vein;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.utils.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Random;

public class VeinFeature extends Feature<VeinFeatureConfig> {
    public static final RegistryObject<VeinFeature> VEIN_FEATURE = MicroMachinery.FEATURE_REGISTER.register("vein_feature", () -> new VeinFeature(VeinFeatureConfig.CODEC));
    //todo modify

    public VeinFeature(Codec<VeinFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    protected static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value) {
        return (F) (Registry.<Feature<?>>register(Registry.f_122839_, key, value));
    }

    public static void Init() {

    }

    @Override
    public boolean place(FeaturePlaceContext<VeinFeatureConfig> pContext) {
        BlockPos pos = pContext.origin();
        Random rand = pContext.m_159776_();
        ChunkGenerator worldIn = pContext.chunkGenerator();
        WorldGenLevel accessor = pContext.level();
        VeinFeatureConfig config = pContext.config();
        int posX = pos.getX() + rand.nextInt(16);
        int posZ = pos.getZ() + rand.nextInt(16);
        int tempY = worldIn.m_142647_(posX, posZ, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, accessor) - (config.getMinHeight() + config.getVeinHeight());
        if (tempY > 0) {
            int posY = config.getMinHeight() + rand.nextInt(tempY);
            generateVein(accessor, new BlockPos(posX, posY, posZ), rand, config);
        }
        return true;

    }

    private void generateVein(LevelAccessor worldIn, BlockPos pos, Random rand, VeinFeatureConfig config) {
        int oreStratum = config.getOreLayers();
        int stoneHeight = config.getStoneHeight();
        int oreDepositHeight = config.getOreDepositHeight();
        int range = config.getRange();
        int veinHeight = config.getVeinHeight();
        double generateChancePerOre = config.getGenerateChancePerOre();
        List<TagKey<Block>> predicate = config.getPredicate();
        List<Pair<Double, Block>> oreGenList = config.getOreGenList();
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
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, oreGenList, y, x, z, radius);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, oreGenList, y, x1, z1, radius);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, oreGenList, y, x2, z2, radius);
            }
        }
    }

    private void generateMiniVein(LevelAccessor worldIn, Random rand, double generateChancePerOre, List<TagKey<Block>> predicate, List<Pair<Double, Block>> oreGenList, int y, int x1, int z1, int radius) {
        for (int rx1 = x1 - radius; rx1 <= radius + x1; rx1++) {
            for (int rz1 = z1 - radius; rz1 <= radius + z1; rz1++) {
                BlockPos position = new BlockPos(rx1, y, rz1);
                if ((Math.pow((x1 - rx1), 2) + Math.pow((z1 - rz1), 2)) <= Math.pow((radius), 2)) {
                    if (RandomUtils.outputBooleanByChance(rand, generateChancePerOre) && predicate.stream().anyMatch(p -> worldIn.getChunk(position).getBlockState(position).is(p))) {
                        setBlock(worldIn, position, RandomUtils.outputRandmonBlockByList(rand, oreGenList));
                    }

                }
            }
        }
    }


}
