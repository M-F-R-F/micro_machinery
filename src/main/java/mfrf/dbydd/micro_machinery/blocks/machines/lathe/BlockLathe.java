package mfrf.dbydd.micro_machinery.blocks.machines.lathe;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import mfrf.dbydd.micro_machinery.blocks.machines.TilePlaceHolder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockLathe extends MMMultiBlockBase {

    public BlockLathe(Properties properties, String name) {
        super(properties, name, true);
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(IS_PLACEHOLDER, false));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (!state.get(IS_PLACEHOLDER)) {
            return new TileLathe();
        } else {
            return new TilePlaceHolder();
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Direction direction = state.get(FACING);
        worldIn.setBlockState(pos.offset(direction.rotateYCCW()), getDefaultState().with(FACING, direction).with(IS_PLACEHOLDER, true));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(state.get(IS_PLACEHOLDER) ? pos.offset(state.get(FACING).rotateY()) : pos);

        }
        return ActionResultType.SUCCESS;
    }
}
