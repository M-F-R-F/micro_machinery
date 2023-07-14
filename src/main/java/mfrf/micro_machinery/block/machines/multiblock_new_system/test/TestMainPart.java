//package mfrf.micro_machinery.block.machines.multiblock_new_system.test;
//
//import mfrf.micro_machinery.block.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.SoundType;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//
//public class TestMainPart extends MMBlockMainPartBase {
//    public TestMainPart() {
//        super(Properties.of().sound(SoundType.STONE), "test", true, "testS");
//    }
//
//    @Override
//    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new TestTileMainPart(pPos, pState);
//    }
//
//    @Override
//    public @org.jetbrains.annotations.Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
//        return null;
//    }
//}
