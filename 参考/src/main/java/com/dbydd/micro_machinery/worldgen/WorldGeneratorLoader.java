package com.dbydd.micro_machinery.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGeneratorLoader {

    private final WorldGenerator generator;
    private BlockPos pos;

    public WorldGeneratorLoader(WorldGenerator generator) {
        MinecraftForge.ORE_GEN_BUS.register(this);
        this.generator = generator;
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event) {
            if (!event.getPos().equals(this.pos)) {
                this.pos = event.getPos();
                generator.generate(event.getWorld(), event.getRand(), event.getPos());
            }
    }

}
