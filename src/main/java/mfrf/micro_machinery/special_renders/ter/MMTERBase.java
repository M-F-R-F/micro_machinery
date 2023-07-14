package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import java.awt.*;

public abstract class MMTERBase<T extends BlockEntity> implements BlockEntityRenderer<T> {
    protected static BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
    protected static ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    protected static int MAX_LIGHT = 0x00F0_00F0;
    protected static int MIN_LIGHT = 0x00F0_0000;

    public MMTERBase(BlockEntityRendererProvider.Context pContext) {

    }

    @Override
    public abstract void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay);

    protected void drawLine(Matrix4f matrixPos, VertexConsumer renderBuffer, Color color, Vec3 startVertex, Vec3 endVertex) {
        renderBuffer.vertex(matrixPos, (float) startVertex.x(), (float) startVertex.y(), (float) startVertex.z())
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())   // there is also a version for floats (0 -> 1)
                .endVertex();
        renderBuffer.vertex(matrixPos, (float) endVertex.x(), (float) endVertex.y(), (float) endVertex.z())
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())   // there is also a version for floats (0 -> 1)
                .endVertex();
    }

    protected Vec3 getTrueDirectionOffsetVec(Direction direction) {
        switch (direction) {
            case UP: {
                return new Vec3(0, 1, 0);
            }
            case DOWN: {
                return new Vec3(0, -1, 0);
            }
            case EAST: {
                return new Vec3(1, 0, 0);
            }
            case WEST: {
                return new Vec3(-1, 0, 0);
            }
            case NORTH: {
                return new Vec3(0, 0, -1);
            }
            case SOUTH: {
                return new Vec3(0, 0, 1);
            }
        }
        return Vec3.ZERO;
    }

}
