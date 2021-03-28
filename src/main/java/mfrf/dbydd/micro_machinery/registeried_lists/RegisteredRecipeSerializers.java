package mfrf.dbydd.micro_machinery.registeried_lists;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.dbydd.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.dbydd.micro_machinery.recipes.cutter.CutterRecipe;
import mfrf.dbydd.micro_machinery.recipes.electrolysis.ElectrolysisRecipe;
import mfrf.dbydd.micro_machinery.recipes.etcher.EtcherRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import mfrf.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredRecipeSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Micro_Machinery.NAME);

    public static final RegistryObject<IRecipeSerializer<KlinItemToFluidRecipe>> KLIN_ITEM_TO_FLUID = RECIPE_SERIALIZERS_REGISTER.register("klin_item_to_fluid", KlinItemToFluidRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<KlinFluidToItemRecipe>> KLIN_FLUID_TO_ITEM = RECIPE_SERIALIZERS_REGISTER.register("klin_fluid_to_item", KlinFluidToItemRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<AnvilRecipe>> FORGE_ANVIL_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("forge_anvil_recipe", AnvilRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<EtcherRecipe>> ETCHER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("etcher_recipe", EtcherRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<BlastFurnaceRecipe>> BLAST_FURNACE_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("blast_furnace_recipe", BlastFurnaceRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<ElectrolysisRecipe>> ELECTROLYSIS_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("electrolysis_recipe", ElectrolysisRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<CutterRecipe>> CUTTER_RECIPE = RECIPE_SERIALIZERS_REGISTER.register("cutter_recipe",CutterRecipe.Searlize::new);

    public static void init() {

    }

    public static class Type {
        public static final IRecipeType<KlinItemToFluidRecipe> KLIN_ITEM_TO_FLUID_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "klin_item_to_fluid_recipe");
        public static final IRecipeType<KlinFluidToItemRecipe> KLIN_FLUID_TP_ITEM_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "klin_fluid_to_item_recipe");
        public static final IRecipeType<AnvilRecipe> FORGE_ANVIL_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "forge_anvil_recipe");
        public static final IRecipeType<EtcherRecipe> ETCHER_RECIPE_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "etcher_recipe");
        public static final IRecipeType<BlastFurnaceRecipe> BLAST_FURNACE_RECIPE_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "blast_furnace_recipe");
        public static final IRecipeType<ElectrolysisRecipe> ELECTROLYSIS_RECIPE_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + "electrolysis_recipe");
        public static final IRecipeType<CutterRecipe> CUTTER_RECIPE = IRecipeType.register(Micro_Machinery.NAME + "cutter_recipe");
    }
}
