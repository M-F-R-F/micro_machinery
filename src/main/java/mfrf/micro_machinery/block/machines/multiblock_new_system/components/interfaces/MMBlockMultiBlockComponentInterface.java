package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces;

import mfrf.micro_machinery.block.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class MMBlockMultiBlockComponentInterface extends MMBlockTileProviderBase {
    public static BooleanProperty CONSTRUCTED = BooleanProperty.create("constructed");
    public static Properties DEFAULT_PROPERTIES = BlockBehaviour.Properties.of().sound(SoundType.ANVIL).noOcclusion().dynamicShape().strength(3.0f);

    public MMBlockMultiBlockComponentInterface(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateToRegistry());
    }

    public MMBlockMultiBlockComponentInterface() {
        super(DEFAULT_PROPERTIES);
        this.registerDefaultState(getStateToRegistry());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(CONSTRUCTED);
    }

    @Override
    protected BlockState getStateToRegistry() {
        return super.getStateToRegistry().setValue(CONSTRUCTED, false);
    }

    public void link(BlockPos mainPart, Level accessor, Vec3i key, BlockPos current) {
        accessor.setBlockAndUpdate(current, accessor.getBlockState(current).setValue(CONSTRUCTED, true));
        linkTo(mainPart, accessor, current, key);
        //todo 天顶星科技准备开始考古
    }

    public void unLink(Level accessor, BlockPos current) {
        accessor.setBlockAndUpdate(current, accessor.getBlockState(current).setValue(CONSTRUCTED, false));
        MMTileMultiBlockComponentInterface blockEntity = (MMTileMultiBlockComponentInterface) accessor.getBlockEntity(current);
        blockEntity.unLink();
    }


    protected void linkTo(BlockPos mainPart, Level accessor, BlockPos currentPos, Vec3i key) {
        MMTileMultiBlockComponentInterface tileEntity = (MMTileMultiBlockComponentInterface) accessor.getBlockEntity(currentPos);
        tileEntity.linkTo(mainPart, accessor, key);
    }
}
