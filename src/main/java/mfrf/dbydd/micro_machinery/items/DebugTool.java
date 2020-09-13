package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.blocks.machines.energy_cable.TileEnergyCable;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DebugTool extends MMItemBase {
    public DebugTool() {
        super("debug_tool");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
            TileEntity tileEntity = world.getTileEntity(context.getPos());
            if(tileEntity instanceof TileEnergyCable){
                context.getPlayer().sendMessage(new StringTextComponent("current:" + ((TileEnergyCable)tileEntity).getContainer().getCurrent()));
            }
        }
        return ActionResultType.SUCCESS;
    }
}
