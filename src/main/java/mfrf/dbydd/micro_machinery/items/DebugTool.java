package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.blocks.machines.energy_cable.TileEnergyCable;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
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
//            if(tileEntity instanceof TileLathe)
//                context.getPlayer().sendMessage(new StringTextComponent(((TileLathe)tileEntity).getActionContainer().getActionQueue().toString()));
        }
        return ActionResultType.SUCCESS;
    }
}
