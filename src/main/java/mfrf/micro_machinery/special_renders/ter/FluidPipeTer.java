package mfrf.micro_machinery.special_renders.ter;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.micro_machinery.block.machines.single_block_machines.fluidpipe.FluidPipeBlock;
import mfrf.micro_machinery.block.machines.single_block_machines.fluidpipe.FluidPipeTile;
import mfrf.micro_machinery.enums.EnumFluidPipeState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FluidPipeTer extends MMTERBase<FluidPipeTile>{
    public FluidPipeTer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(FluidPipeTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        FluidTank fluidTank = pBlockEntity.getFluidTank();
        BlockState blockState = pBlockEntity.getBlockState();
        List<Map.Entry<Direction, EnumProperty<EnumFluidPipeState>>> connected = FluidPipeBlock.DIRECTION_ENUM_PROPERTY_MAP.entrySet().stream().filter(e -> blockState.getValue(e.getValue()).judge).collect(Collectors.toList());

    }



}
