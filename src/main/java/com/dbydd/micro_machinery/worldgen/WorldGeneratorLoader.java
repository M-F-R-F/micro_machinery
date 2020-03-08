package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.init.ModGenerators;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGeneratorLoader {

    private BlockPos pos;

    public WorldGeneratorLoader() {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event) {
        for (WorldGenerator generator : ModGenerators.worldGenerators)
            if (!event.getPos().equals(this.pos)) {
                this.pos = event.getPos();
                generator.generate(event.getWorld(), event.getRand(), event.getPos());
            }
    }

}
