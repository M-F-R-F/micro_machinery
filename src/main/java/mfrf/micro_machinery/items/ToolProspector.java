package mfrf.micro_machinery.items;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.blocks.MMOreBase;
import net.minecraft.world.level.block.Block
import net.minecraft.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
        super(new Properties().maxStackSize(1).group(MicroMachinery.MMTAB).maxDamage(36), "prospector");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        List<Block> TARGET_BLOCKS = new ArrayList<>();
        TARGET_BLOCKS.addAll(MMOreBase.oreList);
        TARGET_BLOCKS.addAll(Arrays.asList(Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE));
        World world = context.getWorld();
        if (!world.isRemote()) {
            Player player = context.getPlayer();
            BlockPos contextPos = context.getPos();
            int count = 0;
            if (world.chunkExists(contextPos.getX() >> 4, contextPos.getZ() >> 4)) {
                for (int currentY = 0; currentY > -32; currentY--) {
                    for (int currentX = -32; currentX < 32; currentX++) {
                        for (int currentZ = -32; currentZ < 32; currentZ++) {
                            if (TARGET_BLOCKS.contains(world.getBlockState(new BlockPos(contextPos.getX() + currentX, contextPos.getY() + currentY, contextPos.getZ() + currentZ)).getBlock())) {
                                count++;
                            }
                        }
                    }
                }
                player.sendMessage(new TranslationTextComponent(count > 64 ? STRONG_FEEDBACK : count > 32 ? OBVIOUS_FEEDBACK : count > 16 ? WEAK_FEEDBACK : count > 4 ? WEAKEST_FEEDBACK : NO_FEEDBACK, TextFormatting.DARK_GRAY));
            }
            context.getItem().damageItem(1, context.getPlayer(), (player1) -> {
                player1.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return ActionResultType.SUCCESS;
    }

}
