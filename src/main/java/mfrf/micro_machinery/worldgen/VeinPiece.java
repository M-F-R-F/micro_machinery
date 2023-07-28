package mfrf.micro_machinery.worldgen;

import com.mojang.datafixers.util.Pair;
import mfrf.micro_machinery.registry_lists.worldgen.MMStructureThings;
import mfrf.micro_machinery.utils.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class VeinPiece extends ScatteredFeaturePiece {
    private VeinStructureConfig config;

    public VeinPiece(VeinStructureConfig config, WorldgenRandom pRandom, int x, int z) {
        super(MMStructureThings.VEIN_PIECE.get(), x, 64, z, config.getRange(), config.getVeinHeight(), config.getVeinHeight(), Direction.NORTH);
        this.config = config;
    }

    public VeinPiece(StructurePieceSerializationContext ctx, CompoundTag pTag) {
        super(MMStructureThings.VEIN_PIECE.get(), pTag);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager pStructureManager, ChunkGenerator pGenerator, RandomSource rand, BoundingBox pBox, ChunkPos pChunkPos, BlockPos pos) {

        int posX = pos.getX() + rand.nextInt(16);
        int posZ = pos.getZ() + rand.nextInt(16);
        int tempY = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, posX, posZ) - (config.getMinHeight() + config.getVeinHeight());
        if (tempY > 0) {
            int posY = config.getMinHeight() + rand.nextInt(tempY);
            generateVein(level, new BlockPos(posX, posY, posZ), rand, config, pBox);
        }
    }


    private boolean generateVein(WorldGenLevel worldIn, BlockPos pos, RandomSource rand, VeinStructureConfig config, BoundingBox box) {
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
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x, z, radius, box);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x1, z1, radius, box);
                generateMiniVein(worldIn, rand, generateChancePerOre, predicate, blocks, y, x2, z2, radius, box);
                return true;
            }
        }
        return false;
    }

    private void generateMiniVein(WorldGenLevel worldIn, RandomSource rand, double generateChancePerOre, RuleTest predicate, List<Pair<BlockState, Double>> oreGenList, int y, int x1, int z1, int radius, BoundingBox box) {
        for (int rx1 = x1 - radius; rx1 <= radius + x1; rx1++) {
            for (int rz1 = z1 - radius; rz1 <= radius + z1; rz1++) {
                BlockPos position = new BlockPos(rx1, y, rz1);
                if ((Math.pow((x1 - rx1), 2) + Math.pow((z1 - rz1), 2)) <= Math.pow((radius), 2)) {
                    BlockState blockState = worldIn.getChunk(position).getBlockState(position);
                    if (RandomUtils.outputBooleanByChance(rand::nextDouble, generateChancePerOre) && predicate.test(blockState, rand)) {
                        RandomUtils.setRandmonBlockByList(rand, oreGenList, blockState, state -> this.placeBlock(worldIn, state, position.getX(), position.getY(), position.getZ(), box));
                    }

                }
            }
        }
    }

}
