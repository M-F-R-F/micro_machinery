package com.dbydd.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public abstract class MMTERBase<T extends TileEntity> extends TileEntityRenderer<T> {
    protected static BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
    protected static ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public MMTERBase(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public abstract void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn);

}
