package org.mfrf.micro_machienry.recipes.centrifuge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mfrf.dbydd.micro_machinery.recipes.IngredientStack;
import mfrf.dbydd.micro_machinery.recipes.RecipeBase;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredRecipeSerializers;
import mfrf.dbydd.micro_machinery.utils.RandomUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CentrifugeRecipe extends RecipeBase {
    private final IngredientStack input;
    private final ArrayList<RandomUtils.RollListI<ItemStack>> rollList;
    private final int time;

    public CentrifugeRecipe(ResourceLocation id, IngredientStack input, ArrayList<RandomUtils.RollListI<ItemStack>> rollList, int time) {
        super(id);
        this.input = input;
        this.rollList = rollList;
        this.time = time;
    }

    public Map<Item, Integer> getOutputs(Random random) {
//        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        HashMap<Item, Integer> ret = new HashMap<>();
        for (RandomUtils.RollListI<ItemStack> itemStackRollListI : rollList) {
            ItemStack roll = itemStackRollListI.roll(random);
            Item item = roll.getItem();
            if (ret.containsKey(item)) {
                ret.put(item, roll.getCount() + ret.get(item));
            } else {
                ret.put(item, roll.getCount());
            }
        }
        return ret;
    }

    public IngredientStack getInput() {
        return input;
    }

    public int getTime() {
        return time;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegisteredRecipeSerializers.CENTRIFUGE_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RegisteredRecipeSerializers.Type.CENTRIFUGE_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CentrifugeRecipe> {

        @Override
        public CentrifugeRecipe read(ResourceLocation recipeId, JsonObject json) {
            int time = json.get("time").getAsInt();
            IngredientStack input = IngredientStack.ReadFromJson(json.get("input_stack").getAsJsonObject());
            JsonArray output = json.get("output").getAsJsonArray();

            ArrayList<RandomUtils.RollListI<ItemStack>> outputs = new ArrayList<>();

            for (JsonElement jsonElement : output) {
                JsonArray slot = jsonElement.getAsJsonArray();

                HashMap<ItemStack, Integer> roll_list = new HashMap<>();
                for (JsonElement element : slot) {
                    JsonObject jsonObject = (JsonObject) element;
                    int probability = 100;
                    if (jsonObject.has("probability")) {
                        probability = jsonObject.get("probability").getAsInt();
                    }
                    ItemStack stack = RecipeHelper.getItemStackFormJsonObject(jsonObject);
                    roll_list.put(stack, probability);
                }

                outputs.add(new RandomUtils.RollListI<>(roll_list));
            }

            return new CentrifugeRecipe(recipeId, input, outputs, time);
        }

        @Nullable
        @Override
        public CentrifugeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int time = buffer.readInt();
            IngredientStack input = IngredientStack.ReadFromBuffer(buffer);
            ArrayList<RandomUtils.RollListI<ItemStack>> rollSlots = new ArrayList<>();

            CompoundNBT container = buffer.readCompoundTag();
            ListNBT slots = container.getList("s", Constants.NBT.TAG_LIST);

            for (INBT inbt : slots) {
                CompoundNBT slot = (CompoundNBT) inbt;
                int bounds = slot.getInt("b");
                HashMap<RandomUtils.RangeI, ItemStack> rangeIItemStackHashMap = new HashMap<>();

                ListNBT rollList = slot.getList("l", Constants.NBT.TAG_LIST);
                for (INBT inbt1 : rollList) {
                    CompoundNBT compoundNBT = (CompoundNBT) inbt1;

                    RandomUtils.RangeI range = new RandomUtils.RangeI(compoundNBT.getCompound("r"));
                    ItemStack itemStack = ItemStack.read(compoundNBT.getCompound("i"));
                    rangeIItemStackHashMap.put(range, itemStack);
                }

                rollSlots.add(new RandomUtils.RollListI<ItemStack>(rangeIItemStackHashMap, bounds));
            }

            return new CentrifugeRecipe(recipeId, input, rollSlots, time);
        }

        @Override
        public void write(PacketBuffer buffer, CentrifugeRecipe recipe) {
            buffer.writeInt(recipe.time);
            recipe.input.serializeToBuffer(buffer);

            ListNBT slots = new ListNBT();
            for (RandomUtils.RollListI<ItemStack> itemStackRollListI : recipe.rollList) {
                CompoundNBT compoundNBT = new CompoundNBT();
                HashMap<RandomUtils.RangeI, ItemStack> list = itemStackRollListI.list;
                int bound = itemStackRollListI.bound;

                ListNBT rollList = new ListNBT();
                for (Map.Entry<RandomUtils.RangeI, ItemStack> rangeIItemStackEntry : list.entrySet()) {
                    CompoundNBT nbt = new CompoundNBT();
                    nbt.put("r", rangeIItemStackEntry.getKey().toNbt());
                    nbt.put("i", rangeIItemStackEntry.getValue().serializeNBT());
                    rollList.add(nbt);
                }

                compoundNBT.putInt("b", bound);
                compoundNBT.put("l", rollList);
                slots.add(compoundNBT);
            }

            CompoundNBT container = new CompoundNBT();
            container.put("s", slots);

            buffer.writeCompoundTag(container);
        }
    }
}
