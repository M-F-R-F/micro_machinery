package org.mfrf.micro_machienry.blocks.machines.single_block_machines.fluidpipe;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumFluidPipeState;
import mfrf.dbydd.micro_machinery.recipes.RecipeHelper;
import mfrf.dbydd.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
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

public class FluidPipeTile extends MMTileBase implements ITickableTileEntity {
    private FluidTank fluidTank = new FluidTank(12000) {
        @Override
        protected void onContentsChanged() {
            markDirty();
        }
    };
    private ItemStackHandler blockItemContainer = new ItemStackHandler(1);
    private int material = -1;

    public FluidPipeTile() {
        super(RegisteredTileEntityTypes.TILE_FLUID_PIPE_DEMO.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        fluidTank.readFromNBT(compound.getCompound("fluid"));
        blockItemContainer.deserializeNBT(compound.getCompound("block_item"));
        if (compound.contains("material")) {
            material = compound.getInt("material");
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT write = super.write(compound);
        compound.put("fluid", fluidTank.writeToNBT(new CompoundNBT()));
        write.put("block_item", blockItemContainer.serializeNBT());
        if (material != -1) {
            write.putInt("material", material);
        }
        return write;
    }

    public int getMaterial() {
        if (material == -1) {
            Block block = getBlockState().getBlock();
            if (block == RegisteredBlocks.PIPE_INVAR) {
                material = 1000;
            }
            if (block == RegisteredBlocks.PIPE_STAINLESS_STEEL) {
                material = 4000;
            }
            if (block == RegisteredBlocks.PIPE_TUNGSTEN_STEEL) {
                material = 8000;
            }
            markDirty();
        }
        return material;
    }

    public boolean blocked() {
        return getBlockState().get(FluidPipeBlock.BLOCKED);
    }

    //todo 做到这里
    public boolean ejectToOpenSide(Direction direction, FluidStack ejectStack) {
        BlockPos offset = pos.offset(direction);
        BlockState blockStateToReplace = world.getBlockState(pos);
        if (ejectStack.getAmount() > 1000 && ejectStack.getFluid().getAttributes().canBePlacedInWorld(world, offset, ejectStack)) {
            if (blockStateToReplace.isReplaceable(ejectStack.getFluid()) && blockStateToReplace.getFluidState().getFluid() == Fluids.EMPTY) {
                BlockState blockState = ejectStack.getFluid().getDefaultState().getBlockState();
                world.setBlockState(offset, blockState);
                return true;
            }
        }
        return false;
    }

    public void block(ItemStack blocker) {
        this.blockItemContainer.setStackInSlot(0, blocker);
        world.setBlockState(pos, getBlockState().with(FluidPipeBlock.BLOCKED, true), 49);
        markDirty();
    }

    public ItemStack unBlock() {
        world.setBlockState(pos, getBlockState().with(FluidPipeBlock.BLOCKED, false), 49);
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
                FluidPipeTile destPipe = (FluidPipeTile) offset;
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
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side != null) {
            EnumFluidPipeState enumFluidPipeState = getBlockState().get(FluidPipeBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
            if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN) {
                return LazyOptional.of(() -> fluidTank).cast();
            }
        }
        return super.getCapability(cap, side);
    }

    //todo 喷射液体和物品
    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (!blocked()) {
                ArrayList<Direction> pipeDirections = new ArrayList<>();
                AtomicInteger pipeFluidSum = new AtomicInteger();

                for (Direction side : Direction.values()) {
                    EnumFluidPipeState enumFluidPipeState = getBlockState().get(FluidPipeBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
                    if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN || enumFluidPipeState == EnumFluidPipeState.AUTO_CONNECTED) {
                        BlockPos offset = pos.offset(side);
                        TileEntity tileEntity = world.getTileEntity(offset);

                        if (tileEntity != null) {
                            tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(
                                    iFluidHandler -> {
                                        //if is pipe, check
                                        if (tileEntity.getType() == RegisteredTileEntityTypes.TILE_FLUID_PIPE_DEMO.get()) {
                                            pipeDirections.add(side);
                                            int amount = iFluidHandler.getFluidInTank(0).getAmount();
                                            if (amount - this.fluidTank.getFluidAmount() < -1)
                                                pipeFluidSum.addAndGet(this.fluidTank.getFluidAmount() - amount);
                                        } else {
                                            int fill = iFluidHandler.fill(this.fluidTank.drain(getMaterial(), IFluidHandler.FluidAction.SIMULATE), IFluidHandler.FluidAction.SIMULATE);
                                            iFluidHandler.fill(this.fluidTank.drain(fill, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                                            markDirty();
                                        }

                                    }
                            );
                        }
                    }
                }

                if (!pipeDirections.isEmpty()) {
                    //do average and some interesting
                    if (pipeFluidSum.get() > getMaterial()) {
                        pipeFluidSum.set(getMaterial());
                    }
                    int remain = pipeFluidSum.get() % pipeDirections.size();
                    int averageOut = (pipeFluidSum.get() - remain) / pipeDirections.size();
                    for (Direction direction : pipeDirections) {
                        FluidPipeTile pipeDemoTile = (FluidPipeTile) world.getTileEntity(this.pos.offset(direction));
                        int received = averageOut - pipeDemoTile.receiveFluid(fluidTank.drain(averageOut, IFluidHandler.FluidAction.SIMULATE), direction);
                        fluidTank.drain(received, IFluidHandler.FluidAction.EXECUTE);
                        markDirty();
                    }
                }

            }
            if (world.getGameTime() % Config.HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE.get() == 0) {
                checkPipeState();
            }
        }
    }

    public void checkPipeState() {
        for (Direction value : Direction.values()) {
            BlockPos offset = pos.offset(value);
            BlockState blockState = world.getBlockState(offset);
            if (blockState.getBlock() instanceof FluidPipeBlock) {
                FluidPipeBlock block = (FluidPipeBlock) blockState.getBlock();
                world.setBlockState(pos, block.getState(world, pos), 18);
            }
        }
    }


}
