package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.recipes.klin.KlinFluidToItemRecipe;
import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredRecipeSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Micro_Machinery.NAME);

    public static final RegistryObject<IRecipeSerializer<KlinItemToFluidRecipe>> KLIN_ITEM_TO_FLUID = RECIPE_SERIALIZERS_REGISTER.register("klin_item_to_fluid", KlinItemToFluidRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<KlinFluidToItemRecipe>> KLIN_FLUID_TO_ITEM = RECIPE_SERIALIZERS_REGISTER.register("klin_fluid_to_item", KlinFluidToItemRecipe.Serializer::new);


    public static void init() {

    }

    public static class Type {
        public static final IRecipeType<KlinItemToFluidRecipe> KLIN_ITEM_TO_FLUID_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + ":klin_item_to_fluid_recipe");
        public static final IRecipeType<KlinFluidToItemRecipe> KLIN_FLUID_TP_ITEM_RECIPE_TYPE = IRecipeType.register(Micro_Machinery.NAME + ":klin_fluid_to_item_recipe");
    }
}
