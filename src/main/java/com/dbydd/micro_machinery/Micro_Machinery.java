package com.dbydd.micro_machinery;

import com.dbydd.micro_machinery.network.AnvilButtonEventPackage;
import com.dbydd.micro_machinery.network.KlinButtonEventPackage;
import com.dbydd.micro_machinery.proxy.CommonProxy;
import com.dbydd.micro_machinery.tabs.micro_machinery;
import com.dbydd.micro_machinery.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Micro_Machinery {

    public static final CreativeTabs Micro_Machinery = new micro_machinery("Micro machinery");
    @Mod.Instance
    public static com.dbydd.micro_machinery.Micro_Machinery instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    private static SimpleNetworkWrapper network;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegisteries(event);
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        network.registerMessage(new KlinButtonEventPackage.MessageHandler(), KlinButtonEventPackage.class, 1, Side.SERVER);
        network.registerMessage(new AnvilButtonEventPackage.MessageHandler(), AnvilButtonEventPackage.class, 2, Side.SERVER);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public static void Postinit(FMLPostInitializationEvent event) {
        RegistryHandler.postInitRegistries(event);
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }

}