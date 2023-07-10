package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.EmptyModel;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class HandGeneratorTer extends MMTERBase<TileHandGenerator> {

    public HandGeneratorTer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    private Vector3f move(Direction direction) {
        Vector3f vector3f = direction.getOpposite().step();
        if (vector3f.x() > 0) {
            return new Vector3f(-1, 0.5f, 0.5f);
        } else if (vector3f.x() < 0) {
            return new Vector3f(0, 0.5f, -0.5f);
        }
        if (vector3f.z() > 0) {
            return new Vector3f(0, 0.5f, 0.5f);
        } else if (vector3f.z() < 0) {
            return new Vector3f(-1, 0.5f, -0.5f);
        }
        return vector3f;
    }

    @Override
    public void render(TileHandGenerator tileEntityIn, float pPartialTick, PoseStack matrixStackIn, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        matrixStackIn.pushPose();
        Direction direction = tileEntityIn.getBlockState().getValue(BlockHandGenerator.FACING);
        Vector3f move = move(direction);
        IntegerContainer progress = tileEntityIn.getProgress();
        matrixStackIn.mulPose(new Quaternionf(new AxisAngle4d(-direction.getOpposite().toYRot(), new Vector3f(0, 1, 0))));
        matrixStackIn.translate(move.x(), move.y(), move.z());
        matrixStackIn.mulPose(new Quaternionf(new AxisAngle4d(360 * ((float) progress.getCurrent() / (float) progress.getMax()), new Vector3f(1, 0, 0))));
        blockRenderer.renderSingleBlock(MMBlocks.HAND_GENERATOR_1.get().defaultBlockState(), matrixStackIn, pBufferSource, pPackedLight, pPackedOverlay);
        matrixStackIn.popPose();
    }
}
