package mfrf.micro_machinery.blocks.machines.single_block_machines.centrifuge;

import mfrf.micro_machinery.blocks.machines.MMTileBase;
import mfrf.micro_machinery.gui.centrifuge.CentrifugeContainer;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.centrifuge.CentrifugeRecipe;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.MenuProvider;
import net.minecraft.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslatableComponent;
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

public class TileCentrifuge extends MMTileBase implements  MenuProvider {
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

    public TileCentrifuge() {
        super(RegisteredBlockEntityTypes.TILE_CENTRIFUGE.get());
    }

    @Override
    public void tick() {
        if (!world.isClientSide()) {

            if (isWorking) {

                if (feContainer.selfSubtract() != -1) {
                    progress.selfAdd();
                    setChanged();
                }

                if (progress.atMaxValue()) {
                    Optional<? extends IRecipe<?>> recipe = world.getRecipeManager().getRecipe(this.recipe);
                    recipe.ifPresent(iRecipe -> {
                        CentrifugeRecipe centrifugeRecipe = (CentrifugeRecipe) iRecipe;
                        Map<Item, Integer> outputs = centrifugeRecipe.getOutputs(world.rand);
                        int randTime = 0;
                        while (testItemInsertable(outputs) && randTime++ < 5) {
                            outputs = centrifugeRecipe.getOutputs(world.rand);
                        }

                        insertResultNoResponse(outputs);

                        isWorking = false;
                        progress.resetValue();
                        this.recipe = null;
                        setChanged();

                    });
                }
            } else {

                if (!input.getStackInSlot(0).isEmpty()) {
                    CentrifugeRecipe centrifugeRecipe = RecipeHelper.getCentrifugeRecipe(input.getStackInSlot(0), world.getRecipeManager());

                    if (centrifugeRecipe != null) {
                        this.input.getStackInSlot(0).shrink(centrifugeRecipe.getInput().getCount());
                        this.progress.setMax(centrifugeRecipe.getTime());
                        this.recipe = centrifugeRecipe.getId();
                        this.isWorking = true;
                        setChanged();
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
            recipe = ResourceLocation.tryCreate(nbt.getString("recipe"));
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
    public ITextComponent getDisplayName() {
        return new TranslatableComponent("centrifuge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new CentrifugeContainer(p_createMenu_1_, p_createMenu_2_, pos, world);
    }
}
