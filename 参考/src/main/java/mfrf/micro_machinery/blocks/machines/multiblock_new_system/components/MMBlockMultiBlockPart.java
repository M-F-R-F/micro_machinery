package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MMBlockMultiBlockPart extends MMBlockTileProviderBase {

    public MMBlockMultiBlockPart(Properties properties, String name, boolean noItem) {
        super(properties, name, noItem);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MMTileMultiBlockPart(pPos, pState);
    }

    @Override
    public @org.jetbrains.annotations.Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return null;
    }

    @Nullable
    public BlockState pack(Level world, BlockPos pos, Direction direction, BlockPos mainPart) {
        BlockState replace = defaultBlockState();

        CompoundTag reserved = new CompoundTag();

        CompoundTag blockNBT = NbtUtils.writeBlockState(world.getBlockState(pos));
        reserved.put("block", blockNBT);

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity != null) {
            reserved.put("tile", tileEntity.serializeNBT());
        }

        world.setBlockAndUpdate(pos, replace);
        ((MMTileMultiBlockPart) world.getBlockEntity(pos)).setPacked(reserved, mainPart);
        return replace;
    }

    public static void unpack(LevelAccessor world, BlockPos pos) {
        MMTileMultiBlockPart thisTile = (MMTileMultiBlockPart) world.getBlockEntity(pos);
        CompoundTag packedNBT = thisTile.getPacked();
        BlockState block = NbtUtils.m_129241_(packedNBT.getCompound("block"));
        world.setBlock(pos, block, 3);
        if (packedNBT.contains("tile")) {
            world.getBlockEntity(pos).load(packedNBT.getCompound("tile"));
        }
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        if (!worldIn.isClientSide()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            ((MMTileMultiBlockPart) te).onBlockHarvest(worldIn, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            return ((MMTileMultiBlockPart) worldIn.getBlockEntity(pos)).onBlockActivated(worldIn, player, handIn, hit);

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.empty();
    }

}
