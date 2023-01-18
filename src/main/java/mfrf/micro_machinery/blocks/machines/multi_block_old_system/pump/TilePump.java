package mfrf.micro_machinery.blocks.machines.multi_block_old_system.pump;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.micro_machinery.blocks.machines.MMTileBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TilePump extends MMTileBase{
    private FluidTank tank = new FluidTank(2000);
    private FEContainer feContainer = new FEContainer(0, 3200) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }

        @Override
        public int selfSubtract() {
            return this.minus(50, false);
        }
    };
    private IntegerContainer cd = new IntegerContainer(0, (int) (2 * (35.0 / Config.HIGH_FREQUENCY_BLOCK_ACTIVE_UPDATE_CYCLE.get())));

    public TilePump() {
        super(RegisteredBlockEntityTypes.TILE_PUMP.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (feContainer.selfSubtract() >= 50) {
                if (cd.atMaxValue()) {
                    BlockPos extractPos = pos.m_142300_(getBlockState().get(BlockPump.FACING).getOpposite()).m_142300_(Direction.DOWN);
                    BlockState extractTarget = world.getBlockState(extractPos);
                    Biome biome = world.getBiome(pos);

                    //in nether,infinity lava
                    if (extractTarget.getBlock() instanceof FlowingFluidBlock) {
                        if (biome == Biomes.NETHER && extractTarget.getBlock() == Blocks.LAVA && extractTarget.getBlock() == Blocks.LAVA) {
                            tank.fill(new FluidStack(Fluids.LAVA, 10), IFluidHandler.FluidAction.EXECUTE);
                        } else if ((biome == Biomes.RIVER || biome == Biomes.OCEAN) && extractTarget.getBlock() == Blocks.WATER) {
                            tank.fill(new FluidStack(Fluids.WATER, 20 - extractTarget.get(FlowingFluidBlock.LEVEL)), IFluidHandler.FluidAction.EXECUTE);
                        } else if (extractTarget.getFluidState().isSource()) {
                            FlowingFluidBlock extractTargetBlock = (FlowingFluidBlock) extractTarget.getBlock();
                            Fluid fluid = extractTargetBlock.getFluid().getFluid();
                            if (tank.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.SIMULATE) == 1000) {
                                Fluid pickupFluid = extractTargetBlock.pickupFluid(world, extractPos, extractTarget);
                                tank.fill(new FluidStack(pickupFluid, 1000), IFluidHandler.FluidAction.EXECUTE);
                            }
                        }
                    }


                    if (!tank.isEmpty()) {
                        BlockEntity tileEntity = world.getBlockEntity(pos.m_142300_(getBackDirection().getOpposite()));
                        if (tileEntity != null) {
                            LazyOptional<IFluidHandler> capability = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getBackDirection());
                            capability.ifPresent(iFluidHandler -> {
                                tank.drain(iFluidHandler.fill(tank.drain(tank.getFluidAmount(), IFluidHandler.FluidAction.SIMULATE), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                                setChanged();
                            });
                        }
                    }

                    cd.resetValue();
                }

                cd.selfAdd();
            }

        }
    }

    @Override
    public void read(CompoundTag compound) {
        super.read(compound);
        tank.readFromNBT(compound);
        feContainer.deserializeNBT(compound.getCompound("fe_container"));
        cd.deserializeNBT(compound.getCompound("cd"));
    }

    @Override
    public CompoundTag write(CompoundTag compound) {
        super.write(compound);
        tank.writeToNBT(compound);
        compound.put("fe_container", feContainer.serializeNBT());
        compound.put("cd", cd.serializeNBT());
        return compound;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == getBackDirection().getOpposite()) {
            return LazyOptional.of(() -> tank).cast();
        }
        if (side == Direction.UP) {
            return LazyOptional.of(() -> feContainer).cast();
        }
        return super.getCapability(cap, side);
    }
}
