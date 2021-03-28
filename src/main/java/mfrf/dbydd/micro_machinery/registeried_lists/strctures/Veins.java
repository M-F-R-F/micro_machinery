package mfrf.dbydd.micro_machinery.registeried_lists.strctures;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.worldgen.Predicates;
import mfrf.dbydd.micro_machinery.worldgen.VeinFeatureConfig;
import mfrf.dbydd.micro_machinery.event.VeinGenerationsRegisterEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class Veins {
    public static final Map<Double, Block> OreCopper = new HashMap<>();
    public static final Map<Double, Block> OreTin = new HashMap<>();
    public static final Map<Double, Block> OreIron = new HashMap<>();
    public static final Map<Double, Block> OreGold = new HashMap<>();
    public static final Map<Double, Block> OreCoal = new HashMap<>();
    public static final Map<Double, Block> OreIlmenite = new HashMap<>();
    public static final Map<Double, Block> OreSilver = new HashMap<>();
    public static final Map<Double, Block> OrePyrolusite = new HashMap<>();
    public static final Map<Double, Block> OreChromite = new HashMap<>();
    public static final Map<Double, Block> OreBauxite = new HashMap<>();
    public static final Map<Double, Block> OreNickel = new HashMap<>();
    public static final Map<Double, Block> OreNolanite = new HashMap<>();
    public static final Map<Double, Block> OreFerroManganese = new HashMap<>();
    public static final Map<Double, Block> NetherOrePyrolusite = new HashMap<>();
    public static final Map<Double, Block> NetherOreGraphite = new HashMap<>();
    public static final Map<Double, Block> NetherOreBauxite = new HashMap<>();
    public static final Map<Double, Block> NetherOreFerroManganese = new HashMap<>();
    public static final Map<Double, Block> NetherOreNolanite = new HashMap<>();
    public static final Map<Double, Block> EndOreTunstite = new HashMap<>();

    public static final VeinGenerationsRegisterEventHandler copper = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_COPPER_VEIN.get(), 0.2, 8, 3, 3, 3, 3, 62, OreCopper, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler tin = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_TIN_VEIN.get(), 0.2, 7, 2, 5, 3, 32, 80, OreTin, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler iron = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_IRON_VEIN.get(), 0.2, 8, 4, 3, 3, 3, 80, OreIron, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler gold = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_GOLD_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 32, OreGold, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler coal = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_COAL_VEIN.get(), 0.2, 10, 5, 3, 3, 3, 120, OreCoal, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler ilmenite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_ILMENITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 32, OreIlmenite, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler silver = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_SILVER_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreSilver, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler pyrolusite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_PYROLUSITE_VEIN.get(), 0.2, 6, 2, 4, 3, 16, 80, OrePyrolusite, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler chromite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_CHROMITE_VEIN.get(), 0.2, 7, 3, 3, 3, 3, 62, OreChromite, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler bauxite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_BAUXITE_VEIN.get(), 0.2, 6, 1, 4, 3, 32, 80, OreBauxite, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler nickel = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NICKEL_VEIN.get(), 0.2, 8, 3, 3, 3, 3, 62, OreNickel, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler nolanite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NOLANITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreNolanite, Predicates.OVERWORLD));
    public static final VeinGenerationsRegisterEventHandler ferromanganese = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_FERROMANGANESE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreFerroManganese, Predicates.OVERWORLD));

    public static final VeinGenerationsRegisterEventHandler netherpyrolusite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERPYROLUSITE_VEIN.get(), 0.2, 6, 3, 3, 3, 60, 120, NetherOrePyrolusite, Predicates.NETHER));
    public static final VeinGenerationsRegisterEventHandler nethergraphite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERGRAPHITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, NetherOreGraphite, Predicates.NETHER));
    public static final VeinGenerationsRegisterEventHandler netherbauxite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERBAUXITE_VEIN.get(), 0.2, 6, 3, 3, 3, 48, 120, NetherOreBauxite, Predicates.NETHER));
    public static final VeinGenerationsRegisterEventHandler netherferromanganese = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERFERROMANGANESE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 120, NetherOreFerroManganese, Predicates.NETHER));
    public static final VeinGenerationsRegisterEventHandler nethernolanite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERNOLANITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 120, NetherOreNolanite, Predicates.NETHER));

    public static final VeinGenerationsRegisterEventHandler tunstite = new VeinGenerationsRegisterEventHandler(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_TUNSTITE_VEIN.get(), 0.04, 8, 5, 3, 3, 3, 62, EndOreTunstite, Predicates.END));

    static{
        OreCopper.put(0.8d, RegisteredBlocks.ORECOPPER);
        OreCopper.put(0.15d, RegisteredBlocks.ORETIN);
        OreCopper.put(0.05d, Blocks.GOLD_ORE);
        OreTin.put(0.8d, RegisteredBlocks.ORETIN);
        OreTin.put(0.2d, RegisteredBlocks.ORECOPPER);
        OreIron.put(0.8d, Blocks.IRON_ORE);
        OreIron.put(0.09d, Blocks.GOLD_ORE);
        OreIron.put(0.11d, RegisteredBlocks.ORENICKEL);
        OreGold.put(0.7d, Blocks.GOLD_ORE);
        OreGold.put(0.15d, RegisteredBlocks.ORENOLANITE);
        OreGold.put(0.1d, RegisteredBlocks.ORESILVER);
        OreGold.put(0.05d, RegisteredBlocks.ORECOPPER);
        OreCoal.put(0.8d, Blocks.COAL_ORE);
        OreCoal.put(0.1d, Blocks.COAL_BLOCK);
        OreCoal.put(0.07d, RegisteredBlocks.OREGRAPHITE);
        OreCoal.put(0.03d, Blocks.DIAMOND_ORE);
        OreIlmenite.put(0.5d, Blocks.IRON_ORE);
        OreIlmenite.put(0.4d, RegisteredBlocks.OREILMENITE);
        OreIlmenite.put(0.1d, RegisteredBlocks.OREFERROMANGANESE);
        OreSilver.put(0.7d, RegisteredBlocks.ORESILVER);
        OreSilver.put(0.2d, Blocks.LAPIS_ORE);
        OreSilver.put(0.1d, RegisteredBlocks.ORECOPPER);
        OrePyrolusite.put(0.8d, RegisteredBlocks.OREPYROLUSITE);
        OrePyrolusite.put(0.2d, RegisteredBlocks.OREFERROMANGANESE);
        OreChromite.put(0.6d, Blocks.IRON_ORE);
        OreChromite.put(0.3d, RegisteredBlocks.ORECHROMITE);
        OreChromite.put(0.1d, RegisteredBlocks.ORENICKEL);
        OreBauxite.put(0.6d, RegisteredBlocks.OREBAUXITE);
        OreBauxite.put(0.3d, Blocks.CLAY);
        OreBauxite.put(0.06d, RegisteredBlocks.ORECOPPER);
        OreBauxite.put(0.04d, Blocks.IRON_ORE);
        OreNickel.put(0.6d, RegisteredBlocks.ORENICKEL);
        OreNickel.put(0.25d, Blocks.IRON_ORE);
        OreNickel.put(0.1d, RegisteredBlocks.ORECOPPER);
        OreNickel.put(0.05d, RegisteredBlocks.OREFERROMANGANESE);
        OreNolanite.put(0.7d, Blocks.IRON_ORE);
        OreNolanite.put(0.2d, RegisteredBlocks.ORENOLANITE);
        OreNolanite.put(0.1d, RegisteredBlocks.OREILMENITE);
        OreFerroManganese.put(0.5d, Blocks.IRON_ORE);
        OreFerroManganese.put(0.3d, RegisteredBlocks.OREFERROMANGANESE);
        OreFerroManganese.put(0.2d, RegisteredBlocks.OREPYROLUSITE);
        NetherOrePyrolusite.put(0.8d, RegisteredBlocks.OREPYROLUSITE_NETHER);
        NetherOrePyrolusite.put(0.2d, RegisteredBlocks.OREFERROMANGANESE_NETHER);
        NetherOreGraphite.put(0.8d, RegisteredBlocks.OREGRAPHITE_NETHER);
        NetherOreGraphite.put(0.2d, Blocks.COAL_BLOCK);
        NetherOreBauxite.put(0.7d, RegisteredBlocks.OREBAUXITE_NETHER);
        NetherOreBauxite.put(0.2d, Blocks.CLAY);
        NetherOreBauxite.put(0.1d, Blocks.GRAVEL);
        NetherOreFerroManganese.put(0.7d, RegisteredBlocks.OREFERROMANGANESE_NETHER);
        NetherOreFerroManganese.put(0.3d, RegisteredBlocks.OREPYROLUSITE_NETHER);
        NetherOreNolanite.put(0.6d, RegisteredBlocks.ORENOLANITE_NETHER);
        NetherOreNolanite.put(0.25d, RegisteredBlocks.OREBAUXITE_NETHER);
        NetherOreNolanite.put(0.15d, Blocks.NETHER_QUARTZ_ORE);
        EndOreTunstite.put(1.0d, RegisteredBlocks.ORETUNSTITE);
    }

    public static final void Init(){

    }
}
