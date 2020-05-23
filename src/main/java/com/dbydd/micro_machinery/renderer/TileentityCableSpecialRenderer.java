package com.dbydd.micro_machinery.renderer;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityEnergyCableWithoutGenerateForce;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.FastTESR;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class TileentityCableSpecialRenderer extends FastTESR<TileEntityEnergyCableWithoutGenerateForce> {
    @Override
    public void renderTileEntityFast(TileEntityEnergyCableWithoutGenerateForce te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        // Translate to the location of our tile entity
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        //todo
        RenderHead(te);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void RenderHead(TileEntityEnergyCableWithoutGenerateForce te) {
        SurrondingsState State = te.getStates();
        List<EnumFacing> facings = State.getNotNullFacings();

        for (EnumFacing facing : facings) {
            GlStateManager.pushMatrix();

            GlStateManager.rotate(facing.getHorizontalAngle(), 0,1,0);

//            this.bindTexture("");
            //todo
            if (Minecraft.isAmbientOcclusionEnabled()) {
                GlStateManager.shadeModel(GL11.GL_SMOOTH);
            } else {
                GlStateManager.shadeModel(GL11.GL_FLAT);
            }

            World world = te.getWorld();
            GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

            IBlockState state = ModBlocks.CABLE_HEAD.getDefaultState();
            BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            IBakedModel model = dispatcher.getModelForState(state);
            dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), builder, true);
            tessellator.draw();

            GlStateManager.popMatrix();
        }
    }


}
