package com.dbydd.micro_machinery;

import com.dbydd.micro_machinery.proxy.CommonProxy;
import com.dbydd.micro_machinery.tabs.micro_machinery;
import com.dbydd.micro_machinery.util.handlers.RegistryHandler;
import com.dbydd.micro_machinery.init.ModFluids;
import com.dbydd.micro_machinery.proxy.ClientProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
		
		public static final CreativeTabs Micro_Machinery = new micro_machinery("Micro machinery");
	
		static {FluidRegistry.enableUniversalBucket();}
		
		@Instance
		public static Main instance;
		
		@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
		public static CommonProxy proxy;

		@EventHandler
		public static void PreInit(FMLPreInitializationEvent event)
		{
			RegistryHandler.preInitRegisteries(event);
			
		}
		
		@EventHandler
		public static void init(FMLInitializationEvent event)
		{
			
		}
		
		@EventHandler
		public static void Postinit(FMLPostInitializationEvent event)
		{
			
		}
		
	}