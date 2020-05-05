package com.dbydd.micro_machinery.items;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.blocks.machine.BlockEnergyCableWithoutGenerateForce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class TestItem extends ItemBase {
    public TestItem(String itemname) {
        super(itemname);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        if(worldIn.getTileEntity(pos)!=null){
//            player.sendMessage(new TextComponentString(worldIn.getTileEntity(pos).serializeNBT().toString()));
//        }
//        player.sendMessage(new TextComponentString(facing.toString()));
        IBlockState blockstate = worldIn.getBlockState(pos);
        if (blockstate.getBlock() instanceof BlockEnergyCableWithoutGenerateForce) {
            player.sendMessage(new TextComponentString((((EnumMMFETileEntityStatus) blockstate.getProperties().get("statue_" + facing.getName())).getName())));
        }
        return EnumActionResult.SUCCESS;
    }
}
