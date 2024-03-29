package mfrf.micro_machinery.registeried_lists;

import mfrf.micro_machinery.block.fluids.MMFluidBase;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FluidBucketDispenserRegister {

    public static List<MMFluidBase> fluids = new ArrayList<>();

    @SubscribeEvent
    public static void onDispenserRegister(FMLCommonSetupEvent event) {

        for (MMFluidBase fluidBase : fluids) {
            DispenserBlock.registerDispenseBehavior(fluidBase.fluid_bucket.get(), new DefaultDispenseItemBehavior() {
                private final DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior();
                /**
                 * Dispense the specified stack, play the dispense sound and spawn particles.
                 */
                public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                    BucketItem bucketitem = (BucketItem) stack.getItem();
                    BlockPos blockpos = source.getBlockPos().m_142300_(source.getBlockState().get(DispenserBlock.FACING));
                    World world = source.getWorld();
                    if (bucketitem.tryPlaceContainedLiquid(null, world, blockpos, null)) {
                        bucketitem.onLiquidPlaced(world, stack, blockpos);
                        return new ItemStack(Items.BUCKET);
                    } else {
                        return this.dispenseItemBehavior.dispense(source, stack);
                    }
                }
            });
        }
        }

}
