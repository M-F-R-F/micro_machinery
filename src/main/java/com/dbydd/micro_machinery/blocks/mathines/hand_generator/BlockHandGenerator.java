package com.dbydd.micro_machinery.blocks.mathines.hand_generator;

import com.dbydd.micro_machinery.blocks.mathines.MMBlockTileProviderBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockHandGenerator extends MMBlockTileProviderBase {

    public BlockHandGenerator() {
        super(Properties.create(Material.IRON).hardnessAndResistance(3).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(1), "hand_generator");
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote() && handIn == Hand.MAIN_HAND){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof TileHandGenerator){
                ((TileHandGenerator)tileEntity).OnActivated();
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileHandGenerator();
    }
}
