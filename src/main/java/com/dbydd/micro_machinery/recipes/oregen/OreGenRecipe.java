package com.dbydd.micro_machinery.recipes.oregen;

import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.worldgen.OreGenerator;
import com.dbydd.micro_machinery.worldgen.SpecialGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class OreGenRecipe {
    private Block ore;
    private IBlockState[] replaceBlocks;
    private int dimision;
    private int minHeight;
    private int allowedYOffset;
    private int count;
    private int generateCountPerChunk;

    public OreGenRecipe(Block ore, IBlockState[] replaceBlocks, int dimision, int minHeight, int allowedYOffset, int count, int generateCountPerChunk, Boolean isSpecialGenerate, OreGenEvent.GenerateMinable.EventType eventType) {
        this.replaceBlocks = replaceBlocks;
        this.ore = ore;
        this.dimision = dimision;
        this.minHeight = minHeight;
        this.allowedYOffset = allowedYOffset;
        this.count = count;
        this.generateCountPerChunk = generateCountPerChunk;
        if (isSpecialGenerate) {
            ModGenerators.oreSpecialGeneratorRecipes.add(this);
            ModGenerators.worldSpecialGenerators.add(new SpecialGenerator(ore.getDefaultState(), count, replaceBlocks, eventType));
        } else {
            ModGenerators.oreGeneratorRecipes.add(this);
            ModGenerators.worldGenerators.add(new OreGenerator(ore.getDefaultState(), this));
        }
    }

    public boolean isBlockMatch(IBlockState block) {
        for (IBlockState Blocks : replaceBlocks) {
            if (Blocks == block) return true;
        }
        return false;
    }

    public IBlockState[] getReplaceBlocks() {
        return replaceBlocks;
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


    public int getDimision() {
        return dimision;
    }


    public int getGenerateCountPerChunk() {
        return generateCountPerChunk;
    }
}
