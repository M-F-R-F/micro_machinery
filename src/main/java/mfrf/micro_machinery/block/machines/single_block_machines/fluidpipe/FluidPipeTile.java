package mfrf.micro_machinery.block.machines.single_block_machines.fluidpipe;

import mfrf.micro_machinery.Config;
import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.enums.EnumFluidPipeState;
import mfrf.micro_machinery.recipes.RecipeHelper;
import mfrf.micro_machinery.recipes.fluid_crash.FluidCrashRecipe;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FluidPipeTile extends MMTileBase {
    private final FluidTank fluidTank = new FluidTank(12000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };
    private final ItemStackHandler blockItemContainer = new ItemStackHandler(1);
    private int material = -1;

    public FluidPipeTile(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.FLUID_PIPE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        fluidTank.readFromNBT(compound.getCompound("fluid"));
        blockItemContainer.deserializeNBT(compound.getCompound("block_item"));
        if (compound.contains("material")) {
            material = compound.getInt("material");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("fluid", fluidTank.writeToNBT(new CompoundTag()));
        pTag.put("block_item", blockItemContainer.serializeNBT());
        if (material != -1) {
            pTag.putInt("material", material);
        }
    }

    public int getMaterial() {
        if (material == -1) {
            Block block = getBlockState().getBlock();
            if (block == MMBlocks.PIPE_1.getKey().get()) {
                material = 1000;
            }
            if (block == MMBlocks.PIPE_2.getKey().get()) {
                material = 4000;
            }
            if (block == MMBlocks.PIPE_3.getKey().get()) {
                material = 8000;
            }
            setChanged();
        }
        return material;
    }

    public static boolean blocked(BlockState state) {
        return state.getValue(FluidPipeBlock.BLOCKED);
    }

    //todo 做到这里
    public boolean ejectToOpenSide(Direction direction, FluidStack ejectStack) {
        BlockPos offset = getBlockPos().relative(direction);
        BlockState blockStateToReplace = level.getBlockState(offset);
        if (ejectStack.getAmount() > 1000 && ejectStack.getFluid().getFluidType().canBePlacedInLevel(level, offset,
                ejectStack)) {
            if (blockStateToReplace.canBeReplaced(ejectStack.getFluid()) && blockStateToReplace.getFluidState().getType() == Fluids.EMPTY) {
                BlockState blockState = ejectStack.getFluid().defaultFluidState().createLegacyBlock();
                level.setBlockAndUpdate(offset, blockState);
                return true;
            }
        }
        return false;
    }

    public void block(ItemStack blocker) {
        this.blockItemContainer.setStackInSlot(0, blocker);
        level.setBlock(worldPosition, getBlockState().setValue(FluidPipeBlock.BLOCKED, true), 49);
        setChanged();
    }

    public ItemStack unBlock() {
        level.setBlock(worldPosition, getBlockState().setValue(FluidPipeBlock.BLOCKED, false), 49);
        ItemStack copy = this.blockItemContainer.getStackInSlot(0).copy();
        this.blockItemContainer.setStackInSlot(0, ItemStack.EMPTY);
        setChanged();
        return copy;
    }

    private int receiveFluid(FluidStack stack, Direction direction) {
        FluidStack thisFluid = this.fluidTank.getFluid();
        Fluid receiveFluid = stack.getFluid();
        int thisAmount = fluidTank.getFluidAmount();
        int receiveAmount = stack.getAmount();

        if (blocked(getBlockState())) {
            BlockEntity offset = level.getBlockEntity(worldPosition.relative(direction));
            if (offset != null && offset.getType() == MMBlockEntityTypes.FLUID_PIPE.get()) {
                FluidPipeTile destPipe = (FluidPipeTile) offset;
                if (!blocked(getBlockState()) && destPipe.fluidTank.getFluidAmount() < thisAmount + receiveAmount) {
                    destPipe.block(this.unBlock());
                }
            }
        }

        if (stack.isFluidEqual(thisFluid)) {
            return this.fluidTank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        } else {
            FluidCrashRecipe fluidCrashRecipe = RecipeHelper.getFluidCrashRecipe(thisFluid, stack, level.getRecipeManager());
            boolean generateTrash = fluidCrashRecipe == null;
            boolean update = false;
            if (!generateTrash) {
                if (receiveAmount < thisAmount) {
                    int generateCount;

                    int thisUse = 0;
                    int receivedUse = 0;
//                    if (fluidCrashRecipe.fluidA == thisFluid.getFluid().getRegistryName()) {
                    if (false) {
                        generateCount = Math.min(thisAmount / fluidCrashRecipe.fluidAUsage, receiveAmount / fluidCrashRecipe.fluidBUsage);
                        if (generateCount == 0) {
                            thisUse = Math.min(receiveAmount, fluidCrashRecipe.fluidAUsage);
                            receivedUse = Math.min(receiveAmount, fluidCrashRecipe.fluidBUsage);
                            if (!blocked(getBlockState())) {
                                generateTrash = true;
                                generateCount = 1;
                            }
                        } else {
                            if (this.blockItemContainer.getStackInSlot(0).is(fluidCrashRecipe.generate.getItem()) && blockItemContainer.getStackInSlot(0).getCount() + fluidCrashRecipe.generate.getCount() <= fluidCrashRecipe.generate.getMaxStackSize()) {
                                thisUse = generateCount * fluidCrashRecipe.fluidAUsage;
                                receivedUse = fluidCrashRecipe.fluidBUsage * generateCount;
                            }
                        }
                    } else {
                        generateCount = Math.min(thisAmount / fluidCrashRecipe.fluidBUsage, receiveAmount / fluidCrashRecipe.fluidAUsage);
                        if (generateCount == 0) {
                            thisUse = Math.min(receiveAmount, fluidCrashRecipe.fluidBUsage);
                            receivedUse = Math.min(receiveAmount, fluidCrashRecipe.fluidAUsage);
                            if (!blocked(getBlockState())) {
                                generateTrash = true;
                                generateCount = 1;
                            }
                        } else {
                            if (this.blockItemContainer.getStackInSlot(0).is(fluidCrashRecipe.generate.getItem()) && blockItemContainer.getStackInSlot(0).getCount() + fluidCrashRecipe.generate.getCount() <= fluidCrashRecipe.generate.getMaxStackSize()) {
                                thisUse = generateCount * fluidCrashRecipe.fluidBUsage;
                                receivedUse = fluidCrashRecipe.fluidAUsage * generateCount;
                            }
                        }
                    }

                    thisAmount -= thisUse;
                    receiveAmount -= receivedUse;

                    if (thisAmount == 0 && receiveAmount != 0) {
                        this.fluidTank.setFluid(new FluidStack(receiveFluid, receiveAmount));
                        setChanged();
                    } else {
                        this.fluidTank.setFluid(new FluidStack(thisFluid, thisAmount));
                        setChanged();
                    }

                    if (!generateTrash) {
                        block(new ItemStack(fluidCrashRecipe.generate.getItem(), Math.min(fluidCrashRecipe.generate.getCount() * generateCount, 64)));
                    }

                }

                if (generateTrash) {
                    block(new ItemStack(fluidCrashRecipe.generate.getItem(), 1));
                }

            }
//            setChanged();
            return receiveAmount;
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER && side != null) {
            EnumFluidPipeState enumFluidPipeState = getBlockState().getValue(FluidPipeBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
            if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN) {
                return LazyOptional.of(() -> fluidTank).cast();
            }
        }
        return super.getCapability(cap, side);
    }

    //todo 喷射液体和物品
    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!world.isClientSide() && blockEntity instanceof FluidPipeTile fluidPipeTile) {
            if (!blocked(state)) {
                ArrayList<Direction> pipeDirections = new ArrayList<>();
                AtomicInteger pipeFluidSum = new AtomicInteger();

                for (Direction side : Direction.values()) {
                    EnumFluidPipeState enumFluidPipeState = state.getValue(FluidPipeBlock.DIRECTION_ENUM_PROPERTY_MAP.get(side));
                    if (enumFluidPipeState == EnumFluidPipeState.AUTO_TRUE || enumFluidPipeState == EnumFluidPipeState.OPEN || enumFluidPipeState == EnumFluidPipeState.AUTO_CONNECTED) {
                        BlockPos offset = pos.relative(side);
                        BlockEntity tileEntity = world.getBlockEntity(offset);

                        if (tileEntity != null) {
                            tileEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, side.getOpposite()).ifPresent(
                                    iFluidHandler -> {
                                        //if is pipe, check
                                        if (tileEntity.getType() == MMBlockEntityTypes.FLUID_PIPE.get()) {
                                            pipeDirections.add(side);
                                            int amount = iFluidHandler.getFluidInTank(0).getAmount();
                                            if (amount - fluidPipeTile.fluidTank.getFluidAmount() < -1)
                                                pipeFluidSum.addAndGet(fluidPipeTile.fluidTank.getFluidAmount() - amount);
                                        } else {
                                            int fill = iFluidHandler.fill(fluidPipeTile.fluidTank.drain(fluidPipeTile.getMaterial(), IFluidHandler.FluidAction.SIMULATE), IFluidHandler.FluidAction.SIMULATE);
                                            iFluidHandler.fill(fluidPipeTile.fluidTank.drain(fill, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                                            fluidPipeTile.setChanged();
                                        }

                                    }
                            );
                        }
                    }
                }

                if (!pipeDirections.isEmpty()) {
                    //do average and some interesting
                    if (pipeFluidSum.get() > fluidPipeTile.getMaterial()) {
                        pipeFluidSum.set(fluidPipeTile.getMaterial());
                    }
                    int remain = pipeFluidSum.get() % pipeDirections.size();
                    int averageOut = (pipeFluidSum.get() - remain) / pipeDirections.size();
                    for (Direction direction : pipeDirections) {
                        FluidPipeTile pipeDemoTile = (FluidPipeTile) world.getBlockEntity(fluidPipeTile.getBlockPos().relative(direction));
                        int received = averageOut - pipeDemoTile.receiveFluid(fluidPipeTile.fluidTank.drain(averageOut, IFluidHandler.FluidAction.SIMULATE), direction);
                        fluidPipeTile.fluidTank.drain(received, IFluidHandler.FluidAction.EXECUTE);
                        fluidPipeTile.setChanged();
                    }
                }

            }
            if (world.getGameTime() % Config.HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE.get() == 0) {
                fluidPipeTile.checkPipeState();
            }
        }
    }

    public void checkPipeState() {
        for (Direction value : Direction.values()) {
            BlockPos pos = getBlockPos();
            BlockPos offset = pos.relative(value);
            if (level.getBlockState(offset).getBlock() instanceof FluidPipeBlock) {
                level.setBlock(pos, ((FluidPipeBlock) getBlockState().getBlock()).getState(level, pos), 18);
                break;
            }
        }
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }
}
