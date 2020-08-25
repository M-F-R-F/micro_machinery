package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;
import com.dbydd.micro_machinery.recipes.klin.KlinItemToFluidRecipe;
import com.dbydd.micro_machinery.registery_lists.RegisteredRecipeSerializers;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Optional;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
            BlockPos pos = context.getPos();
            if (world.chunkExists(pos.getX() >> 4, pos.getZ() >> 4)) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity != null) {
                    if (tileEntity instanceof TileKlin) {
                        RecipeManager recipeManager = world.getRecipeManager();
                        Optional<KlinItemToFluidRecipe> recipe = recipeManager.getRecipe(RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE, new RecipeWrapper(new ItemStackHandler(2)), world);
                        recipe.ifPresent(klinItemToFluidRecipe -> context.getPlayer().sendMessage(new StringTextComponent(klinItemToFluidRecipe.toString())));
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
