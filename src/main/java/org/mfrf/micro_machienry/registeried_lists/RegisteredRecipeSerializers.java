package org.mfrf.micro_machienry.registeried_lists;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.dbydd.micro_machinery.recipes.atomization.AtomizationRecipe;
import mfrf.dbydd.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.dbydd.micro_machinery.recipes.centrifuge.CentrifugeRecipe;
import mfrf.dbydd.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.dbydd.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.dbydd.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.dbydd.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.dbydd.micro_machinery.recipes.weld.WeldRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredRecipeSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MicroMachinery.NAME);

    public static final RegistryObject<IRecipeSerializer<KlinItemToFluidRecipe>> KLIN_ITEM_TO_FLUID = RECIPE_SERIALIZERS_REGISTER.register("klin_item_to_fluid", KlinItemToFluidRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<KlinFluidToItemRecipe>> KLIN_FLUID_TO_ITEM = RECIPE_SERIALIZERS_REGISTER.register("klin_fluid_to_item", KlinFluidToItemRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<AnvilRecipe>> FORGE_ANVIL_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("forge_anvil_recipe", AnvilRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<EtcherRecipe>> ETCHER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("etcher_recipe", EtcherRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<BlastFurnaceRecipe>> BLAST_FURNACE_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("blast_furnace_recipe", BlastFurnaceRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<ElectrolysisRecipe>> ELECTROLYSIS_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("electrolysis_recipe", ElectrolysisRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<CutterRecipe>> CUTTER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("cutter_recipe", CutterRecipe.Searlizer::new);
    public static final RegistryObject<IRecipeSerializer<CentrifugeRecipe>> CENTRIFUGE_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("centrifuge_recipe", CentrifugeRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<FluidCrashRecipe>> FLUID_CRASH_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("fluid_crash_recipe", FluidCrashRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<AtomizationRecipe>> ATOMIZATION_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("atomization_recipe", AtomizationRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<WeldRecipe>> WELD_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("weld_recipe", WeldRecipe.Serializer::new);

    public static void init() {

    }

    public static class Type {
        public static final IRecipeType<KlinItemToFluidRecipe> KLIN_ITEM_TO_FLUID_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "klin_item_to_fluid_recipe");
        public static final IRecipeType<KlinFluidToItemRecipe> KLIN_FLUID_TP_ITEM_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "klin_fluid_to_item_recipe");
        public static final IRecipeType<AnvilRecipe> FORGE_ANVIL_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "forge_anvil_recipe");
        public static final IRecipeType<EtcherRecipe> ETCHER_RECIPE_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "etcher_recipe");
        public static final IRecipeType<BlastFurnaceRecipe> BLAST_FURNACE_RECIPE_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "blast_furnace_recipe");
        public static final IRecipeType<ElectrolysisRecipe> ELECTROLYSIS_RECIPE_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "electrolysis_recipe");
        public static final IRecipeType<CutterRecipe> CUTTER_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "cutter_recipe");
        public static final IRecipeType<CentrifugeRecipe> CENTRIFUGE_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "centrifuge_recipe");
        public static final IRecipeType<FluidCrashRecipe> FLUID_CRASH_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "fluid_crash_recipe");
        public static final IRecipeType<AtomizationRecipe> ATOMIZATION_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "atomization_recipe");
        public static final IRecipeType<WeldRecipe> WELD_RECIPE_TYPE = IRecipeType.register(MicroMachinery.NAME + "weld_recipe");
    }
}
