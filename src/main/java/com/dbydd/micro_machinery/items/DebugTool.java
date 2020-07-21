package com.dbydd.micro_machinery.items;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.StringTextComponent;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        context.getPlayer().sendMessage(new StringTextComponent(context.getWorld().getBlockState(context.getPos()).get(HorizontalBlock.HORIZONTAL_FACING).toString()));
        return ActionResultType.SUCCESS;
    }
}
