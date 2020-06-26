package com.dbydd.micro_machinery.util.handlers;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TESRTestTile;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityTickableEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityHandGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModFluids;
import com.dbydd.micro_machinery.init.ModGenerators;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.renderer.TesrRender;
import com.dbydd.micro_machinery.renderer.TileEntityHandGeneratorRender;
import com.dbydd.micro_machinery.renderer.TileentityCableSpecialRenderer;
import com.dbydd.micro_machinery.util.IHasModel;
import com.dbydd.micro_machinery.worldgen.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

//import com.dbydd.micro_machinery.init.ModBlocks;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileRegisteryHandler.registerTileEntities();

    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain(Reference.MODID);

        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTickableEnergyCableWithoutGenerateForce.class, new TileentityCableSpecialRenderer<TileEntityTickableEnergyCableWithoutGenerateForce>());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyCableWithoutGenerateForce.class, new TileentityCableSpecialRenderer<TileEntityEnergyCableWithoutGenerateForce>());
        ClientRegistry.bindTileEntitySpecialRenderer(TESRTestTile.class, new TesrRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHandGenerator.class, new TileEntityHandGeneratorRender());
//        ClientRegistry.bindTileEntitySpecialRenderer(TESRTestTile.class, new FastTesrRender());
    }

    public static void preInitRegisteries(FMLPreInitializationEvent event) {
        ModFluids.registerFluids(ModFluids.MOLTEN_COPPER);
        ModFluids.registerFluids(ModFluids.MOLTEN_TIN);
        ModFluids.registerFluids(ModFluids.MOLTEN_BRONZE);
        ModFluids.registerFluids(ModFluids.MOLTEN_STEEL);
        ModFluids.registerFluids(ModFluids.MOLTEN_SS);
        ModFluids.registerFluids(ModFluids.MOLTEN_TUNGSTEN);
        ModFluids.registerFluids(ModFluids.MOLTEN_NICKEL);
        ModFluids.registerFluids(ModFluids.MOLTEN_INVAR);
        ModFluids.registerFluids(ModFluids.MOLTEN_HSS);
        ModFluids.registerFluids(ModFluids.MOLTEN_SILVER);
        ModFluids.registerFluids(ModFluids.MOLTEN_GOLD);
        ModFluids.registerFluids(ModFluids.MOLTEN_MANGANESE);
        ModFluids.registerFluids(ModFluids.MOLTEN_CHROMIUM);
        ModFluids.registerFluids(ModFluids.MOLTEN_VANADIUM);
        ModFluids.registerFluids(ModFluids.MOLTEN_COBALT);
        ModFluids.registerFluids(ModFluids.MOLTEN_TITANIUM);
        ModFluids.registerFluids(ModFluids.MOLTEN_ALUMINUM);
        ModFluids.registerFluids(ModFluids.MOLTEN_NCALLOY);
        ModFluids.registerFluids(ModFluids.MOLTEN_IRON);
        ModFluids.registerFluids(ModFluids.MOLTEN_FERROCHROME);
        ModFluids.registerFluids(ModFluids.GOLDEN_APPLE_JUICE);
        ModFluids.registerFluids(ModFluids.APPLE_JUICE);
        ModFluids.registerFluids(ModFluids.ETHENE);
    }

    public static void initRegistries(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Micro_Machinery.instance, new GUIHandler());

        MinecraftForge.ORE_GEN_BUS.register(DisabledOres.class);
        ModGenerators.initMaps();

        for (WorldGenerator generator : ModGenerators.worldGenerators) {
            new WorldGeneratorLoader(generator);
        }
        for (SpecialGenerator generator : ModGenerators.worldSpecialGenerators) {
            new SpecialGeneratorLoader(generator);
        }

        for(VeinGenerator generator : ModGenerators.worldVeinGenerators){
            new VeinGeneratorLoader(generator);
        }

        for(EndVeinGenerator generator: ModGenerators.endVeinGenerators){
            new EndVeinGeneratorLoader(generator);
        }
    }

    public static void postInitRegistries(FMLPostInitializationEvent event) {

    }

    public static void serverRegistries(FMLServerStartingEvent event) {
    }
}
