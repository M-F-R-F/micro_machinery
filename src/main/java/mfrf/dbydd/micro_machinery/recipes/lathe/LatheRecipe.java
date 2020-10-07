package mfrf.dbydd.micro_machinery.recipes.lathe;

import com.google.common.collect.EvictingQueue;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import mfrf.dbydd.micro_machinery.utils.ActionContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Random;
import java.util.function.Function;

public class LatheRecipe implements INBTSerializable<CompoundNBT> {
    private final Function<ItemStack, ItemStack> recipe;
    private TileLathe.Action action2;
    private TileLathe.Action action1;
    private int wasteValueNeeded;

    public LatheRecipe(Function<ItemStack, ItemStack> recipe, Random random) {
        this.recipe = recipe;
        this.wasteValueNeeded = random.nextInt(30) + 50;
        this.action1 = TileLathe.Action.random(random);
        this.action2 = TileLathe.Action.random(random);
    }

    public boolean hasRecipe(ItemStack stack) {
        return !recipe.apply(stack).isEmpty();
    }

    public SubRecipe getSubRecipe(ItemStack stack) {
        if (stack.isEmpty()) {
            return new SubRecipe();
        }
        ItemStack resultStack = recipe.apply(stack);
        return new SubRecipe(resultStack, new ItemStack(stack.getItem()), action1, action2, wasteValueNeeded);
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("wasteValueNeeded", wasteValueNeeded);
        compoundNBT.putString("action1", action1.name());
        compoundNBT.putString("action2", action2.name());
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT compoundNBT) {
        this.wasteValueNeeded = compoundNBT.getInt("wasteValueNeeded");
        this.action1 = TileLathe.Action.valueOf(compoundNBT.getString("action1"));
        this.action2 = TileLathe.Action.valueOf(compoundNBT.getString("action2"));
    }

    public static class SubRecipe implements INBTSerializable<CompoundNBT> {
        public static final SubRecipe EMPTY = new SubRecipe();
        private TileLathe.Action action2;
        private TileLathe.Action action1;
        private int wasteValueNeed;
        private ItemStack result;
        private ItemStack input;

        public SubRecipe(ItemStack result, ItemStack input, TileLathe.Action action1, TileLathe.Action action2, int wasteValueNeed) {
            this.result = result;
            this.input = input;
            this.action1 = action1;
            this.action2 = action2;
            this.wasteValueNeed = wasteValueNeed;
        }

        public SubRecipe() {
            action2 = TileLathe.Action.EMPTY;
            action1 = TileLathe.Action.EMPTY;
            wasteValueNeed = 0;
            result = ItemStack.EMPTY;
            input = ItemStack.EMPTY;
        }

        public TileLathe.Action getAction2() {
            return action2;
        }

        public TileLathe.Action getAction1() {
            return action1;
        }

        public ItemStack getResult() {
            return result;
        }

        public ItemStack getInput() {
            return input;
        }

        public int getWasteValueNeed() {
            return wasteValueNeed;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.put("input", input.serializeNBT());
            compoundNBT.put("result", result.serializeNBT());
            compoundNBT.putString("action1", action1.name());
            compoundNBT.putString("action2", action2.name());
            compoundNBT.putInt("waste_value_need", wasteValueNeed);
            return compoundNBT;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            input = ItemStack.read(nbt.getCompound("input"));
            result = ItemStack.read(nbt.getCompound("result"));
            action1 = TileLathe.Action.valueOf(nbt.getString("action1"));
            action2 = TileLathe.Action.valueOf(nbt.getString("action2"));
            wasteValueNeed = nbt.getInt("waste_value_need");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubRecipe subRecipe = (SubRecipe) o;

            if (getWasteValueNeed() != subRecipe.getWasteValueNeed()) return false;
            if (getAction2() != subRecipe.getAction2()) return false;
            if (getAction1() != subRecipe.getAction1()) return false;
            if (!getResult().equals(subRecipe.getResult(), false)) return false;
            return getInput().equals(subRecipe.getInput(), false);
        }

        @Override
        public int hashCode() {
            int result1 = getAction2().hashCode();
            result1 = 31 * result1 + getAction1().hashCode();
            result1 = 31 * result1 + getWasteValueNeed();
            result1 = 31 * result1 + getResult().hashCode();
            result1 = 31 * result1 + getInput().hashCode();
            return result1;
        }
    }
}
