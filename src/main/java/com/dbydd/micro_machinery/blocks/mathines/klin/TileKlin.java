package com.dbydd.micro_machinery.blocks.mathines.klin;

import com.dbydd.micro_machinery.blocks.mathines.TileEntityBase;
import com.dbydd.micro_machinery.registery_lists.Registereyed_Tileentities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TileKlin extends TileEntityBase implements ITickableTileEntity {

    public TileKlin() {
        super(Registereyed_Tileentities.KLIN_TYPE.getType());
    }

    public TileKlin(TileEntityType type) {
        super(type);
    }

    public void onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        player.sendMessage(new StringTextComponent("actived"));
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }

    @Override
    public void tick() {

    }
}
