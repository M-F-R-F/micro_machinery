package mfrf.dbydd.micro_machinery.utils;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class RecipeFluidStack {
    private final ResourceLocation fluidName;
    private final int amount;

    public RecipeFluidStack(ResourceLocation fluidName, int amount) {
        this.fluidName = fluidName;
        this.amount = amount;
    }

    public static RecipeFluidStack read(PacketBuffer buffer) {
        ResourceLocation fluid = ResourceLocation.tryCreate(buffer.readString(32767));
        int amount = buffer.readInt();
        return new RecipeFluidStack(fluid, amount);
    }

    public ResourceLocation getFluidName() {
        return fluidName;
    }

    public int getAmount() {
        return amount;
    }

    public void write(PacketBuffer buffer) {
        buffer.writeString(fluidName.toString());
        buffer.writeInt(amount);
    }

    public boolean test(FluidStack stack) {
        return stack.getFluid().getRegistryName().equals(fluidName) && amount <= stack.getAmount();
    }
}
