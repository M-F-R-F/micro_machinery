package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MMBlockMultiBlockPart extends MMBlockTileProviderBase {

    public MMBlockMultiBlockPart(Properties properties, String name, boolean noItem) {
        super(properties, name, noItem);
    }

    @Nullable
    public BlockState pack(World world, BlockPos pos, Direction direction, BlockPos mainPart) {
        BlockState replace = defaultBlockState();

        CompoundTag reserved = new CompoundTag();

        CompoundTag blockNBT = NBTUtil.writeBlockState(world.getBlockState(pos));
        reserved.put("block", blockNBT);

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity != null) {
            reserved.put("tile", tileEntity.serializeNBT());
        }

        world.setBlockState(pos, replace);
        ((MMTileMultiBlockPart) world.getBlockEntity(pos)).setPacked(reserved, mainPart);
        return replace;
    }

    public static void unpack(World world, BlockPos pos) {
        MMTileMultiBlockPart thisTile = (MMTileMultiBlockPart) world.getBlockEntity(pos);
        CompoundTag packedNBT = thisTile.getPacked();
        BlockState block = NBTUtil.readBlockState(packedNBT.getCompound("block"));
        world.setBlockState(pos, block);
        if (packedNBT.contains("tile")) {
            world.getBlockEntity(pos).read(packedNBT.getCompound("tile"));
        }
    }


    @Override
    public boolean hasBlockEntity(BlockState state) {
        return true;
    }


    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, Player player) {
        if (!worldIn.isRemote()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            ((MMTileMultiBlockPart) te).onBlockHarvest(worldIn, pos, player, state);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            return ((MMTileMultiBlockPart) worldIn.getBlockEntity(pos)).onBlockActivated(worldIn, player, handIn, hit);

        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return worldIn.getMaxLightLevel();
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new MMTileMultiBlockPart();
    }
}
