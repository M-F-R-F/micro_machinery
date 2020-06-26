package com.dbydd.micro_machinery.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EndVeinGeneratorLoader {
    private final EndVeinGenerator generator;
    private BlockPos pos;

    public EndVeinGeneratorLoader(EndVeinGenerator generator) {
        MinecraftForge.ORE_GEN_BUS.register(this);
        this.generator = generator;
    }

    @SubscribeEvent
    public void onOreGen(OreGenEvent.Post event) {
        if (event.getWorld().provider.getDimension() == 1) {
            event.getRand().nextInt();
            event.getRand().nextDouble();
            generator.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }
}
