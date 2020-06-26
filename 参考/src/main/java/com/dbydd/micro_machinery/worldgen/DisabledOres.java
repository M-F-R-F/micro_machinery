package com.dbydd.micro_machinery.worldgen;

import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.recipes.oregen.OreGenRecipe;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DisabledOres {

    @SubscribeEvent
    public static void disableOres(OreGenEvent.GenerateMinable event) {
        for (OreGenRecipe recipes : ModGenerators.oreSpecialGeneratorRecipes) {
            if (event.getType().equals(recipes.getEventType())) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

}
