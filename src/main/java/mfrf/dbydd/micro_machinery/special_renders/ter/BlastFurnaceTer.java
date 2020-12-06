package mfrf.dbydd.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace.TileBlastFurnace;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class BlastFurnaceTer extends MMTERBase<TileBlastFurnace>{
    public BlastFurnaceTer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileBlastFurnace tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
