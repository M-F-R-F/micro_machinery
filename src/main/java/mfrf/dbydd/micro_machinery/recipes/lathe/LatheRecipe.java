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

    public boolean isActionEqual(ActionContainer container) {
        EvictingQueue<TileLathe.Action> actionQueue = container.getActionQueue();
        return false;
    }

    public SubRecipe getResult(ItemStack stack) {
        ItemStack resultStack = recipe.apply(stack);
        return new SubRecipe(resultStack, new ItemStack(stack.getItem()));
    }

    public int getWasteValueNeeded() {
        return wasteValueNeeded;
    }

    public TileLathe.Action getAction1() {
        return action1;
    }

    public TileLathe.Action getAction2() {
        return action2;
    }

    @Override
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

    public class SubRecipe implements INBTSerializable<CompoundNBT> {
        private TileLathe.Action action2 = LatheRecipe.this.action1;
        private TileLathe.Action action1 = LatheRecipe.this.action2;
        private ItemStack result;
        private ItemStack input;

        public SubRecipe(ItemStack result, ItemStack input) {
            this.result = result;
            this.input = input;
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

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.put("input", input.serializeNBT());
            compoundNBT.put("result", result.serializeNBT());
            compoundNBT.putString("action1", action1.name());
            compoundNBT.putString("action2", action2.name());
            return compoundNBT;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            input = ItemStack.read(nbt.getCompound("input"));
            result = ItemStack.read(nbt.getCompound("result"));
            action1 = TileLathe.Action.valueOf(nbt.getString("action1"));
            action2 = TileLathe.Action.valueOf(nbt.getString("action2"));
        }
    }
}
