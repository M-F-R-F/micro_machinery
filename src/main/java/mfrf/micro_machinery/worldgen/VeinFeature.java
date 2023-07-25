package mfrf.micro_machinery.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mfrf.micro_machinery.registry_lists.MMFeatures;
import mfrf.micro_machinery.utils.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class VeinFeature extends Structure {

    public static final Codec<VeinFeature> CODEC = RecordCodecBuilder.<VeinFeature>mapCodec((instance) -> {
        return instance.group(
                VeinFeature.settingsCodec(instance),
                Codec.simpleMap(Codec.DOUBLE, OreConfiguration.TargetBlockState.CODEC, Keyable.forStrings(List.of("chance")::stream)).fieldOf("block_map").forGetter((p_161027_) -> {
                    return p_161027_.blocks;
                }),
                Codec.doubleRange(0, 1D).fieldOf("vein_gen_chance").forGetter(
                        VeinFeature::getVeinGenChance
                ),
                Codec.doubleRange(0, 1D).fieldOf("generate_chance_per_ore").forGetter(
                        VeinFeature::getGenerateChancePerOre
                ),
                Codec.intRange(0, 128).fieldOf("range").forGetter(
                        VeinFeature::getRange
                ),
                Codec.intRange(0, 128).fieldOf("ore_stratum").forGetter(
                        VeinFeature::getOreStratum
                ),
                Codec.intRange(0, 128).fieldOf("ore_deposit_height").forGetter(
                        VeinFeature::getOreDepositHeight
                ),
                Codec.intRange(0, 128).fieldOf("stone_height").forGetter(
                        VeinFeature::getStoneHeight
                ),
                Codec.intRange(0, 128).fieldOf("min_height").forGetter(
                        VeinFeature::getMinHeight
                ),
                Codec.intRange(0, 128).fieldOf("max_height").forGetter(
                        VeinFeature::getMaxHeight
                ),
                RuleTest.CODEC.fieldOf("predicate").forGetter(
                        VeinFeature::getPredicate
                )
        ).apply(instance, VeinFeature::new);
    }).codec();
    private final Map<Double, OreConfiguration.TargetBlockState> blocks;
    private final Double veinGenChance;
    private final Double generateChancePerOre;
    private final int range;
    private final int oreStratum;
    private final int oreDepositHeight;
    private final int stoneHeight;
    private final int minHeight;
    private final int maxHeight;
    private final int veinHeight;
    private final RuleTest predicate;

    public Double getVeinGenChance() {
        return veinGenChance;
    }

    public Double getGenerateChancePerOre() {
        return generateChancePerOre;
    }

    public int getRange() {
        return range;
    }

    public int getOreStratum() {
        return oreStratum;
    }

    public int getOreDepositHeight() {
        return oreDepositHeight;
    }

    public int getStoneHeight() {
        return stoneHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinHeight() {
        return veinHeight;
    }

    public Map<Double, OreConfiguration.TargetBlockState> getBlocks() {
        return blocks;
    }

    public RuleTest getPredicate() {
        return predicate;
    }

    public VeinFeature(Structure.StructureSettings config, Map<Double, OreConfiguration.TargetBlockState> blocks, Double veinGenChance, Double generateChancePerOre, int range, int oreStratum, int oreDepositHeight, int stoneHeight, int minHeight, int maxHeight, RuleTest predicate) {
        super(config);
        this.blocks = blocks;
        this.veinGenChance = veinGenChance;
        this.generateChancePerOre = generateChancePerOre;
        this.oreStratum = oreStratum;
        this.oreDepositHeight = oreDepositHeight;
        this.stoneHeight = stoneHeight;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinHeight = (oreDepositHeight + stoneHeight) * oreStratum - stoneHeight;
        this.predicate = predicate;
        if (range > 32) {
            this.range = 32;
        } else this.range = range;
    }

    public static void Init() {

    }

    private boolean generateVein(WorldGenLevel worldIn, BlockPos pos, RandomSource rand) {
//        int oreStratum = config.getOreStratum();
//        int stoneHeight = config.getStoneHeight();
//        int oreDepositHeight = config.getOreDepositHeight();
//        int range = config.getRange();
//        int veinHeight = config.getVeinHeight();
//        double generateChancePerOre = config.getGenerateChancePerOre();
//        RuleTest predicate = config.getPredicate();
        Map<Double, OreConfiguration.TargetBlockState> blocks = getBlocks();
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

    private void generateMiniVein(WorldGenLevel worldIn, RandomSource rand, double generateChancePerOre, RuleTest predicate, Map<Double, OreConfiguration.TargetBlockState> oreGenList, int y, int x1, int z1, int radius) {
        for (int rx1 = x1 - radius; rx1 <= radius + x1; rx1++) {
            for (int rz1 = z1 - radius; rz1 <= radius + z1; rz1++) {
                BlockPos position = new BlockPos(rx1, y, rz1);
                if ((Math.pow((x1 - rx1), 2) + Math.pow((z1 - rz1), 2)) <= Math.pow((radius), 2)) {
                    BlockState blockState = worldIn.getChunk(position).getBlockState(position);
                    if (RandomUtils.outputBooleanByChance(rand::nextDouble, generateChancePerOre) && predicate.test(blockState, rand)) {
                        RandomUtils.setRandmonBlockByList(rand, oreGenList, blockState, state -> worldIn.setBlock(position, state, 2));
                    }

                }
            }
        }
    }


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

    private static int extraSpawningChecks(GenerationContext context, VeinFeature feature, int x, int z) {
        ChunkPos pos = context.chunkPos();
        RandomSource rand = context.random();

        int height = context.chunkGenerator().getFirstOccupiedHeight(
                pos.getMinBlockX(),
                pos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState());
        int tempY = height - (feature.getMinHeight() + feature.getVeinHeight());
        if (tempY <= 0) {
            return Integer.MIN_VALUE;
        }

        UniformHeight heightProvider = UniformHeight.of(VerticalAnchor.aboveBottom(feature.minHeight), VerticalAnchor.belowTop(feature.maxHeight));
        return heightProvider.sample(rand, new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
//        WorldgenRandom random = context.random();
//        int x = random.nextInt(16);
//        int z = random.next(16);
//        int selectedHeight = extraSpawningChecks(context, this, x, z);
//        if (selectedHeight == -1) {
//            return Optional.empty();
//        }
            return Optional.empty();
//
//        // Set's our spawning blockpos's y offset to be 60 blocks up.
//        // Since we are going to have heightmap/terrain height spawning set to true further down, this will make it so we spawn 60 blocks above terrain.
//        // If we wanted to spawn on ocean floor, we would set heightmap/terrain height spawning to false and the grab the y value of the terrain with OCEAN_FLOOR_WG heightmap.
//        // Turns the chunk coordinates into actual coordinates we can use. (Gets corner of that chunk)
//        ChunkPos chunkPos = context.chunkPos();
//        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), selectedHeight, chunkPos.getMinBlockZ());
//
//        Optional<Structure.GenerationStub> structurePiecesGenerator =
//                JigsawPlacement.addPieces(
//                        context, // Used for JigsawPlacement to get all the proper behaviors done.
//                        this.startPool, // The starting pool to use to create the structure layout from
//                        this.startJigsawName, // Can be used to only spawn from one Jigsaw block. But we don't need to worry about this.
//                        this.size, // How deep a branch of pieces can go away from center piece. (5 means branches cannot be longer than 5 pieces from center piece)
//                        blockPos, // Where to spawn the structure.
//                        false, // "useExpansionHack" This is for legacy villages to generate properly. You should keep this false always.
//                        this.projectStartToHeightmap, // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
//                        // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
//                        // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
//                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
//                        this.maxDistanceFromCenter); // Maximum limit for how far pieces can spawn from center. You cannot set this bigger than 128 or else pieces gets cutoff.
//
//        /*
//         * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
//         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
//         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
//         */
//
//        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
//        return structurePiecesGenerator;
    }


    @Override
    public StructureStart generate(RegistryAccess pRegistryAccess, ChunkGenerator pChunkGenerator, BiomeSource pBiomeSource, RandomState pRandomState, StructureTemplateManager pStructureTemplateManager, long pSeed, ChunkPos pChunkPos, int p_226604_, LevelHeightAccessor pHeightAccessor, Predicate<Holder<Biome>> pValidBiome) {
//        WorldgenRandom random = context.random();
//        int x = random.nextInt(16);
//        int z = random.next(16);
//        int selectedHeight = extraSpawningChecks(context, this, x, z);
//        if (selectedHeight == -1) {
//            return Optional.empty();
//        }
        return super.generate(pRegistryAccess, pChunkGenerator, pBiomeSource, pRandomState, pStructureTemplateManager, pSeed, pChunkPos, p_226604_, pHeightAccessor, pValidBiome);
    }

    @Override
    public StructureType<?> type() {
        return MMFeatures.VEIN_FEATURE.get();
    }
}
