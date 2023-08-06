package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.block.MMDirectionalBlock;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.micro_machinery.block.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.micro_machinery.registry_lists.MMBlocks;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class HandGeneratorTer extends MMTERBase<TileHandGenerator> {

    public HandGeneratorTer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    //no east/south
    @Override
    public void render(TileHandGenerator tileEntityIn, float pPartialTick, PoseStack matrixStackIn, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Direction direction = tileEntityIn.getBlockState().getValue(BlockHandGenerator.FACING);
        IntegerContainer progress = tileEntityIn.getProgress();
        boolean flip = direction == Direction.EAST || direction == Direction.SOUTH;

        Direction counterClockWise1 = direction.getCounterClockWise();
        Vec3i normI = counterClockWise1.getNormal();
        Vector3f normal = new Vector3f(normI.getX(), normI.getY(), normI.getZ());
        Vec3i normal1 = counterClockWise1.getCounterClockWise().getNormal();
        Vector3f mov = new Vector3f(normal1.getX(), normal1.getY() + 1, normal1.getZ()).mul(.5f);


        matrixStackIn.pushPose();
        if (flip) {
            BlockState blockState = MMBlocks.HAND_GENERATOR_1.get().defaultBlockState().setValue(MMDirectionalBlock.FACING, Direction.from2DDataValue(direction.get2DDataValue() + 2));
            matrixStackIn.scale(-1,1,-1);
            matrixStackIn.translate(mov.x()-normal.x, mov.y(), mov.z()+normal.z);
            matrixStackIn.mulPose(new Quaternionf(new AxisAngle4d(2 * Math.PI * ((float) progress.getCurrent() / (float) progress.getMax()), normal)));
            blockRenderer.renderSingleBlock(blockState, matrixStackIn, pBufferSource, pPackedLight, pPackedOverlay);
        } else {
            BlockState blockState = MMBlocks.HAND_GENERATOR_1.get().defaultBlockState().setValue(MMDirectionalBlock.FACING, direction);
            matrixStackIn.translate(mov.x(), mov.y(), mov.z());
            matrixStackIn.mulPose(new Quaternionf(new AxisAngle4d(-2 * Math.PI * ((float) progress.getCurrent() / (float) progress.getMax()), normal)));
            blockRenderer.renderSingleBlock(blockState, matrixStackIn, pBufferSource, pPackedLight, pPackedOverlay);
        }
        matrixStackIn.popPose();
    }
}
