package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.micro_machinery.blocks.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.data.EmptyModelData;

public class HandGeneratorTer extends MMTERBase<TileHandGenerator> {

    public HandGeneratorTer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    private Vector3f move(Direction direction) {
        Vector3f vector3f = direction.getOpposite().m_122432_();
        if (vector3f.m_122239_() > 0) {
            return new Vector3f(-1, 0.5f, 0.5f);
        } else if (vector3f.m_122239_() < 0) {
            return new Vector3f(0, 0.5f, -0.5f);
        }
        if (vector3f.m_122269_() > 0) {
            return new Vector3f(0, 0.5f, 0.5f);
        } else if (vector3f.m_122269_() < 0) {
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
        matrixStackIn.m_85845_(new Quaternion(new Vector3f(0, 1, 0), -direction.getOpposite().toYRot(), true));
        matrixStackIn.translate(move.m_122239_(), move.m_122260_(), move.m_122269_());
        matrixStackIn.m_85845_(new Quaternion(new Vector3f(1, 0, 0), 360 * ((float) progress.getCurrent() / (float) progress.getMax()), true));
        blockRenderer.renderSingleBlock(RegisteredBlocks.HAND_GENERATOR_1.defaultBlockState(), matrixStackIn, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        matrixStackIn.popPose();
    }
}
