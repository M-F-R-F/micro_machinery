package mfrf.dbydd.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import mfrf.dbydd.micro_machinery.blocks.machines.etcher.BlockEtcher;
import mfrf.dbydd.micro_machinery.blocks.machines.etcher.TileEtcher;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.model.data.EmptyModelData;

public class EtcherTer extends MMTERBase<TileEtcher> {

    public EtcherTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEtcher tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        TileEtcher.State state = tileEntityIn.getState();
        double scale = 0;
        IntegerContainer plugProgress = tileEntityIn.getPlugProgress();
        Direction direction = tileEntityIn.getBlockState().get(BlockEtcher.FACING);
        switch (state) {
            case EJECTING:
                scale = (double) plugProgress.getCurrent() / (double) plugProgress.getMax() + 0.05;
                break;
            case PLUGGING:
                scale = 1.1 - ((double) plugProgress.getCurrent() / (double) plugProgress.getMax() + 0.05);
                break;
            case WORKING:
            case SEARCHING:
                scale = 0.05;
            case WAITING:
            case FINISHED:
                scale = 1.05;
                break;
        }
        Vec3i directionVec = direction.getDirectionVec();
        ItemStack currentItemStackInSlot = tileEntityIn.getCurrentItemStackInSlot();
        double translateX = directionVec.getX() * scale;
        double translateY = directionVec.getY() * scale;
        double translateZ = directionVec.getZ() * scale;
        matrixStackIn.push();
        matrixStackIn.rotate(new Quaternion(0, 90 * (direction.getOpposite().getHorizontalIndex() - 1), 0, true));
        matrixStackIn.translate(translateX, translateY, translateZ);

        blockRenderer.renderBlock(RegisteredBlocks.ETCHER_1.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();
        matrixStackIn.push();
        matrixStackIn.rotate(new Quaternion(0, 90 * (direction.getOpposite().getHorizontalIndex() - 1), 0, true));
        matrixStackIn.translate(translateX, translateY, translateZ + 1.5);

        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(currentItemStackInSlot, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(currentItemStackInSlot, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();
        //todo fix it
    }
}
