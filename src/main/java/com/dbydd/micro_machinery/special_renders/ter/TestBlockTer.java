package com.dbydd.micro_machinery.special_renders.ter;

import com.dbydd.micro_machinery.blocks.mathines.ter_test.TerTestTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.EmptyModelData;

public class TestBlockTer extends TileEntityRenderer<TerTestTile> {
    public TestBlockTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TerTestTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        matrixStackIn.push();
        matrixStackIn.translate(1, 0, 0);
        blockRenderer.renderBlock(Blocks.LANTERN.getDefaultState(), matrixStackIn, bufferIn, 0x00F000F0, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();
    }
}
