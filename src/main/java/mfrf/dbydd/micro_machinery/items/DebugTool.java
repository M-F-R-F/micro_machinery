package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.blocks.machines.energy_cable.TileEnergyCable;
import mfrf.dbydd.micro_machinery.world_saved_data.EnergyCableSavedData;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

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
            if (context.getPlayer().isSneaking()) {
                context.getPlayer().sendMessage(new StringTextComponent(EnergyCableSavedData.get(world).write(new CompoundNBT()).toString()));
            } else {
                BlockPos pos = context.getPos();
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof TileEnergyCable) {
                    context.getPlayer().sendMessage(new StringTextComponent(((TileEnergyCable) tileEntity).getSign().toString()));
                }

            }
        }
        return ActionResultType.SUCCESS;
    }
}
