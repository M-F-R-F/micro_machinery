package mfrf.micro_machinery.utils;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class RecipeFluidStack {
    private final ResourceLocation fluidName;
    private final int amount;

    public RecipeFluidStack(ResourceLocation fluidName, int amount) {
        this.fluidName = fluidName;
        this.amount = amount;
    }

    public static RecipeFluidStack read(FriendlyByteBuf buffer) {
        ResourceLocation fluid = ResourceLocation.tryParse(buffer.readUtf(32767));
        int amount = buffer.readInt();
        return new RecipeFluidStack(fluid, amount);
    }

    public ResourceLocation getFluidName() {
        return fluidName;
    }

    public int getAmount() {
        return amount;
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeUtf(fluidName.toString());
        buffer.writeInt(amount);
    }

    public boolean test(FluidStack stack) {
//        return stack.getFluid().getFluidType().equals(fluidName) && amount <= stack.getAmount(); //todo fixit
        return true;
    }
}
