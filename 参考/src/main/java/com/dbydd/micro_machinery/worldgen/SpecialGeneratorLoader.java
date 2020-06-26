package com.dbydd.micro_machinery.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpecialGeneratorLoader {
    private final SpecialGenerator generator;
    private BlockPos pos;

    public SpecialGeneratorLoader(SpecialGenerator generator) {
        MinecraftForge.ORE_GEN_BUS.register(this);
        this.generator = generator;
    }

    @SubscribeEvent
    public void onOreGen(OreGenEvent.GenerateMinable event) {
        if (event.getType() == generator.recipe.getEventType()) {
            generator.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }
}
