package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.dbydd.micro_machinery.blocks.machines.MMBlockTileProviderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMBlockMainPartBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTTypes;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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
        BlockState replace = getDefaultState();

        CompoundNBT reserved = new CompoundNBT();

        CompoundNBT blockNBT = NBTUtil.writeBlockState(world.getBlockState(pos));
        reserved.put("block", blockNBT);

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            reserved.put("tile", tileEntity.serializeNBT());
        }

        world.setBlockState(pos, replace);
        ((MMTileMultiBlockPart) world.getTileEntity(pos)).setPacked(reserved, mainPart);
        return replace;
    }

    public void unpack(CompoundNBT packedNBT, World world, BlockPos pos) {
        ((MMTileMultiBlockPart) world.getTileEntity(pos)).onUnpack();
        BlockState block = NBTUtil.readBlockState(packedNBT.getCompound("block"));
        world.setBlockState(pos, block);
        if (packedNBT.contains("tile")) {
            world.getTileEntity(pos).read(packedNBT.getCompound("tile"));
        }
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity te = worldIn.getTileEntity(pos);
        ((MMTileMultiBlockPart) te).onBlockHarvest(worldIn, pos, player, state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            return ((MMTileMultiBlockPart) worldIn.getTileEntity(pos)).onBlockActivated(worldIn, player, handIn, hit);

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
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MMTileMultiBlockPart();
    }
}
