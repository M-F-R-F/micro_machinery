package mfrf.micro_machinery.special_renders.ter;//package mfrf.micro_machinery.special_renders.ter;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import mfrf.micro_machinery.blocks.machines.single_block_machines.etcher.BlockEtcher;
//import mfrf.micro_machinery.blocks.machines.single_block_machines.etcher.TileEtcher;
//import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
//import mfrf.micro_machinery.utils.IntegerContainer;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.LightTexture;
//import net.minecraft.client.renderer.Quaternion;
//import net.minecraft.client.renderer.Vector3f;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.core.Direction;
//import net.minecraft.core.Vec3i;
//import net.minecraft.world.LightType;
//import net.minecraft.world.lighting.WorldLightManager;
//import net.minecraftforge.client.model.data.EmptyModelData;
//
//public class EtcherTer extends MMTERBase<TileEtcher> {
//
//    public EtcherTer(BlockEntityRendererDispatcher rendererDispatcherIn) {
//        super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(TileEtcher tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//        TileEtcher.State state = tileEntityIn.getState();
//        IntegerContainer plugProgress = tileEntityIn.getPlugProgress();
//        WorldLightManager lightEngine = tileEntityIn.getWorld().getLightManager();
//        int light = LightTexture.packLight(lightEngine.getLightEngine(LightType.BLOCK).getLightFor(tileEntityIn.getPos()), lightEngine.getLightEngine(LightType.SKY).getLightFor(tileEntityIn.getPos().above()));
//
//        double scale = calcScale(state, plugProgress);
//        Direction direction = tileEntityIn.getBlockState().get(BlockEtcher.FACING);
//        ItemStack currentItemStackInSlot = tileEntityIn.getSlot().getStackInSlot(0);
//        Vec3i directionVec = direction.getDirectionVec();
//        double x = directionVec.getX() * scale;
//        double y = directionVec.getY() * scale;
//        double z = directionVec.getZ() * scale;
//        //====================================================================================================================================================================================================//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(x, y, z);
//        matrixStackIn.translate(0.5, 0, 0.5);
//        matrixStackIn.rotate(new Quaternion(0, direction == Direction.SOUTH || direction == Direction.NORTH ? direction.getHorizontalAngle() - 90 : direction.getHorizontalAngle() + 90, 0, true));
//        blockRenderer.renderBlock(RegisteredBlocks.ETCHER_1.defaultBlockState(), matrixStackIn, bufferIn, light, combinedOverlayIn, EmptyModelData.INSTANCE);
//        matrixStackIn.popPose();
//        //====================================================================================================================================================================================================//
//        //====================================================================================================================================================================================================//
//        matrixStackIn.pushPose();
//        matrixStackIn.translate(0.5, 0, 0.5);
//        matrixStackIn.translate(x + directionVec.getX() * 0.1, 0.65, z + directionVec.getZ() * 0.1);
//        matrixStackIn.scale(0.5f, 0.3f, 0.5f);
//        matrixStackIn.rotate(new Quaternion(new Vector3f(directionVec.getX(), directionVec.getY(), directionVec.getZ()), 90, true));
//        matrixStackIn.rotate(new Quaternion(0, direction == Direction.SOUTH || direction == Direction.NORTH ? direction.getHorizontalAngle() - 90 : direction.getHorizontalAngle() + 90, 0, true));
//        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(currentItemStackInSlot, tileEntityIn.getWorld(), null);
//        itemRenderer.renderItem(currentItemStackInSlot, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, light, combinedOverlayIn, ibakedmodel);
//        matrixStackIn.popPose();
//        //====================================================================================================================================================================================================//
//
//    }
//
//    private double calcScale(TileEtcher.State state, IntegerContainer container) {
//        double scale = 0.7;
//        switch (state) {
//            case EJECTING: {
//                return scale * (double) container.getCurrent() / (double) container.getMax();
//            }
//            case PLUGGING: {
//                return scale - (0.7 * ((double) container.getCurrent() / (double) container.getMax()));
//            }
//            case WORKING:
//            case SEARCHING:
//                return 0;
//        }
//        return scale;
//    }
//}
