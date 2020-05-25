package com.dbydd.micro_machinery.renderer;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.blocks.machine.Cable_Heads;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;

public class TileentityCableSpecialRenderer<T extends TileEntityEnergyCableWithoutGenerateForce> extends FastTESR<T> {

    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

        //todo fixit
        IBlockState state = ((Cable_Heads) ModBlocks.CABLE_HEAD).InitState(te.getCableHeads(EnumMMFETileEntityStatus.CABLE_HEAD));
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel model = dispatcher.getModelForState(state);

        BlockPos pos = te.getPos();
        buffer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());
        dispatcher.getBlockModelRenderer().renderModel(te.getWorld(), model, state, pos, buffer, true);

    }

}
