package mfrf.micro_machinery.recipes;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;

public class IngredientStack {
    private final Ingredient ingredient;
    private final int count;

    public IngredientStack(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public static IngredientStack ReadFromBuffer(PacketBuffer buffer) {
        return new IngredientStack(Ingredient.read(buffer), buffer.readInt());
    }

    public static IngredientStack ReadFromJson(JsonObject object){
         return new IngredientStack(Ingredient.deserialize(object.get("input")),object.get("count").getAsInt());
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }

    public boolean test(ItemStack stack){
        return ingredient.test(stack) && count <= stack.getCount();
    }

    public void serializeToBuffer(PacketBuffer buffer) {
        ingredient.write(buffer);
        buffer.writeInt(count);
    }
}
