package com.dbydd.micro_machinery.renderer;

import com.dbydd.micro_machinery.blocks.tileentities.TESRTestTile;
import com.dbydd.micro_machinery.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import org.lwjgl.opengl.GL11;

public class FastTesrRender extends FastTESR<TESRTestTile> {//在这个<>里填上要绑定的tileentity，然后去RegisteryHandler绑定


    @Override
    public void renderTileEntityFast(TESRTestTile te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        //在这里写渲染操作

        IBlockState state = ModBlocks.ORECOPPER.getDefaultState();//获取到blockstate

        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();//渲染器
        IBakedModel model = dispatcher.getModelForState(state);//通过渲染器获得模型

        BlockPos pos = te.getPos();//获得方块位置
        buffer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());//buffer的平移，看不懂坐标系
//        GlStateManager.rotate();//旋转
//        GlStateManager.translate();//平移
        dispatcher.getBlockModelRenderer().renderModel(te.getWorld(), model, state, pos, buffer, true);//进行渲染
    }



}
