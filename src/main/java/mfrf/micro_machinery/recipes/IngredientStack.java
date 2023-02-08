package mfrf.micro_machinery.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientStack {
    private final Ingredient ingredient;
    private final int count;

    public IngredientStack(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public static IngredientStack ReadFromBuffer(FriendlyByteBuf buffer) {
        return new IngredientStack(Ingredient.fromNetwork(buffer), buffer.readInt());
    }

    public static IngredientStack ReadFromJson(JsonObject object) {
        return new IngredientStack(Ingredient.fromJson(object.get("input")), object.get("count").getAsInt());
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }

    public boolean test(ItemStack stack) {
        return ingredient.test(stack) && count <= stack.getCount();
    }

    public void serializeToBuffer(FriendlyByteBuf buffer) {
        ingredient.toNetwork(buffer);
        buffer.writeInt(count);
    }
}
