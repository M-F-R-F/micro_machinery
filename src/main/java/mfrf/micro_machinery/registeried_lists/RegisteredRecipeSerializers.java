package mfrf.micro_machinery.registeried_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.micro_machinery.recipes.atomization.AtomizationRecipe;
import mfrf.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.micro_machinery.recipes.centrifuge.CentrifugeRecipe;
import mfrf.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import mfrf.micro_machinery.recipes.weld.WeldRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteredRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MicroMachinery.MODID);

    public static final RegistryObject<RecipeSerializer<KlinItemToFluidRecipe>> KLIN_ITEM_TO_FLUID = RECIPE_SERIALIZERS_REGISTER.register("klin_item_to_fluid", KlinItemToFluidRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<KlinFluidToItemRecipe>> KLIN_FLUID_TO_ITEM = RECIPE_SERIALIZERS_REGISTER.register("klin_fluid_to_item", KlinFluidToItemRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<AnvilRecipe>> FORGE_ANVIL_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("forge_anvil_recipe", AnvilRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<EtcherRecipe>> ETCHER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("etcher_recipe", EtcherRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<BlastFurnaceRecipe>> BLAST_FURNACE_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("blast_furnace_recipe", BlastFurnaceRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<ElectrolysisRecipe>> ELECTROLYSIS_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("electrolysis_recipe", ElectrolysisRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<CutterRecipe>> CUTTER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("cutter_recipe", CutterRecipe.Searlizer::new);
    public static final RegistryObject<RecipeSerializer<CentrifugeRecipe>> CENTRIFUGE_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("centrifuge_recipe", CentrifugeRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<FluidCrashRecipe>> FLUID_CRASH_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("fluid_crash_recipe", FluidCrashRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<AtomizationRecipe>> ATOMIZATION_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("atomization_recipe", AtomizationRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<WeldRecipe>> WELD_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("weld_recipe", WeldRecipe.Serializer::new);

    public static void init() {

    }

    public static class Type {
        public static final RecipeType<KlinItemToFluidRecipe> KLIN_ITEM_TO_FLUID_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "klin_item_to_fluid_recipe");
        public static final RecipeType<KlinFluidToItemRecipe> KLIN_FLUID_TP_ITEM_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "klin_fluid_to_item_recipe");
        public static final RecipeType<AnvilRecipe> FORGE_ANVIL_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "forge_anvil_recipe");
        public static final RecipeType<EtcherRecipe> ETCHER_RECIPE_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "etcher_recipe");
        public static final RecipeType<BlastFurnaceRecipe> BLAST_FURNACE_RECIPE_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "blast_furnace_recipe");
        public static final RecipeType<ElectrolysisRecipe> ELECTROLYSIS_RECIPE_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "electrolysis_recipe");
        public static final RecipeType<CutterRecipe> CUTTER_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "cutter_recipe");
        public static final RecipeType<CentrifugeRecipe> CENTRIFUGE_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "centrifuge_recipe");
        public static final RecipeType<FluidCrashRecipe> FLUID_CRASH_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "fluid_crash_recipe");
        public static final RecipeType<AtomizationRecipe> ATOMIZATION_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "atomization_recipe");
        public static final RecipeType<WeldRecipe> WELD_RECIPE_TYPE = RecipeType.register(MicroMachinery.MODID + "weld_recipe");
    }
}
