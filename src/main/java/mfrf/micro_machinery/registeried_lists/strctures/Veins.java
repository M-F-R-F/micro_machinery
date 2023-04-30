package mfrf.micro_machinery.registeried_lists.strctures;

import com.mojang.datafixers.util.Pair;
import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.event.VeinGenerationsRegisterEventHandler;
import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.micro_machinery.worldgen.Predicates;
import mfrf.micro_machinery.worldgen.VeinFeatureConfig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class Veins {

    public static final List<Pair<Double, Block>> OreCopper = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreTin = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreIron = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreGold = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreCoal = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreIlmenite = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreSilver = new ArrayList<>();
    public static final List<Pair<Double, Block>> OrePyrolusite = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreChromite = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreBauxite = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreNickel = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreNolanite = new ArrayList<>();
    public static final List<Pair<Double, Block>> OreFerroManganese = new ArrayList<>();
    public static final List<Pair<Double, Block>> NetherOrePyrolusite = new ArrayList<>();
    public static final List<Pair<Double, Block>> NetherOreGraphite = new ArrayList<>();
    public static final List<Pair<Double, Block>> NetherOreBauxite = new ArrayList<>();
    public static final List<Pair<Double, Block>> NetherOreFerroManganese = new ArrayList<>();
    public static final List<Pair<Double, Block>> NetherOreNolanite = new ArrayList<>();
    public static final List<Pair<Double, Block>> EndOreTunstite = new ArrayList<>();

    public static final VeinFeatureConfig copper = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_COPPER_VEIN.get(), 0.2, 8, 3, 3, 3, 3, 62, OreCopper, Predicates.OVERWORLD));
    public static final VeinFeatureConfig tin = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_TIN_VEIN.get(), 0.2, 7, 2, 5, 3, 32, 80, OreTin, Predicates.OVERWORLD));
    public static final VeinFeatureConfig iron = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_IRON_VEIN.get(), 0.2, 8, 4, 3, 3, 3, 80, OreIron, Predicates.OVERWORLD));
    public static final VeinFeatureConfig gold = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_GOLD_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 32, OreGold, Predicates.OVERWORLD));
    public static final VeinFeatureConfig coal = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_COAL_VEIN.get(), 0.2, 10, 5, 3, 3, 3, 120, OreCoal, Predicates.OVERWORLD));
    public static final VeinFeatureConfig ilmenite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_ILMENITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 32, OreIlmenite, Predicates.OVERWORLD));
    public static final VeinFeatureConfig silver = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_SILVER_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreSilver, Predicates.OVERWORLD));
    public static final VeinFeatureConfig pyrolusite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_PYROLUSITE_VEIN.get(), 0.2, 6, 2, 4, 3, 16, 80, OrePyrolusite, Predicates.OVERWORLD));
    public static final VeinFeatureConfig chromite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_CHROMITE_VEIN.get(), 0.2, 7, 3, 3, 3, 3, 62, OreChromite, Predicates.OVERWORLD));
    public static final VeinFeatureConfig bauxite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_BAUXITE_VEIN.get(), 0.2, 6, 1, 4, 3, 32, 80, OreBauxite, Predicates.OVERWORLD));
    public static final VeinFeatureConfig nickel = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NICKEL_VEIN.get(), 0.2, 8, 3, 3, 3, 3, 62, OreNickel, Predicates.OVERWORLD));
    public static final VeinFeatureConfig nolanite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NOLANITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreNolanite, Predicates.OVERWORLD));
    public static final VeinFeatureConfig ferromanganese = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_FERROMANGANESE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, OreFerroManganese, Predicates.OVERWORLD));

    public static final VeinFeatureConfig netherpyrolusite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERPYROLUSITE_VEIN.get(), 0.2, 6, 3, 3, 3, 60, 120, NetherOrePyrolusite, Predicates.NETHER));
    public static final VeinFeatureConfig nethergraphite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERGRAPHITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 48, NetherOreGraphite, Predicates.NETHER));
    public static final VeinFeatureConfig netherbauxite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERBAUXITE_VEIN.get(), 0.2, 6, 3, 3, 3, 48, 120, NetherOreBauxite, Predicates.NETHER));
    public static final VeinFeatureConfig netherferromanganese = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERFERROMANGANESE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 120, NetherOreFerroManganese, Predicates.NETHER));
    public static final VeinFeatureConfig nethernolanite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_NETHERNOLANITE_VEIN.get(), 0.2, 6, 3, 3, 3, 3, 120, NetherOreNolanite, Predicates.NETHER));

    public static final VeinFeatureConfig tunstite = VeinGenerationsRegisterEventHandler.addConfig(new VeinFeatureConfig(Config.GENERATE_CHANCE_OF_TUNSTITE_VEIN.get(), 0.04, 8, 5, 3, 3, 3, 62, EndOreTunstite, Predicates.END));

    static {
        OreCopper.add(Pair.of(0.8d, RegisteredBlocks.ORECOPPER));
        OreCopper.add(Pair.of(0.15d, RegisteredBlocks.ORETIN));
        OreCopper.add(Pair.of(0.05d, Blocks.GOLD_ORE));
        OreTin.add(Pair.of(0.8d, RegisteredBlocks.ORETIN));
        OreTin.add(Pair.of(0.2d, RegisteredBlocks.ORECOPPER));
        OreIron.add(Pair.of(0.8d, Blocks.IRON_ORE));
        OreIron.add(Pair.of(0.09d, Blocks.GOLD_ORE));
        OreIron.add(Pair.of(0.11d, RegisteredBlocks.ORENICKEL));
        OreGold.add(Pair.of(0.7d, Blocks.GOLD_ORE));
        OreGold.add(Pair.of(0.15d, RegisteredBlocks.ORENOLANITE));
        OreGold.add(Pair.of(0.1d, RegisteredBlocks.ORESILVER));
        OreGold.add(Pair.of(0.05d, RegisteredBlocks.ORECOPPER));
        OreCoal.add(Pair.of(0.8d, Blocks.COAL_ORE));
        OreCoal.add(Pair.of(0.1d, Blocks.COAL_BLOCK));
        OreCoal.add(Pair.of(0.07d, RegisteredBlocks.OREGRAPHITE));
        OreCoal.add(Pair.of(0.03d, Blocks.DIAMOND_ORE));
        OreIlmenite.add(Pair.of(0.5d, Blocks.IRON_ORE));
        OreIlmenite.add(Pair.of(0.4d, RegisteredBlocks.OREILMENITE));
        OreIlmenite.add(Pair.of(0.1d, RegisteredBlocks.OREFERROMANGANESE));
        OreSilver.add(Pair.of(0.7d, RegisteredBlocks.ORESILVER));
        OreSilver.add(Pair.of(0.2d, Blocks.LAPIS_ORE));
        OreSilver.add(Pair.of(0.1d, RegisteredBlocks.ORECOPPER));
        OrePyrolusite.add(Pair.of(0.8d, RegisteredBlocks.OREPYROLUSITE));
        OrePyrolusite.add(Pair.of(0.2d, RegisteredBlocks.OREFERROMANGANESE));
        OreChromite.add(Pair.of(0.6d, Blocks.IRON_ORE));
        OreChromite.add(Pair.of(0.3d, RegisteredBlocks.ORECHROMITE));
        OreChromite.add(Pair.of(0.1d, RegisteredBlocks.ORENICKEL));
        OreBauxite.add(Pair.of(0.6d, RegisteredBlocks.OREBAUXITE));
        OreBauxite.add(Pair.of(0.3d, Blocks.CLAY));
        OreBauxite.add(Pair.of(0.06d, RegisteredBlocks.ORECOPPER));
        OreBauxite.add(Pair.of(0.04d, Blocks.IRON_ORE));
        OreNickel.add(Pair.of(0.6d, RegisteredBlocks.ORENICKEL));
        OreNickel.add(Pair.of(0.25d, Blocks.IRON_ORE));
        OreNickel.add(Pair.of(0.1d, RegisteredBlocks.ORECOPPER));
        OreNickel.add(Pair.of(0.05d, RegisteredBlocks.OREFERROMANGANESE));
        OreNolanite.add(Pair.of(0.7d, Blocks.IRON_ORE));
        OreNolanite.add(Pair.of(0.2d, RegisteredBlocks.ORENOLANITE));
        OreNolanite.add(Pair.of(0.1d, RegisteredBlocks.OREILMENITE));
        OreFerroManganese.add(Pair.of(0.5d, Blocks.IRON_ORE));
        OreFerroManganese.add(Pair.of(0.3d, RegisteredBlocks.OREFERROMANGANESE));
        OreFerroManganese.add(Pair.of(0.2d, RegisteredBlocks.OREPYROLUSITE));
        NetherOrePyrolusite.add(Pair.of(0.8d, RegisteredBlocks.OREPYROLUSITE_NETHER));
        NetherOrePyrolusite.add(Pair.of(0.2d, RegisteredBlocks.OREFERROMANGANESE_NETHER));
        NetherOreGraphite.add(Pair.of(0.8d, RegisteredBlocks.OREGRAPHITE_NETHER));
        NetherOreGraphite.add(Pair.of(0.2d, Blocks.COAL_BLOCK));
        NetherOreBauxite.add(Pair.of(0.7d, RegisteredBlocks.OREBAUXITE_NETHER));
        NetherOreBauxite.add(Pair.of(0.2d, Blocks.CLAY));
        NetherOreBauxite.add(Pair.of(0.1d, Blocks.GRAVEL));
        NetherOreFerroManganese.add(Pair.of(0.7d, RegisteredBlocks.OREFERROMANGANESE_NETHER));
        NetherOreFerroManganese.add(Pair.of(0.3d, RegisteredBlocks.OREPYROLUSITE_NETHER));
        NetherOreNolanite.add(Pair.of(0.6d, RegisteredBlocks.ORENOLANITE_NETHER));
        NetherOreNolanite.add(Pair.of(0.25d, RegisteredBlocks.OREBAUXITE_NETHER));
        NetherOreNolanite.add(Pair.of(0.15d, Blocks.NETHER_QUARTZ_ORE));
        EndOreTunstite.add(Pair.of(1.0d, RegisteredBlocks.ORETUNSTITE));
    }

    public static final void Init() {

    }
}
