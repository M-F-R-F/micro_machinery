package mfrf.dbydd.micro_machinery.blocks.machines.pump;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredTileEntityTypes;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class TilePump extends MMTileBase implements ITickableTileEntity {
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
        super(RegisteredTileEntityTypes.TILE_PUMP.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (feContainer.selfSubtract() >= 50) {
                if (cd.atMaxValue()) {
                    BlockPos extractPos = pos.offset(getBlockState().get(BlockPump.FACING).getOpposite()).offset(Direction.DOWN);
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

                    cd.resetValue();
                }

                cd.selfAdd();
            }

        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        tank.readFromNBT(compound);
        feContainer.deserializeNBT(compound.getCompound("fe_container"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        tank.writeToNBT(compound);
        compound.put("fe_container", feContainer.serializeNBT());
        return compound;
    }

}
