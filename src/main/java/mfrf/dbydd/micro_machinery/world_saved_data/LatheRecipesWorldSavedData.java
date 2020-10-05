package mfrf.dbydd.micro_machinery.world_saved_data;

import mfrf.dbydd.micro_machinery.recipes.lathe.LatheRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.Collection;
import java.util.Random;

public class LatheRecipesWorldSavedData extends WorldSavedData {
    public static final String NAME = "LatheRecipesWorldSavedData";
    private final LatheRecipe GEAR_Recipe;
    private final LatheRecipe STICK_Recipe;
    private final LatheRecipe ROLL_Recipe;
    private final LatheRecipe SCREW_Recipe;

    private LatheRecipesWorldSavedData(Random random) {
        super(NAME);
        GEAR_Recipe = new LatheRecipe(itemStack -> {
            for (ResourceLocation tag : itemStack.getItem().getTags()) {
                if (tag.getNamespace().equals("forge")) {
                    String path = tag.getPath();
                    if (path.matches("^gearblanks/\\w+")) {
                        String[] split = path.split("/");
                        Collection<Item> elements = new ItemTags.Wrapper(new ResourceLocation("forge", path.replace("gearblanks/", "gears/"))).getAllElements();
                        return elements.size() == 0 ? ItemStack.EMPTY : new ItemStack((Item) elements.toArray()[0]);
                    }
                }
            }
            return ItemStack.EMPTY;
        }, random);

        STICK_Recipe = new LatheRecipe(itemStack -> {
            for (ResourceLocation tag : itemStack.getItem().getTags()) {
                if (tag.getNamespace().equals("forge")) {
                    String path = tag.getPath();
                    if (path.matches("^ingots/\\w+")) {
                        String[] split = path.split("/");
                        Collection<Item> elements = new ItemTags.Wrapper(new ResourceLocation("forge", path.replace("ingots/", "sticks/"))).getAllElements();
                        return elements.size() == 0 ? ItemStack.EMPTY : new ItemStack((Item) elements.toArray()[0]);
                    }
                }
            }
            return ItemStack.EMPTY;
        }, random);

        SCREW_Recipe = new LatheRecipe(itemStack -> {
            for (ResourceLocation tag : itemStack.getItem().getTags()) {
                if (tag.getNamespace().equals("forge")) {
                    String path = tag.getPath();
                    if (path.matches("^sticks/\\w+")) {
                        String[] split = path.split("/");
                        Collection<Item> elements = new ItemTags.Wrapper(new ResourceLocation("forge", path.replace("sticks/", "screws/"))).getAllElements();
                        return elements.size() == 0 ? ItemStack.EMPTY : new ItemStack((Item) elements.toArray()[0]);
                    }
                }
            }
            return ItemStack.EMPTY;
        }, random);

        ROLL_Recipe = new LatheRecipe(itemStack -> {
            for (ResourceLocation tag : itemStack.getItem().getTags()) {
                if (tag.getNamespace().equals("forge")) {
                    String path = tag.getPath();
                    if (path.matches("^axles/\\w+")) {
                        String[] split = path.split("/");
                        Collection<Item> elements = new ItemTags.Wrapper(new ResourceLocation("forge", path.replace("axles/", "rolls/"))).getAllElements();
                        return elements.size() == 0 ? ItemStack.EMPTY : new ItemStack((Item) elements.toArray()[0]);
                    }
                }
            }
            return ItemStack.EMPTY;
        }, random);
    }

    public static LatheRecipe.SubRecipe getResult(World worldIn, ItemStack stack) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);

        DimensionSavedDataManager storage = world.getSavedData();
        LatheRecipesWorldSavedData worldSavedData = storage.getOrCreate(() -> new LatheRecipesWorldSavedData(world.rand), NAME);
        if (worldSavedData.GEAR_Recipe.hasRecipe(stack)) {
            return worldSavedData.GEAR_Recipe.getResult(stack);
        }
        if (worldSavedData.STICK_Recipe.hasRecipe(stack)) {
            return worldSavedData.STICK_Recipe.getResult(stack);
        }
        if (worldSavedData.SCREW_Recipe.hasRecipe(stack)) {
            return worldSavedData.SCREW_Recipe.getResult(stack);
        }
        if (worldSavedData.ROLL_Recipe.hasRecipe(stack)) {
            return worldSavedData.ROLL_Recipe.getResult(stack);
        }
        return null;
    }

    public static LatheRecipe getRecipeType(World worldIn, ItemStack stack) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);

        DimensionSavedDataManager storage = world.getSavedData();
        LatheRecipesWorldSavedData worldSavedData = storage.getOrCreate(() -> new LatheRecipesWorldSavedData(world.rand), NAME);
        if (worldSavedData.GEAR_Recipe.hasRecipe(stack)) {
            return worldSavedData.GEAR_Recipe;
        }
        if (worldSavedData.STICK_Recipe.hasRecipe(stack)) {
            return worldSavedData.STICK_Recipe;
        }
        if (worldSavedData.SCREW_Recipe.hasRecipe(stack)) {
            return worldSavedData.SCREW_Recipe;
        }
        if (worldSavedData.ROLL_Recipe.hasRecipe(stack)) {
            return worldSavedData.ROLL_Recipe;
        }
        return null;
    }

    @Override
    public void read(CompoundNBT nbt) {
        GEAR_Recipe.deserializeNBT(nbt.getCompound("gear_recipe"));
        STICK_Recipe.deserializeNBT(nbt.getCompound("stick_recipe"));
        SCREW_Recipe.deserializeNBT(nbt.getCompound("screw_recipe"));
        ROLL_Recipe.deserializeNBT(nbt.getCompound("roll_recipe"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("gear_recipe", GEAR_Recipe.serializeNBT());
        compound.put("stick_recipe", STICK_Recipe.serializeNBT());
        compound.put("screw_recipe", SCREW_Recipe.serializeNBT());
        compound.put("roll_recipe", ROLL_Recipe.serializeNBT());
        return compound;
    }

}
