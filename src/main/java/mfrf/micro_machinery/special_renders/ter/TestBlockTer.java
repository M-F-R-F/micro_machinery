//package mfrf.micro_machinery.special_renders.ter;
//
//import mfrf.micro_machinery.blocks.machines.ter_test.TerTestTile;
//import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockRendererDispatcher;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.tileentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
//import net.minecraftforge.client.model.data.EmptyModelData;
//
//public class TestBlockTer extends BlockEntityRenderer<TerTestTile> {
//    public TestBlockTer(BlockEntityRendererDispatcher rendererDispatcherIn) {
//        super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(TerTestTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(1, 0, 0);
//        blockRenderer.renderBlock(RegisteredBlocks.PUMP_1.defaultBlockState(), matrixStackIn,bufferIn , 0x00F0_00F0, combinedOverlayIn,EmptyModelData.INSTANCE);
//        matrixStackIn.popPose();
//    }
//}
