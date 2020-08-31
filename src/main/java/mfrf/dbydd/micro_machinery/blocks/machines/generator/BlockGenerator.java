package mfrf.dbydd.micro_machinery.blocks.machines.generator;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockGenerator extends MMBlockTileProviderBase {
    public static final BooleanProperty ISBURNING = BooleanProperty.create("isburning");

    public BlockGenerator() {
        super(Properties.create(Material.IRON).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3.0f), "generator");
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ISBURNING, false));
    }

    public static void setIsburning(boolean isburning, World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(ISBURNING, isburning), 3);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ISBURNING);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote() && handIn == Hand.MAIN_HAND) {
            ItemStack heldItem = player.getHeldItem(handIn);
            TileGenerator tileGenerator = (TileGenerator) worldIn.getTileEntity(pos);
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof BucketItem) {
                BucketItem item = (BucketItem) heldItem.getItem();
                if (item.getFluid() == Fluids.WATER) {
                    LazyOptional<IFluidHandler> capability = tileGenerator.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
                    capability.ifPresent(iFluidHandler -> {
                        int fill = iFluidHandler.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.SIMULATE);
                        if (fill != 0) {
                            iFluidHandler.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
                            tileGenerator.markDirty2();
                            if (!player.isCreative()) {
                                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                            }
                        }
                    });
                }
            } else {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileGenerator, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeBlockPos(tileGenerator.getPos());
                });
            }

        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileGenerator();
    }
}
