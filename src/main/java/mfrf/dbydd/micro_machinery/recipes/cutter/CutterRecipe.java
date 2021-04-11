package mfrf.dbydd.micro_machinery.recipes.cutter;

import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.IngredientStack;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

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
    public IRecipeSerializer<?> getSerializer() {
        return new Searlize();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.CUTTER_RECIPE_TYPE;
    }

    public static class Searlize extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CutterRecipe> {

        @Override
        public CutterRecipe read(ResourceLocation resourceLocation, JsonObject jsonObject) {
            IngredientStack input = IngredientStack.ReadFromJson(jsonObject.getAsJsonObject("input_stack"));
            ItemStack output = RecipeHelper.getItemStackFormJsonObject(jsonObject.getAsJsonObject("output"));
            int tick_use = jsonObject.get("tick_use").getAsInt();
            return new CutterRecipe(resourceLocation, input, output, tick_use);
        }

        @Nullable
        @Override
        public CutterRecipe read(ResourceLocation resourceLocation, PacketBuffer packetBuffer) {
            IngredientStack ingredientStack = IngredientStack.ReadFromBuffer(packetBuffer);
            ItemStack itemStack = packetBuffer.readItemStack();
            int i = packetBuffer.readInt();
            return new CutterRecipe(resourceLocation, ingredientStack, itemStack, i);
        }

        @Override
        public void write(PacketBuffer packetBuffer, CutterRecipe cutterRecipe) {
            cutterRecipe.input.serializeToBuffer(packetBuffer);
            packetBuffer.writeItemStack(cutterRecipe.output);
            packetBuffer.writeInt(cutterRecipe.tickUse);
        }
    }
}
