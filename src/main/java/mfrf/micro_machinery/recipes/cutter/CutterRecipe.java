package mfrf.micro_machinery.recipes.cutter;

import com.google.gson.JsonObject;
import mfrf.micro_machinery.recipes.IngredientStack;
import mfrf.micro_machinery.recipes.RecipeBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.registeried_lists.MMRecipeSerializers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CutterRecipe extends RecipeBase {
    private final IngredientStack input;
    private final ItemStack output;
    private final int tickUse;

    public CutterRecipe(ResourceLocation id, IngredientStack input, ItemStack output, int tickUse) {
        super(id);
        this.input = input;
        this.output = output;
        this.tickUse = tickUse;
    }

    public IngredientStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTickUse() {
        return tickUse;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.CUTTER_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MMRecipeSerializers.Type.CUTTER_RECIPE_TYPE;
    }

    public static class Searlizer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CutterRecipe> {

        @Override
        public CutterRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
            IngredientStack input = IngredientStack.ReadFromJson(jsonObject.getAsJsonObject("input_stack"));
            ItemStack output = RecipeHelper.getItemStackFormJsonObject(jsonObject.getAsJsonObject("output"));
            int tick_use = jsonObject.get("tick_use").getAsInt();
            return new CutterRecipe(pRecipeId, input, output, tick_use);
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public CutterRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf packetBuffer) {
            IngredientStack ingredientStack = IngredientStack.ReadFromBuffer(packetBuffer);
            ItemStack itemStack = packetBuffer.readItem();
            int i = packetBuffer.readInt();
            return new CutterRecipe(pRecipeId, ingredientStack, itemStack, i);
        }

        @Override
        public void toNetwork(FriendlyByteBuf packetBuffer, CutterRecipe cutterRecipe) {
            cutterRecipe.input.serializeToBuffer(packetBuffer);
            packetBuffer.writeItemStack(cutterRecipe.output, false);
            packetBuffer.writeInt(cutterRecipe.tickUse);
        }
    }
}
