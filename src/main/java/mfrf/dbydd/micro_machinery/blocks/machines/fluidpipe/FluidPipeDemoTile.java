package mfrf.dbydd.micro_machinery.blocks.machines.fluidpipe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumFluidPipeState;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FluidPipeDemoTile extends MMTileBase implements ITickableTileEntity {
    private FluidTank fluidTank = new FluidTank(12000) {
        @Override
        protected void onContentsChanged() {
            markDirty();
        }
    };
    private ItemStackHandler blockItemContainer = new ItemStackHandler(1);

    public FluidPipeDemoTile() {
        super(RegisteredTileEntityTypes.TILE_FLUID_PIPE_DEMO.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        fluidTank.readFromNBT(compound.getCompound("fluid"));
        blockItemContainer.deserializeNBT(compound.getCompound("block_item"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        compound.put("fluid", fluidTank.writeToNBT(new CompoundNBT()));
        write.put("block_item", blockItemContainer.serializeNBT());
        return write;
    }

    public boolean blocked() {
        return getBlockState().get(FluidPipeDemoBlock.BLOCKED);
    }

    public void block(ItemStack blocker) {
        this.blockItemContainer.setStackInSlot(0, blocker);
        world.setBlockState(pos, getBlockState().with(FluidPipeDemoBlock.BLOCKED, true), 49);
        markDirty();
    }

    public ItemStack unBlock() {
        world.setBlockState(pos, getBlockState().with(FluidPipeDemoBlock.BLOCKED, false), 49);
        ItemStack copy = this.blockItemContainer.getStackInSlot(0).copy();
        this.blockItemContainer.setStackInSlot(0, ItemStack.EMPTY);
        markDirty();
        return copy;
    }

    private int receiveFluid(FluidStack stack, Direction direction) {
        FluidStack thisFluid = this.fluidTank.getFluid();
        Fluid receiveFluid = stack.getFluid();
        int thisAmount = fluidTank.getFluidAmount();
        int receiveAmount = stack.getAmount();

        if (blocked()) {
            TileEntity offset = world.getTileEntity(pos.offset(direction));
            if (offset != null && offset.getType() == RegisteredTileEntityTypes.TILE_FLUID_PIPE_DEMO.get()) {
                FluidPipeDemoTile destPipe = (FluidPipeDemoTile) offset;
                if (!destPipe.blocked() && destPipe.fluidTank.getFluidAmount() < thisAmount + receiveAmount) {
                    destPipe.block(this.unBlock());
                }
            }
        }

        if (stack.isFluidEqual(thisFluid)) {
            return this.fluidTank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        } else {
            FluidCrashRecipe fluidCrashRecipe = RecipeHelper.getFluidCrashRecipe(thisFluid, stack, world.getRecipeManager());
            boolean generateTrash = fluidCrashRecipe == null;
            if (!generateTrash) {
                if (receiveAmount < thisAmount) {
                    int generateCount;

                    int thisUse = 0;
                    int receivedUse = 0;
                    if (fluidCrashRecipe.fluidA == thisFluid.getFluid().getRegistryName()) {
                        generateCount = Math.min(thisAmount / fluidCrashRecipe.fluidAUsage, receiveAmount / fluidCrashRecipe.fluidBUsage);
                        if (generateCount == 0) {
                            thisUse = Math.min(receiveAmount, fluidCrashRecipe.fluidAUsage);
                            receivedUse = Math.min(receiveAmount, fluidCrashRecipe.fluidBUsage);
                            if (!this.blocked()) {
                                generateTrash = true;
                                generateCount = 1;
                            }
                        } else {
                            if (this.blockItemContainer.getStackInSlot(0).isItemEqual(fluidCrashRecipe.generate) && blockItemContainer.getStackInSlot(0).getCount() + fluidCrashRecipe.generate.getCount() <= fluidCrashRecipe.generate.getMaxStackSize()) {
                                thisUse = generateCount * fluidCrashRecipe.fluidAUsage;
                                receivedUse = fluidCrashRecipe.fluidBUsage * generateCount;
                            }
                        }
                    } else {
                        generateCount = Math.min(thisAmount / fluidCrashRecipe.fluidBUsage, receiveAmount / fluidCrashRecipe.fluidAUsage);
                        if (generateCount == 0) {
                            thisUse = Math.min(receiveAmount, fluidCrashRecipe.fluidBUsage);
                            receivedUse = Math.min(receiveAmount, fluidCrashRecipe.fluidAUsage);
                            if (!this.blocked()) {
                                generateTrash = true;
                                generateCount = 1;
                            }
                            } else {
                            if (this.blockItemContainer.getStackInSlot(0).isItemEqual(fluidCrashRecipe.generate) && blockItemContainer.getStackInSlot(0).getCount() + fluidCrashRecipe.generate.getCount() <= fluidCrashRecipe.generate.getMaxStackSize()) {
                                thisUse = generateCount * fluidCrashRecipe.fluidBUsage;
                                receivedUse = fluidCrashRecipe.fluidAUsage * generateCount;
                            }
                        }
                        }

                        thisAmount -= thisUse;
                        receiveAmount -= receivedUse;

                        if (thisAmount == 0 && receiveAmount != 0) {
                            this.fluidTank.setFluid(new FluidStack(receiveFluid, receiveAmount));
                        } else {
                            this.fluidTank.setFluid(new FluidStack(thisFluid, thisAmount));
                        }

                        if (!generateTrash) {
                            block(new ItemStack(fluidCrashRecipe.generate.getItem(), Math.min(fluidCrashRecipe.generate.getCount() * generateCount, 64)));
                        }

                    }

                    if (generateTrash) {
                        block(new ItemStack(fluidCrashRecipe.generate.getItem(), 1));
                    }

                }
                markDirty();
                return receiveAmount;
            }
        }

        @Nonnull
        @Override
        public <T > LazyOptional < T > getCapability(@Nonnull Capability < T > cap, @Nullable Direction side) {
            if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
                EnumFluidPipeState enumFluidPipeState = ((FluidPipeDemoBlock) RegisteredBlocks.FLUIDPIPE_DEMO).getState(world, pos).get(FluidPipeDemoBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
                if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN) {
                    return LazyOptional.of(() -> fluidTank).cast();
                }
            }
            return super.getCapability(cap, side);
        }

        @Override
        public void tick () {
            if (!world.isRemote() && !blocked()) {
                ArrayList<Direction> pipeDirections = new ArrayList<>();
                AtomicInteger pipeFluidSum = new AtomicInteger();

                for (Direction side : Direction.values()) {
                    EnumFluidPipeState enumFluidPipeState = ((FluidPipeDemoBlock) RegisteredBlocks.FLUIDPIPE_DEMO).getState(world, pos).get(FluidPipeDemoBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
                    if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN) {
                        BlockPos offset = pos.offset(side);
                        TileEntity tileEntity = world.getTileEntity(offset);

                        tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(
                                iFluidHandler -> {
                                    //if is pipe, check
                                    if (tileEntity.getType() == RegisteredTileEntityTypes.TILE_FLUID_PIPE_DEMO.get()) {
                                        pipeDirections.add(side);
                                        int amount = iFluidHandler.getFluidInTank(0).getAmount();
                                        if (amount - this.fluidTank.getFluidAmount() < -1)
                                            pipeFluidSum.addAndGet(this.fluidTank.getFluidAmount() - amount);
                                    } else {
                                        int fill = iFluidHandler.fill(this.fluidTank.getFluid(), IFluidHandler.FluidAction.SIMULATE);
                                        iFluidHandler.fill(this.fluidTank.drain(fill, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                                        markDirty();
                                    }

                                }
                                //todo push blockItem
                        );
                    }
                }

                //do average and some interesting
                int remain = pipeFluidSum.get() % pipeDirections.size();
                int averageOut = (pipeFluidSum.get() - remain) / pipeDirections.size();
                for (Direction direction : pipeDirections) {
                    FluidPipeDemoTile pipeDemoTile = (FluidPipeDemoTile) world.getTileEntity(this.pos.offset(direction));
                    int received = averageOut - pipeDemoTile.receiveFluid(fluidTank.drain(averageOut, IFluidHandler.FluidAction.SIMULATE), direction);
                    fluidTank.drain(received, IFluidHandler.FluidAction.EXECUTE);
                    markDirty();
                }

            }
        }

        private void pushBlocker (Direction direction,int currentPressure){

        }
    }
