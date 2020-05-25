package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class TestItem extends ItemBase {
    public TestItem(String itemname) {
        super(itemname);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
//        if(worldIn.getTileEntity(pos)!=null){
//            player.sendMessage(new TextComponentString(worldIn.getTileEntity(pos).serializeNBT().toString()));
//        }
//        player.sendMessage(new TextComponentString(facing.toString()));

//        IBlockState blockstate = worldIn.getBlockState(pos);
//        if (blockstate.getBlock() instanceof BlockEnergyCableWithoutGenerateForce) {
//            player.sendMessage(new TextComponentString((((EnumMMFETileEntityStatus) blockstate.getProperties().get("statue_" + facing.getName())).getName())));
            EnergyNetSavedData data = EnergyNetSavedData.getData(worldIn);
            List<EnergyNetworkSign> list = data.getNetworkSignList();
//            list.get(0).addMaxEnergyCapacityOfNetwork(25600);
//            data.markDirty();

//        }
//            list.removeIf(new Predicate<EnergyNetworkSign>(){
//
//                @Override
//                public boolean test(EnergyNetworkSign sign) {
//                    return true;
//                }
//            });
//            data.markDirty();
            for (EnergyNetworkSign o : list) {
                player.sendMessage(new TextComponentString("sign:" + o.getSIGN() + "  energy:" + o.getEnergyStoragedOfNetwork() + "  maxenergy" + o.getMaxEnergyCapacityOfNetwork() + "  index:" + list.indexOf(o)));
            }
//        player.sendMessage(new TextComponentString(""+list.get(0).getEnergyStoragedOfNetwork()));
        }
        return EnumActionResult.SUCCESS;
    }
}
