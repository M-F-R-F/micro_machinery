package mfrf.dbydd.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace.TileBlastFurnace;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.EmptyModelData;

public class BlastFurnaceTer extends MMTERBase<TileBlastFurnace> {
    public BlastFurnaceTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileBlastFurnace blastFurnace, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float scaleValue = blastFurnace.isWorking() ?(float) Math.abs(Math.cos(blastFurnace.getWorld().getGameTime() / 8.0f)) / 2.0f + 0.5f : 1;
//        if (blastFurnace.isWorking()) {
        Direction facingDirection = blastFurnace.getFacingDirection();

//==========================left================================================
        matrixStackIn.push();

        if (facingDirection == Direction.NORTH) {
            matrixStackIn.translate(-scaleValue - 0.1, -1, 1);
            matrixStackIn.scale(scaleValue, 1, 1);
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_RIGHT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.WEST) {
            matrixStackIn.translate(2, -1, 1.1);//平移
            matrixStackIn.scale(1, 1, scaleValue);//缩放
            matrixStackIn.rotate(new Quaternion(0, 270, 0, true));//旋转
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.SOUTH) {
            matrixStackIn.translate(-scaleValue - 0.1, -1, -1);
            matrixStackIn.scale(scaleValue, 1, 1);
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_RIGHT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.EAST) {
            matrixStackIn.translate(-1, -1, -0.1);
            matrixStackIn.scale(1, 1, scaleValue);
            matrixStackIn.rotate(new Quaternion(0, 90, 0, true));
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        }

        matrixStackIn.pop();
//==========================================================================
        matrixStackIn.push();

        if (facingDirection == Direction.NORTH) {
            matrixStackIn.translate(1.1, -1, 1);
            matrixStackIn.scale(scaleValue, 1, 1);
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.WEST) {
            matrixStackIn.scale(1, 1, scaleValue);
            matrixStackIn.rotate(new Quaternion(0, 90, 0, true));
            matrixStackIn.translate(0.1, -1, 1);
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.SOUTH) {
            matrixStackIn.translate(1.1, -1, -1);
            matrixStackIn.scale(scaleValue, 1, 1);
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        } else if (facingDirection == Direction.EAST) {
            matrixStackIn.translate(0, -1, 1.1);//平移
            matrixStackIn.scale(1, 1, scaleValue);//缩放
            matrixStackIn.rotate(new Quaternion(0, 270, 0, true));//旋转
            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        }

        matrixStackIn.pop();
//==========================right================================================

//        }
    }

    @Override
    public boolean isGlobalRenderer(TileBlastFurnace te) {
        return true;
    }

}
