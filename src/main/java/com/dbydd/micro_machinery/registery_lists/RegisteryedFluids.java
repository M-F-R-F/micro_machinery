package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.fluids.MMFluidBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.HashMap;
import java.util.Map;

public class RegisteryedFluids {
    public static final MMFluidBase MOLTEN_COPPER = new MMFluidBase("molten_copper", Block.Properties.create(Material.LAVA), (text) -> text.luminosity(9).temperature(800).viscosity(5).density(2));

    public static void Init() {
    }
}
