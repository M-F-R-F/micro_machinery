package com.dbydd.micro_machinery.recipes.oregen;

import net.minecraft.block.Block;

public class OreGenRecipe {
    private Block ore;
    private Block[] replaceBlocks;
    private int dimision;
    private int minHeight;
    private int allowedYOffset;
    private int count;
    private int generateCountPerChunk;

    public OreGenRecipe(Block ore, Block[] replaceBlocks, int dimision, int minHeight, int allowedYOffset, int count, int generateCountPerChunk) {
        this.ore = ore;
        this.replaceBlocks = replaceBlocks;
        this.dimision = dimision;
        this.minHeight = minHeight;
        this.allowedYOffset = allowedYOffset;
        this.count = count;
        this.generateCountPerChunk = generateCountPerChunk;
    }

    public boolean isBlockMatch(Block block) {
        for (Block Blocks : replaceBlocks) {
            if (Blocks == block) return true;
        }
        return false;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getAllowedYOffset() {
        return allowedYOffset;
    }

    public int getCount() {
        return count;
    }

    public Block getOre() {
        return ore;
    }

    public Block[] getReplaceBlocks() {
        return replaceBlocks;
    }

    public int getDimision() {
        return dimision;
    }


    public int getGenerateCountPerChunk() {
        return generateCountPerChunk;
    }
}
