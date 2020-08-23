//package com.dbydd.micro_machinery.event;
//
//import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
//import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.item.crafting.IRecipeType;
//import net.minecraft.item.crafting.RecipeManager;
//import net.minecraft.resources.IResourceManager;
//import net.minecraft.resources.IResourceManagerReloadListener;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.event.RecipesUpdatedEvent;
//import net.minecraftforge.eventbus.api.EventPriority;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
//public class RecipeLoadHandler implements IResourceManagerReloadListener {
//
//    @Override
//    public void onResourceManagerReload(IResourceManager resourceManager) {
//
//    }
//
//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public void onRecipesUpdated(RecipesUpdatedEvent event)
//    {
//        buildRecipeLists(event.getRecipeManager());
//    }
//
//    private void buildRecipeLists(RecipeManager recipeManager) {
//        Collection<IRecipe<?>> recipes = recipeManager.getRecipes();
//
//        KlinItemToFluidRecipe.RECIPES = filterRecipes(recipes, KlinItemToFluidRecipe.class, KlinItemToFluidRecipe.TYPE);
//
//    }
//
//    static <R extends IRecipe<?>> Map<ResourceLocation, R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType)
//    {
//        return recipes.stream()
//                .filter(iRecipe -> iRecipe.getType()==recipeType)
//                .map(recipeClass::cast)
//                .collect(Collectors.toMap(recipe -> recipe.getId(), recipe -> recipe));
//    }
//
//}
