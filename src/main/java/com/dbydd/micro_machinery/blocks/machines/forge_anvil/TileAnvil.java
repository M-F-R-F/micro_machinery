package com.dbydd.micro_machinery.blocks.machines.forge_anvil;

import com.dbydd.micro_machinery.blocks.machines.MMTileBase;
import com.dbydd.micro_machinery.enums.EnumAnvilType;
import com.dbydd.micro_machinery.items.MMHammerBase;
import com.dbydd.micro_machinery.recipes.Anvil.AnvilRecipe;
import com.dbydd.micro_machinery.recipes.RecipeHelper;
import com.dbydd.micro_machinery.registery_lists.Registered_Tileentitie_Types;
import com.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class TileAnvil extends MMTileBase {
    private IntegerContainer forgeTime = new IntegerContainer(0, 3);
    private ItemStackHandler itemStackHandler = new ItemStackHandler(1);
    private EnumAnvilType rank = null;
    public TileAnvil() {
        super(Registered_Tileentitie_Types.TILE_ANVIL_TYPE.get());
    }

    public TileAnvil(EnumAnvilType rank) {
        super(Registered_Tileentitie_Types.TILE_ANVIL_TYPE.get());
        this.rank = rank;
    }

    public IntegerContainer getForgeTime() {
        return forgeTime;
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    @Override
    public void read(CompoundNBT compound) {
        forgeTime.deserializeNBT(compound.getCompound("forge_time"));
        rank = EnumAnvilType.valueOf(compound.getString("rank"));
        itemStackHandler.deserializeNBT(compound.getCompound("items"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("forge_time", forgeTime.serializeNBT());
        compound.put("items", itemStackHandler.serializeNBT());
        compound.putString("rank", rank.name());
        return super.write(compound);
    }

    public ActionResultType onActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        if (!worldIn.isRemote()) {
            if (handIn == Hand.MAIN_HAND) {
                ItemStack heldItem = player.getHeldItem(handIn);
                if (heldItem.isEmpty()) {
                    if (!itemStackHandler.getStackInSlot(0).isEmpty()) {
                        ItemStack stackInSlot = itemStackHandler.getStackInSlot(0);
                        itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
                        ItemHandlerHelper.giveItemToPlayer(player, stackInSlot);
                        forgeTime.resetValue();
                        markDirty2();
                    }
                } else {
                    Item item = heldItem.getItem();
                    if (item instanceof MMHammerBase) {
                        forgeTime.self_add();
                        heldItem.damageItem(1, player, playerEntity -> {});
                        worldIn.playSound(player, pos, rank.getSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                        if (forgeTime.atMaxValue()) {
                            ItemStack stackInSlot = itemStackHandler.getStackInSlot(0);
                            if (!stackInSlot.isEmpty()) {
                                AnvilRecipe recipe = RecipeHelper.getForgingAnvilRecipe(stackInSlot, worldIn.getRecipeManager());
                                if (recipe != null && recipe.getRankNeed() <= this.rank.getRank()) {
                                    itemStackHandler.setStackInSlot(0, recipe.getOutput());
                                    forgeTime.resetValue();
                                    markDirty2();
                                } else {
                                    forgeTime.resetValue();
                                    markDirty2();
                                }
                            }
                        }
                        markDirty2();
                    } else {
                        if (itemStackHandler.getStackInSlot(0).isEmpty()) {
                            itemStackHandler.setStackInSlot(0, new ItemStack(heldItem.getItem()));
                            forgeTime.resetValue();
                            heldItem.shrink(1);
                            player.setHeldItem(handIn, heldItem);
                        }else {
                            ItemStack stackInSlot = itemStackHandler.getStackInSlot(0);
                            ItemHandlerHelper.giveItemToPlayer(player, stackInSlot);
                            itemStackHandler.setStackInSlot(0,ItemStack.EMPTY);
                            forgeTime.resetValue();
                        }
                        markDirty2();
                    }
                }
            }
//        }
        return ActionResultType.SUCCESS;
    }
}
