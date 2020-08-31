package mfrf.dbydd.micro_machinery.items;

import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
//            BlockPos pos = context.getPos();
//            if (world.chunkExists(pos.getX() >> 4, pos.getZ() >> 4)) {
//                TileEntity tileEntity = world.getTileEntity(pos);
//                if (tileEntity != null) {
//                    if (tileEntity instanceof TileKlin) {
//                        RecipeManager recipeManager = world.getRecipeManager();
//                        Optional<KlinItemToFluidRecipe> recipe = recipeManager.getRecipe(RegisteredRecipeSerializers.Type.KLIN_ITEM_TO_FLUID_RECIPE_TYPE, new RecipeWrapper(new ItemStackHandler(2)), world);
//                        recipe.ifPresent(klinItemToFluidRecipe -> context.getPlayer().sendMessage(new StringTextComponent(klinItemToFluidRecipe.toString())));
//                    }
//                }
//            }
        }
        return ActionResultType.SUCCESS;
    }
}
