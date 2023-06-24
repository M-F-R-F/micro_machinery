package mfrf.micro_machinery.items;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.blocks.MMOreBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolProspector extends MMItemBase {
    private static final String STRONG_FEEDBACK = MicroMachinery.MODID + ".notify." + "strong_feedback";
    private static final String OBVIOUS_FEEDBACK = MicroMachinery.MODID + ".notify." + "obvious_feedback";
    private static final String WEAK_FEEDBACK = MicroMachinery.MODID + ".notify." + "weak_feedback";
    private static final String WEAKEST_FEEDBACK = MicroMachinery.MODID + ".notify." + "weakest_feedback";
    private static final String NO_FEEDBACK = MicroMachinery.MODID + ".notify." + "no_feedback";

    public ToolProspector() {
        super(new Properties().stacksTo(1).m_41491_(MicroMachinery.MMTAB).durability(36), "prospector");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand pUsedHand) {
        List<Block> TARGET_BLOCKS = new ArrayList<>();
        TARGET_BLOCKS.addAll(MMOreBase.oreList);
        TARGET_BLOCKS.addAll(Arrays.asList(Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE));
        ItemStack itemInHand = player.getItemInHand(pUsedHand);
        boolean clientSide = world.isClientSide();
        BlockPos pos = player.getOnPos();
        int count = 0;
//            int chunkX = pos.getX() >> 4;
//            int chunkZ = pos.getZ() >> 4;
//            if (world.hasChunk(chunkX, chunkZ)) {
//                world.ge
//            }
        for (int currentY = 0; currentY > -32; currentY--) {
            for (int currentX = -32; currentX < 32; currentX++) {
                for (int currentZ = -32; currentZ < 32; currentZ++) {
                    if (TARGET_BLOCKS.contains(world.getBlockState(new BlockPos(pos.getX() + currentX, pos.getY() + currentY, pos.getZ() + currentZ)).getBlock())) {
                        count++;
                    }
                }
            }
            player.displayClientMessage(new TranslatableComponent(count > 64 ? STRONG_FEEDBACK : count > 32 ? OBVIOUS_FEEDBACK : count > 16 ? WEAK_FEEDBACK : count > 4 ? WEAKEST_FEEDBACK : NO_FEEDBACK).withStyle(ChatFormatting.DARK_GRAY), true);
        }

        damageItem(itemInHand, 1, player, (player1) -> {
            player1.broadcastBreakEvent(InteractionHand.MAIN_HAND);
        });
        return InteractionResultHolder.sidedSuccess(itemInHand, clientSide);
    }

}
