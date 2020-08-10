package com.dbydd.micro_machinery.special_renders.ter;

import com.dbydd.micro_machinery.blocks.mathines.hand_generator.BlockHandGenerator;
import com.dbydd.micro_machinery.blocks.mathines.hand_generator.BlockHandGenerator_Handler;
import com.dbydd.micro_machinery.blocks.mathines.hand_generator.TileHandGenerator;
import com.dbydd.micro_machinery.registery_lists.RegisteredBlocks;
import com.dbydd.micro_machinery.utils.IntegerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.model.data.EmptyModelData;

public class HandGeneratorTer extends MMTERBase<TileHandGenerator> {
    public HandGeneratorTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileHandGenerator tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction facing = tileEntityIn.getBlockState().get(BlockHandGenerator.FACING);
        Direction direction = Direction.fromAngle(facing.getHorizontalAngle() + 90);
        IntegerContainer progress = tileEntityIn.getProgress();
        Vector3f vector3f = direction.toVector3f();
        Vector3f move = move(vector3f);
        matrixStackIn.push();
        matrixStackIn.translate(move.getX(), move.getY(), move.getZ());
        matrixStackIn.rotate(new Quaternion(vector3f, 360 * ((float) progress.getCurrent() / (float) progress.getMax()), true));
        blockRenderer.renderBlock(RegisteredBlocks.HAND_GENERATOR_1.getDefaultState().with(BlockHandGenerator_Handler.FACING, facing), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();

    }

    private Vector3f move(Vector3f vector3f) {
        Vector3f copy = vector3f.copy();
        if (vector3f.getX() > 0) {
            return new Vector3f(0, 0.5f, copy.getX() / 2.0f);
        } else if (vector3f.getX() < 0) {
            return new Vector3f(0, 0.5f, -copy.getX() / 2.0f);
        }
        if (vector3f.getZ() > 0) {
            return new Vector3f(copy.getZ() / 2.0f, 0.5f, 0);
        } else if (vector3f.getZ() < 0) {
            return new Vector3f(-copy.getZ() / 2.0f, 0.5f, 0);
        }
        return vector3f;
    }
}
