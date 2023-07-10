package mfrf.micro_machinery.special_renders.ter;//package mfrf.micro_machinery.special_renders.ter;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.vertex.PoseStack;
//import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockMainPartBase;
//import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.blast_furnace.TileBlastFurnace;
//import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.Quaternion;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
//import net.minecraft.core.Direction;
//import net.minecraftforge.client.model.data.EmptyModelData;
//
//public class BlastFurnaceTer extends MMTERBase<TileBlastFurnace> {
//
//    public BlastFurnaceTer(BlockEntityRendererProvider.Context pContext) {
//        super(pContext);
//    }
//
//    @Override
//    public void render(TileBlastFurnace blastFurnace, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//        float scaleValue = blastFurnace.isWorking() ? (float) Math.abs(Math.cos(blastFurnace.getWorld().getGameTime() / 8.0f)) / 2.0f + 0.5f : 1;
//        Direction facingDirection = blastFurnace.getFacingDirection();
//
////==========================left================================================
//        matrixStackIn.pushPose();
//
//        if (facingDirection == Direction.NORTH) {
//            matrixStackIn.translate(-scaleValue - 0.1, -1, 1);
//            matrixStackIn.scale(scaleValue, 1, 1);
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_RIGHT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.WEST) {
//            matrixStackIn.translate(2, -1, 1.1);//平移
//            matrixStackIn.scale(1, 1, scaleValue);//缩放
//            matrixStackIn.rotate(new Quaternion(0, 270, 0, true));//旋转
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.SOUTH) {
//            matrixStackIn.translate(-scaleValue - 0.1, -1, -1);
//            matrixStackIn.scale(scaleValue, 1, 1);
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_RIGHT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.EAST) {
//            matrixStackIn.translate(-1, -1, -0.1);
//            matrixStackIn.scale(1, 1, scaleValue);
//            matrixStackIn.rotate(new Quaternion(0, 90, 0, true));
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        }
//
//        matrixStackIn.popPose();
////==========================================================================
//        matrixStackIn.pushPose();
//
//        if (facingDirection == Direction.NORTH) {
//            matrixStackIn.translate(1.1, -1, 1);
//            matrixStackIn.scale(scaleValue, 1, 1);
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.WEST) {
//            matrixStackIn.scale(1, 1, scaleValue);
//            matrixStackIn.rotate(new Quaternion(0, 90, 0, true));
//            matrixStackIn.translate(0.1, -1, 1);
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.SOUTH) {
//            matrixStackIn.translate(1.1, -1, -1);
//            matrixStackIn.scale(scaleValue, 1, 1);
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        } else if (facingDirection == Direction.EAST) {
//            matrixStackIn.translate(0, -1, 1.1);//平移
//            matrixStackIn.scale(1, 1, scaleValue);//缩放
//            matrixStackIn.rotate(new Quaternion(0, 270, 0, true));//旋转
//            blockRenderer.renderBlock(RegisteredBlocks.BELLOW_LEFT.defaultBlockState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
//        }
//
//        matrixStackIn.popPose();
////==========================right================================================
//
////==========================model================================================
//
//        matrixStackIn.pushPose();
//        blockRenderer.renderBlock(blastFurnace.getBlockState().setValue(MMMultiBlockMainPartBase.MODEL_PLACEHOLDER, true), matrixStackIn, bufferIn, combinedLightIn - 1, combinedOverlayIn, EmptyModelData.INSTANCE);
//        matrixStackIn.popPose();
//
////==========================model================================================
//
//
//    }
//
//    @Override
//    public boolean isGlobalRenderer(TileBlastFurnace te) {
//        return true;
//    }
//
//    @Override
//    public void render(TileBlastFurnace pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
//
//    }
//}
