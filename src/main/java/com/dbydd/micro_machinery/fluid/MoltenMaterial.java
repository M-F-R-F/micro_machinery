package com.dbydd.micro_machinery.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MoltenMaterial extends Fluid {

	public MoltenMaterial(String fluidName, ResourceLocation still, ResourceLocation flowing, int temperature) {
		super(fluidName, still, flowing);
		// TODO Auto-generated constructor stub
		this.setTemperature(temperature);
		this.setLuminosity(13);
		this.setViscosity(4800);
	}

}
