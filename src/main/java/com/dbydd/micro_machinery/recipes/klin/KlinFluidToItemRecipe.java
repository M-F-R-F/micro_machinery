package com.dbydd.micro_machinery.recipes.klin;

import com.dbydd.micro_machinery.enums.EnumCastType;
import com.dbydd.micro_machinery.items.MMCastBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class KlinFluidToItemRecipe {

    public static List<KlinFluidToItemRecipe> RECIPES = new ArrayList<>();

    private ItemStack output;
    private EnumCastType cast;
    private FluidStack inputfluid;
    private int cooldown;


    public KlinFluidToItemRecipe(ItemStack output, FluidStack inputfluid, EnumCastType cast, int cooldown) {
        this.output = output;
        this.inputfluid = inputfluid;
        this.cast = cast;
        this.cooldown = cooldown;

        RECIPES.add(this);
    }

    public ItemStack getOutput() {
        return output;
    }

    public EnumCastType getCast() {
        return cast;
    }

    public FluidStack getInputfluid() {
        return inputfluid;
    }

    public int getCooldown() {
        return cooldown;
    }
}