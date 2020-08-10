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
        matrixStackIn.push();
        matrixStackIn.translate((float) direction.getDirectionVec().getX() / (float) 2, 0.5, (float) direction.getDirectionVec().getX() / (float) 2);
        if (!progress.atMinValue()) {
            matrixStackIn.rotate(new Quaternion(direction.toVector3f(), 360 * ((float) progress.getCurrent() / (float) progress.getMax()), true));
        //todo fix
        }

        blockRenderer.renderBlock(RegisteredBlocks.HAND_GENERATOR_1.getDefaultState().with(BlockHandGenerator_Handler.FACING, facing), matrixStackIn, bufferIn, 0x00F0_00F0, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();

    }

    private Vector3f move(float x, float z) {
        float tempX = 0;
        float tempZ = 0;
        if (x != 0) {
            if (x > 0) {
                tempZ = 0.5f;
            }
            if (z != 0) {
                tempX = 0.5f;
            }
        }
        return new Vector3f(tempX, 0.5f, tempZ);
    }
}
