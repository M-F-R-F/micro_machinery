package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.material.Material;
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

public class BlockPlaceHolder extends MMMultiBlockHolderBase {

    public BlockPlaceHolder(String name) {
        super(Properties.create(Material.IRON).harvestLevel(-1).hardnessAndResistance(-1), name, true, false, false);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.SOUTH).setValue(IS_PLACEHOLDER, true));
    }

    public BlockPlaceHolder(Properties properties, String name, boolean addToPlaceHolderList, boolean haveBlockItem, boolean addToStructureMaps) {
        super(properties, name, addToPlaceHolderList, haveBlockItem, addToStructureMaps);
    }

    public static BlockPos packageBlock(World world, BlockPos pos, BlockPos mainPart) {
        CompoundTag packedNBT = new CompoundTag();

        BlockState blockState = world.getBlockState(pos);
        packedNBT.put("block_state_nbt", NBTUtil.writeBlockState(blockState));

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity != null) {
            packedNBT.put("tile_packaged", tileEntity.write(new CompoundTag()));
        }

        if (blockState.getBlock() == Blocks.LEVER) {
            world.setBlockState(pos, RegisteredBlocks.LEVER_PLACEHOLDER.getDefaultState());
        } else {
            world.setBlockState(pos, RegisteredBlocks.PLACE_HOLDER.getDefaultState());
        }

        TilePlaceHolder placeHolder = (TilePlaceHolder) world.getBlockEntity(pos);
        placeHolder.setPackedNBT(packedNBT);
        placeHolder.setMainPartPos(mainPart);
        return pos;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, Player player) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        ((TilePlaceHolder) te).onBlockHarvest(worldIn, pos, player, state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, Player player,
            Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            return ((TilePlaceHolder) worldIn.getBlockEntity(pos)).onBlockActivated(worldIn, player, handIn, hit);

        }
        return ActionResultType.SUCCESS;
    }

    public void breakBlock(CompoundTag packedNBT, World world, BlockPos pos) {
        BlockState blockState = NBTUtil.readBlockState(packedNBT.getCompound("block_state_nbt"));
        world.setBlockState(pos, blockState);
        if (packedNBT.contains("tile_packaged")) {
            world.getBlockEntity(pos).read(packedNBT.getCompound("tile_packaged"));
        }
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return worldIn.getMaxLightLevel();
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f;
    }


    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        return new TilePlaceHolder();
    }
}
