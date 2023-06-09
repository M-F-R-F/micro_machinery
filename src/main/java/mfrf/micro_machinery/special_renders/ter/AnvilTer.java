package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.micro_machinery.blocks.machines.single_block_machines.forge_anvil.TileAnvil;
import mfrf.micro_machinery.utils.IntegerContainer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AnvilTer extends MMTERBase<TileAnvil> {

    public AnvilTer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(TileAnvil tileEntityIn, float pPartialTick, PoseStack matrixStackIn, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemStack itemStack = tileEntityIn.getItemStackHandler().getStackInSlot(0);
        if (!itemStack.isEmpty()) {
            matrixStackIn.pushPose();
            BlockState blockState = tileEntityIn.getBlockState();
            IntegerContainer forgeTime = tileEntityIn.getForgeTime();
            Direction direction = blockState.getValue(MMBlockTileProviderBase.FACING);
            VoxelShape collisionShape = blockState.getCollisionShape(tileEntityIn.getLevel(), tileEntityIn.getBlockPos());
            double maxY = collisionShape.bounds().maxY;
            matrixStackIn.translate(0.5, maxY+0.01, 0.5);
            matrixStackIn.m_85845_(new Quaternion(new Vector3f(0, 1, 0), direction.get2DDataValue(), true));
            matrixStackIn.m_85845_(new Quaternion(new Vector3f(1,0,0), 90,true));
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            matrixStackIn.scale(1,1,forgeTime.getCurrent() == 0?1:1-((float) forgeTime.getCurrent() / (float) forgeTime.getMax()));
            BakedModel ibakedmodel = itemRenderer.getModel(itemStack, tileEntityIn.getLevel(), null, 1);//todo last argument called "speed", should try to figure out means
            itemRenderer.render(itemStack, ItemTransforms.TransformType.FIXED, true, matrixStackIn, pBufferSource, pPackedLight, pPackedOverlay, ibakedmodel);
            matrixStackIn.popPose();
        }

    }
}
