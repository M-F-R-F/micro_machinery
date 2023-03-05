package mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.centrifuge.CentrifugeContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.centrifuge.CentrifugeRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.item.crafting.Recipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class TileCentrifuge extends MMTileBase implements MenuProvider {
    private FEContainer feContainer = new FEContainer(0, 40000) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            markDirty2();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            markDirty2();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public boolean canReceive() {
            return true;
        }

        @Override
        public int selfSubtract() {
            int current = getCurrent();
            if (current >= 128) {
                minus(128, false);
                return current;
            } else {
                return -1;
            }
        }
    };
    private ItemStackHandler input = new ItemStackHandler(1);
    private ItemStackHandler output = new ItemStackHandler(5);
    private IntegerContainer progress = new IntegerContainer();
    private boolean isWorking = false;
    private ResourceLocation recipe = null;

    public TileCentrifuge(BlockPos pos, BlockState state) {
        super(RegisteredBlockEntityTypes.TILE_CENTRIFUGE.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof TileCentrifuge centrifuge) {

            if (centrifuge.isWorking) {

                if (centrifuge.feContainer.selfSubtract() != -1) {
                    centrifuge.progress.selfAdd();
                    centrifuge.setChanged();
                }

                if (centrifuge.progress.atMaxValue()) {
                    Optional<? extends Recipe<?>> recipe = world.getRecipeManager().byKey(centrifuge.recipe);
                    recipe.ifPresent(iRecipe -> {
                        CentrifugeRecipe centrifugeRecipe = (CentrifugeRecipe) iRecipe;
                        Map<Item, Integer> outputs = centrifugeRecipe.getOutputs(world.random);
                        int randTime = 0;
                        while (centrifuge.testItemInsertable(outputs) && randTime++ < 5) {
                            outputs = centrifugeRecipe.getOutputs(world.random);
                        }

                        centrifuge.insertResultNoResponse(outputs);

                        centrifuge.isWorking = false;
                        centrifuge.progress.resetValue();
                        centrifuge.recipe = null;
                        centrifuge.setChanged();

                    });
                }
            } else {

                if (!centrifuge.input.getStackInSlot(0).isEmpty()) {
                    CentrifugeRecipe centrifugeRecipe = RecipeHelper.getCentrifugeRecipe(centrifuge.input.getStackInSlot(0), world.getRecipeManager());

                    if (centrifugeRecipe != null) {
                        centrifuge.input.getStackInSlot(0).shrink(centrifugeRecipe.getInput().getCount());
                        centrifuge.progress.setMax(centrifugeRecipe.getTime());
                        centrifuge.recipe = centrifugeRecipe.getId();
                        centrifuge.isWorking = true;
                        centrifuge.setChanged();
                    }
                }
            }
        }
    }

    private boolean testItemInsertable(Map<Item, Integer> map) {
        boolean ret = true;
        for (Map.Entry<Item, Integer> itemIntegerEntry : map.entrySet()) {
            ret = ret && ItemHandlerHelper.insertItemStacked(output, new ItemStack(itemIntegerEntry.getKey(), itemIntegerEntry.getValue()), true).isEmpty();
        }
        return ret;
    }

    private void insertResultNoResponse(Map<Item, Integer> map) {
        for (Map.Entry<Item, Integer> itemIntegerEntry : map.entrySet()) {
            if (!ItemHandlerHelper.insertItemStacked(output, new ItemStack(itemIntegerEntry.getKey(), itemIntegerEntry.getValue()), false).isEmpty()) {
                break;
            }
        }
        markDirty2();

    }

    public FEContainer getFeContainer() {
        return feContainer;
    }

    public IntegerContainer getProgress() {
        return progress;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    public ItemStackHandler getInput() {
        return input;
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        CompoundTag compoundNBT = super.write(compound);
        compoundNBT.put("fe_container", feContainer.serializeNBT());
        compoundNBT.put("input", input.serializeNBT());
        compoundNBT.put("output", output.serializeNBT());
        compoundNBT.putBoolean("is_working", isWorking);
        compoundNBT.put("progress", progress.serializeNBT());
        if (recipe != null) {
            compoundNBT.putString("recipe", recipe.toString());
        }
        return compoundNBT;
    }

    @Override
    public void read(CompoundTag nbt) {
        super.read(nbt);
        feContainer.deserializeNBT(nbt.getCompound("fe_container"));
        input.deserializeNBT(nbt.getCompound("input"));
        output.deserializeNBT(nbt.getCompound("output"));
        isWorking = nbt.getBoolean("is_working");
        progress.deserializeNBT(nbt.getCompound("progress"));
        if (nbt.contains("recipe")) {
            recipe = ResourceLocation.tryParse(nbt.getString("recipe"));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && isBackDirection(side)) {
            return LazyOptional.of(() -> feContainer).cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.DOWN) {
                return LazyOptional.of(() -> output).cast();
            } else {
                return LazyOptional.of(() -> input).cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("centrifuge");
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CentrifugeContainer(pContainerId, pPlayerInventory, getBlockPos(), level);
    }
}
