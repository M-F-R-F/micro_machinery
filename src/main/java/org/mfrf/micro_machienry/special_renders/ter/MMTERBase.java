package org.mfrf.micro_machienry.special_renders.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public abstract class MMTERBase<T extends TileEntity> extends TileEntityRenderer<T> {
    protected static BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
    protected static ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    protected static int MAX_LIGHT = 0x00F0_00F0;
    protected static int MIN_LIGHT = 0x00F0_0000;

    public MMTERBase(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public abstract void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn);

    protected void drawLine(Matrix4f matrixPos, IVertexBuilder renderBuffer, Color color, Vec3d startVertex, Vec3d endVertex) {
        renderBuffer.pos(matrixPos, (float) startVertex.getX(), (float) startVertex.getY(), (float) startVertex.getZ())
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())   // there is also a version for floats (0 -> 1)
                .endVertex();
        renderBuffer.pos(matrixPos, (float) endVertex.getX(), (float) endVertex.getY(), (float) endVertex.getZ())
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())   // there is also a version for floats (0 -> 1)
                .endVertex();
    }

    protected Vec3d getTrueDirectionOffsetVec(Direction direction) {
        switch (direction) {
            case UP: {
                return new Vec3d(0, 1, 0);
            }
            case DOWN: {
                return new Vec3d(0, -1, 0);
            }
            case EAST: {
                return new Vec3d(1, 0, 0);
            }
            case WEST: {
                return new Vec3d(-1, 0, 0);
            }
            case NORTH: {
                return new Vec3d(0, 0, -1);
            }
            case SOUTH: {
                return new Vec3d(0, 0, 1);
            }
        }
        return Vec3d.ZERO;
    }

}
