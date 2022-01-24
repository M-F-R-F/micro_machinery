package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.dbydd.micro_machinery.gui.blast_furnace.BlastFurnaceContainer;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.blast_furnace.BlastFurnaceRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.dbydd.micro_machinery.utils.MultiBlockStructureMaps;
import mfrf.dbydd.micro_machinery.utils.MultiBlockStructureMaps.MultiBlockPosBox;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class TileBlastFurnace extends MMMultiBlockTileMainPartBase implements INamedContainerProvider, ITickableTileEntity {

    private ItemStackHandler itemHandler = new ItemStackHandler(3);
    private IntegerContainer progressContainer = new IntegerContainer();
    private IntegerContainer heatHandler = new IntegerContainer();
    private boolean burning = false;
    private ItemStack output = ItemStack.EMPTY;

    public TileBlastFurnace() {
        super(RegisteredTileEntityTypes.TILE_BLAST_FURNACE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("is_burning", burning);
        compound.put("items", itemHandler.serializeNBT());
        compound.put("progress", progressContainer.serializeNBT());
        compound.put("heat", heatHandler.serializeNBT());
        if (output != ItemStack.EMPTY) {
            compound.put("output", output.write(new CompoundNBT()));
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        burning = compound.getBoolean("is_burning");
        itemHandler.deserializeNBT(compound.getCompound("items"));
        progressContainer.deserializeNBT(compound.getCompound("progress"));
        heatHandler.deserializeNBT(compound.getCompound("heat"));
        if (compound.contains("output")) {
            output = ItemStack.read(compound.getCompound("output"));
        }
    }

    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new BlastFurnaceContainer(p_createMenu_1_, p_createMenu_2_, this.pos, this.world);
    }

    @Override
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.BLAST_FURNACE.getNameTextComponent();
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {

            if (!heatHandler.atMinValue()) {
                heatHandler.selfSubtract();
                markDirty();
            }

            if (!progressContainer.atMinValue() && output != ItemStack.EMPTY) {

                if (heatHandler.atMinValue()) {
                    burning = extractFuel();
                    if (burning) {
                        heatHandler.selfSubtract();
                        progressContainer.selfAdd();
                    }
                    markDirty();
                } else {
                    progressContainer.selfAdd();
                }
                markDirty();

                if (progressContainer.atMaxValue()) {
                    if (itemHandler.insertItem(2, output, true) == ItemStack.EMPTY) {
                        itemHandler.insertItem(2, output, false);
                        output = ItemStack.EMPTY;
                        progressContainer.resetValue();
                        markDirty();
                    }
                }
            } else {
                BlastFurnaceRecipe blastFurnaceRecipe = RecipeHelper
                        .getBlastFurnaceRecipe(itemHandler.getStackInSlot(0), world.getRecipeManager());
                if (blastFurnaceRecipe != null) {
                    itemHandler.extractItem(0, blastFurnaceRecipe.getInput().getCount(), false);
                    output = blastFurnaceRecipe.getOutput();
                    progressContainer.setCurrent(0);
                    progressContainer.setMax(blastFurnaceRecipe.getCookTime());
                    markDirty();
                }
            }

        }
    }

    private boolean extractFuel() {
        ItemStack stackInSlot = itemHandler.getStackInSlot(1);
        int burnTime = stackInSlot.getItem() == Items.CHARCOAL ? 200 : 0;
        if (burnTime != 0) {
            heatHandler.setMax(burnTime);
            heatHandler.setCurrent(burnTime);
            itemHandler.extractItem(1, 1, false);
            markDirty();
            return true;
        } else
            return false;
    }

    @Override
    protected MultiBlockPosBox getMap() {
        return MultiBlockStructureMaps.getStructureMaps().get("blast_furnace").rotateTo(getBackDirection());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos pos) {
        return this.getCapability(cap, side);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, BlockPos pos) {
        return getCapability(cap);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public IntegerContainer getProgressContainer() {
        return progressContainer;
    }

    public IntegerContainer getHeatHandler() {
        return heatHandler;
    }

    public boolean isWorking() {
        return !heatHandler.atMinValue() && !progressContainer.atMinValue();
    }

    public Direction getFacingDirection() {
        return super.getFacingDirection();
    }

    public boolean burning() {
        return burning;
    }

    public enum slot {
        INPUT(0), FUEL(1), OUTPUT(2);

        private final int num;

        slot(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
