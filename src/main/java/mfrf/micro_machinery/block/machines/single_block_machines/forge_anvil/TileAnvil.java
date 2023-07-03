package mfrf.micro_machinery.block.machines.single_block_machines.forge_anvil;

import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.enums.EnumAnvilType;
import mfrf.micro_machinery.item.MMHammerBase;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.anvil.AnvilRecipe;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class TileAnvil extends MMTileBase {
    private final IntegerContainer forgeTime = new IntegerContainer(0, 3);
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1);
    private EnumAnvilType rank = null;

    public TileAnvil(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_ANVIL_TYPE.get(), pos, state);
    }

    public TileAnvil(EnumAnvilType rank, BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_ANVIL_TYPE.get(), pos, state);
        this.rank = rank;
    }

    public IntegerContainer getForgeTime() {
        return forgeTime;
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    @Override
    public void load(CompoundTag compound) {
        forgeTime.deserializeNBT(compound.getCompound("forge_time"));
        rank = EnumAnvilType.valueOf(compound.getString("rank"));
        itemStackHandler.deserializeNBT(compound.getCompound("items"));
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("forge_time", forgeTime.serializeNBT());
        pTag.put("items", itemStackHandler.serializeNBT());
        pTag.putString("rank", rank.name());
    }

    public InteractionResult onActivated(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
//        if (!worldIn.isClientSide()) {
        if (handIn == InteractionHand.MAIN_HAND) {
            ItemStack heldItem = player.getItemInHand(handIn);
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
                    forgeTime.selfAdd();
                    heldItem.hurtAndBreak(1, player, playerEntity -> {
                    });
                    worldIn.playSound(player, pos, rank.getSound(), SoundSource.PLAYERS, 1.0F, 1.0F);
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
                        player.setItemInHand(handIn, heldItem);
                    } else {
                        ItemStack stackInSlot = itemStackHandler.getStackInSlot(0);
                        ItemHandlerHelper.giveItemToPlayer(player, stackInSlot);
                        itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
                        forgeTime.resetValue();
                    }
                    markDirty2();
                }
            }
        }
//        }
        return InteractionResult.SUCCESS;
    }
}
