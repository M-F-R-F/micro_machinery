package mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.pump;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.block.machines.MMTileBase;
import mfrf.micro_machinery.block.machines.multiblock_new_system.expand_machine.MainTile;
import mfrf.micro_machinery.registry_lists.MMBlockEntityTypes;
import mfrf.micro_machinery.utils.FEContainer;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.gui.font.providers.UnihexProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TilePump extends MainTile {
    private FluidTank tank = new FluidTank(2000);
    private IntegerContainer coolDown = new IntegerContainer(0, 40);

    private FEContainer feContainer = new FEContainer(0, 6400) {
        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    };

    public TilePump(BlockPos pos, BlockState state) {
        super(MMBlockEntityTypes.TILE_PUMP.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        CompoundTag tank = this.tank.writeToNBT(new CompoundTag());
        CompoundTag coolDown = this.coolDown.serializeNBT();
        CompoundTag fe_container = feContainer.serializeNBT();
        pTag.put("tank", tank);
        pTag.put("cool_down", coolDown);
        pTag.put("fe_container", fe_container);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        tank.readFromNBT(pTag.getCompound("tank"));
        coolDown.deserializeNBT(pTag.getCompound("cool_down"));
        feContainer.deserializeNBT(pTag.getCompound("fe_container"));
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (world.isClientSide) return;
        TilePump tilePump = (TilePump) blockEntity;
        FluidTank tank = tilePump.tank;
        Direction facingDirection = tilePump.getFacingDirection();
        world.getBlockEntity(pos.relative(facingDirection)).getCapability(ForgeCapabilities.FLUID_HANDLER, facingDirection.getOpposite()).ifPresent(
                iFluidHandler -> {
                    tank.drain(iFluidHandler.fill(tank.drain(tank.getFluidAmount(), FluidTank.FluidAction.SIMULATE), FluidTank.FluidAction.EXECUTE), FluidTank.FluidAction.EXECUTE);
                    tilePump.setChanged();
                }
        );


        if (tilePump.coolDown.atMaxValue()) {

            BlockPos below = pos.below();

            BlockState extractFrom = world.getBlockState(below);
            FluidState fluidState = extractFrom.getFluidState();
            if (!fluidState.isEmpty()) {
                if (fluidState.is(Fluids.WATER)) {
                    if (fluidState.isSource()) {
                        int fill = tank.fill(new FluidStack(fluidState.getType(), 25), FluidTank.FluidAction.SIMULATE);
                        if (fill == 25) {
                            tank.fill(new FluidStack(fluidState.getType(), 25), FluidTank.FluidAction.EXECUTE);
                            tilePump.feContainer.extractEnergy(64, false);
                            tilePump.setChanged();
                        }
                    } else {
                        int fill = tank.fill(new FluidStack(fluidState.getType(), 20), FluidTank.FluidAction.SIMULATE);
                        if (fill == 20) {
                            tank.fill(new FluidStack(fluidState.getType(), 25), FluidTank.FluidAction.EXECUTE);
                            tilePump.feContainer.extractEnergy(64, false);
                            tilePump.setChanged();
                        }
                    }
                } else {
                    if (fluidState.isSource()) {
                        int fill = tank.fill(new FluidStack(fluidState.getType(), 1000), FluidTank.FluidAction.SIMULATE);
                        if (fill == 1000) {
                            tank.fill(new FluidStack(fluidState.getType(), 1000), FluidTank.FluidAction.EXECUTE);
                            tilePump.feContainer.extractEnergy(64, false);
                            tilePump.coolDown.resetValue();
                            if (!(fluidState.is(Fluids.LAVA) && world.getBiome(below).is(BiomeTags.IS_NETHER))) {
                                world.setBlock(below, Blocks.AIR.defaultBlockState(), 3);
                            }
                            tilePump.setChanged();
                        }
                    }

                }
            }
        } else {
            tilePump.coolDown.selfAdd();
            tilePump.setChanged();
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (side == getBackDirection() && cap == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> tank).cast();
        return LazyOptional.empty();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side, BlockPos worldPosition) {
        if (side == Direction.UP && cap == ForgeCapabilities.ENERGY) return LazyOptional.of(() -> feContainer).cast();
        return LazyOptional.empty();
    }
}
