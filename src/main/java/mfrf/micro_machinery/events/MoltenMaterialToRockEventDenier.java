//package mfrf.micro_machinery.events;
//
//import mfrf.micro_machinery.MicroMachinery;
//import mfrf.micro_machinery.block.fluids.MMFluidBase;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraftforge.event.level.BlockEvent;
//import net.minecraftforge.eventbus.api.Event;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.HashMap;
//
//
//public class MoltenMaterialToRockEventDenier {
//    private static final HashMap<MMFluidBase, Block> BLOCK_CACHE = new HashMap<>();
//
//    public static void onMoltenMaterialGenerateStone(BlockEvent.FluidPlaceBlockEvent event) {
//        FluidState fluid = event.getLevel().getFluidState(event.getLiquidPos());
//        MMFluidBase mmFluidBase = MMFluidBase.getFluidMap().get(fluid.getType().getFluidType());
//        if (mmFluidBase != null && mmFluidBase.is_molten_material) {
//            Block block = tryGetCachedOrNewOne(mmFluidBase);
//            if (block != null) {
//                event.setNewState(block.defaultBlockState());
//            }
//        }
//        event.setResult(Event.Result.ALLOW);
//    }
//
//    private static Block tryGetCachedOrNewOne(MMFluidBase key) {
//        if (!BLOCK_CACHE.containsKey(key)) {
//            BLOCK_CACHE.put(key, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MicroMachinery.MODID, key.getName() + "_discarded")));
//        }
//        return BLOCK_CACHE.get(key);
//    }
//}
