package mfrf.micro_machinery.special_renders.ter;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.forge_anvil.TileAnvil;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AnvilTer extends MMTERBase<TileAnvil> {

    public AnvilTer(BlockEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileAnvil tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack itemStack = tileEntityIn.getItemStackHandler().getStackInSlot(0);
        if (!itemStack.isEmpty()) {
            matrixStackIn.push();
            BlockState blockState = tileEntityIn.getBlockState();
            IntegerContainer forgeTime = tileEntityIn.getForgeTime();
            Direction direction = blockState.get(MMBlockTileProviderBase.FACING);
            VoxelShape collisionShape = blockState.getCollisionShape(tileEntityIn.getWorld(), tileEntityIn.getPos());
            double maxY = collisionShape.getBoundingBox().maxY;
            matrixStackIn.translate(0.5, maxY+0.01, 0.5);
            matrixStackIn.rotate(new Quaternion(new Vector3f(0, 1, 0), direction.getHorizontalAngle(), true));
            matrixStackIn.rotate(new Quaternion(new Vector3f(1,0,0), 90,true));
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            matrixStackIn.scale(1,1,forgeTime.getCurrent() == 0?1:1-((float) forgeTime.getCurrent() / (float) forgeTime.getMax()));
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(itemStack, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        }
    }
}
