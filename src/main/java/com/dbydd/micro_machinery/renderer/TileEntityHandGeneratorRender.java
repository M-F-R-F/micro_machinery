package com.dbydd.micro_machinery.renderer;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityHandGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntityHandGeneratorRender extends TileEntitySpecialRenderer<TileEntityHandGenerator> {

    @Override
    public void render(TileEntityHandGenerator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        EnumFacing facing = te.getBlockState().getValue(BlockHorizontal.FACING);
        float progress = (float) te.getProgress();
        BlockPos pos1 = te.getPos();
        BlockPos pos2 = pos1.offset(EnumFacing.fromAngle(facing.getHorizontalAngle() - 90));
        float X_Axis = pos2.getX() - pos1.getX();
        float Y_Axis = pos2.getY() - pos1.getY();
        float Z_Axis = pos2.getZ() - pos1.getZ();
        float rotation = (progress / (float) te.getHAND_GENERATOR_MAX_ROTATE_TIME()) * 360f;
        Vector3d vector = move(X_Axis, Z_Axis);

        GlStateManager.translate(x + vector.x, y + vector.y, z + vector.z);

        GlStateManager.disableRescaleNormal();

        GlStateManager.rotate(rotation, X_Axis, Y_Axis, Z_Axis);

        RenderHelper.disableStandardItemLighting();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }

        World world = te.getWorld();
        // Translate back to local view coordinates so that we can do the acual rendering here
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        IBlockState state = ModBlocks.HAND_GENERATOR_HANDLE.getDefaultState().withProperty(BlockHorizontal.FACING, facing);
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel model = dispatcher.getModelForState(state);
        dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
        tessellator.draw();

        RenderHelper.enableStandardItemLighting();

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

//    @Override
//    public void renderTileEntityFast(TileEntityHandGenerator te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
//
//        EnumFacing facing = te.getBlockState().getValue(BlockHorizontal.FACING);
//        float progress = (float) te.getProgress();
//        BlockPos pos1 = te.getPos();
//        BlockPos pos2 = pos1.offset(EnumFacing.fromAngle(facing.getHorizontalAngle() - 90));
//        float X_Axis = Math.abs(pos2.getX() - pos1.getX());
//        float Y_Axis = Math.abs(pos2.getY() - pos1.getY());
//        float Z_Axis = Math.abs(pos2.getZ() - pos1.getZ());
//        float rotation = (progress / (float) te.getHAND_GENERATOR_MAX_ROTATE_TIME()) * 360f;
//
//        IBlockState state = ModBlocks.HAND_GENERATOR_HANDLE.getDefaultState().withProperty(BlockHorizontal.FACING, facing);
//        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
//        IBakedModel model = dispatcher.getModelForState(state);
//
//        buffer.setTranslation(x - pos1.getX(), y - pos1.getY(), z - pos1.getZ());
//        GlStateManager.rotate(rotation, X_Axis, Y_Axis, Z_Axis);
//        GlStateManager.scale(10, 10, 10);
//        dispatcher.getBlockModelRenderer().renderModel(te.getWorld(), model, state, pos1, buffer, true);
//    }

    private Vector3d move(float x, float z) {
        Vector3d vector = new Vector3d();
        vector.x = 0;
        vector.y = 0.5;
        vector.z = 0.5;
        if (x != 0) {
            if (x > 0) {
                vector.z = 0.5;
            }
            if (z != 0) {
                vector.x = 0.5;
            }
            return vector;
        }
        return vector;
    }
}
