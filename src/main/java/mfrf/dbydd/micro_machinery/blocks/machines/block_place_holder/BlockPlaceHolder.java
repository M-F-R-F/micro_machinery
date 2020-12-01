package mfrf.dbydd.micro_machinery.blocks.machines.block_place_holder;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockPlaceHolder extends MMMultiBlockBase {

    public BlockPlaceHolder(String name) {
        super(Properties.create(Material.IRON).harvestLevel(-1).hardnessAndResistance(-1), name, true,  false, false);
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(IS_PLACEHOLDER, true));
    }

    public static CompoundNBT packageBlock(World world, BlockPos pos) {
        CompoundNBT packedNBT = new CompoundNBT();

        BlockState blockState = world.getBlockState(pos);
        packedNBT.put("block_state_nbt", NBTUtil.writeBlockState(blockState));

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            packedNBT.put("tile_packaged", tileEntity.write(new CompoundNBT()));
        }

        if (blockState.getBlock() == Blocks.LEVER) {
            world.setBlockState(pos, RegisteredBlocks.LEVER_PLACEHOLDER.getDefaultState());
        } else {
            world.setBlockState(pos, RegisteredBlocks.PLACE_HOLDER.getDefaultState());
        }
        world.getTileEntity(pos).read(packedNBT);
        return packedNBT;
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        ((TilePlaceHolder) te).onBlockHarvest(worldIn, pos, player, state, stack);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            return ((TilePlaceHolder) worldIn.getTileEntity(pos)).onBlockActivated(worldIn, player, handIn, hit);

        }
        return ActionResultType.SUCCESS;
    }

    public void breakBlock(CompoundNBT packedNBT, World world, BlockPos pos) {
        BlockState blockState = NBTUtil.readBlockState(packedNBT.getCompound("block_state_nbt"));
        world.setBlockState(pos, blockState);
        if (packedNBT.contains("tile_packaged")) {
            world.getTileEntity(pos).read(packedNBT.getCompound("tile_packaged"));
        }
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TilePlaceHolder();
    }
}
