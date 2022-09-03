package mfrf.dbydd.micro_machinery.special_renders.ter;

import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator.BlockHandGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.hand_generator.TileHandGenerator;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.EmptyModelData;

public class HandGeneratorTer extends MMTERBase<TileHandGenerator> {
    public HandGeneratorTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileHandGenerator tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        Direction direction = tileEntityIn.getBlockState().get(BlockHandGenerator.FACING);
        Vector3f move = move(direction);
        IntegerContainer progress = tileEntityIn.getProgress();
        matrixStackIn.rotate(new Quaternion(new Vector3f(0,1,0), -direction.getOpposite().getHorizontalAngle(), true));
        matrixStackIn.translate(move.getX(), move.getY(), move.getZ());
        matrixStackIn.rotate(new Quaternion(new Vector3f(1,0,0), 360*((float)progress.getCurrent()/(float)progress.getMax()), true));
        blockRenderer.renderBlock(RegisteredBlocks.HAND_GENERATOR_1.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();

    }

    private Vector3f move(Direction direction) {
        Vector3f vector3f = direction.getOpposite().toVector3f();
        if (vector3f.getX() > 0) {
            return new Vector3f(-1, 0.5f, 0.5f);
        } else if (vector3f.getX() < 0) {
            return new Vector3f(0, 0.5f, -0.5f);
        }
        if (vector3f.getZ() > 0) {
            return new Vector3f(0, 0.5f, 0.5f);
        } else if (vector3f.getZ() < 0) {
            return new Vector3f(-1, 0.5f, -0.5f);
        }
        return vector3f;
    }
}
