package mfrf.micro_machinery.items;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolProspector extends MMItemBase {
    private static final String STRONG_FEEDBACK = MicroMachinery.MODID + ".notify." + "strong_feedback";
    private static final String OBVIOUS_FEEDBACK = MicroMachinery.MODID + ".notify." + "obvious_feedback";
    private static final String WEAK_FEEDBACK = MicroMachinery.MODID + ".notify." + "weak_feedback";
    private static final String WEAKEST_FEEDBACK = MicroMachinery.MODID + ".notify." + "weakest_feedback";
    private static final String NO_FEEDBACK = MicroMachinery.MODID + ".notify." + "no_feedback";

    public ToolProspector() {
        super(new Properties().stacksTo(1).durability(36));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand pUsedHand) {
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
                    if (world.getBlockState(new BlockPos(pos.getX() + currentX, pos.getY() + currentY, pos.getZ() + currentZ)).getTags().anyMatch(blockTagKey -> blockTagKey.location().getPath().contains("blocks/ores"))) {
                        count++;
                    }
                }
            }
            player.displayClientMessage(Component.translatable(count > 64 ? STRONG_FEEDBACK : count > 32 ? OBVIOUS_FEEDBACK : count > 16 ? WEAK_FEEDBACK : count > 4 ? WEAKEST_FEEDBACK : NO_FEEDBACK).withStyle(ChatFormatting.DARK_GRAY), true);
        }

        damageItem(itemInHand, 1, player, (player1) -> {
            player1.broadcastBreakEvent(InteractionHand.MAIN_HAND);
        });
        return InteractionResultHolder.sidedSuccess(itemInHand, clientSide);
    }

}
