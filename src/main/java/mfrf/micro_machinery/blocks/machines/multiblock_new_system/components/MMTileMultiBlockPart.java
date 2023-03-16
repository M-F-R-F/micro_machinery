package mfrf.micro_machinery.blocks.machines.multiblock_new_system.components;

import mfrf.micro_machinery.blocks.machines.multiblock_new_system.components.main_parts.MMTileMainPartBase;
import mfrf.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MMTileMultiBlockPart extends BlockEntity {
    private CompoundTag packed = new CompoundTag();
    private BlockPos mainPart = null;


    public MMTileMultiBlockPart(BlockPos pos, BlockState state) {
        super(RegisteredBlockEntityTypes.MULTI_BLOCK_PART.get(), pos, state);
    }

    public MMTileMultiBlockPart(BlockEntityType<?> p_i48289_1_, BlockPos pos, BlockState state) {
        super(p_i48289_1_, pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        packed = compound.getCompound("packed");
        if (compound.contains("main")) {
            mainPart = NBTUtil.readBlockPos(compound.getCompound("main"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("packed", packed);
        if (mainPart != null) {
            pTag.put("main", NBTUtil.writeBlockPos(mainPart));
        }
    }

    public void setPacked(CompoundTag nbt, BlockPos mainPart) {
        packed = nbt;
        this.mainPart = mainPart;
        ((MMTileMainPartBase) level.getBlockEntity(mainPart)).link(this.getBlockPos());
        setChanged();
    }

    public CompoundTag getPacked() {
        return packed;
    }

    public InteractionResult onBlockActivated(Level worldIn, Player player, InteractionHand handIn, BlockHitResult hit) {
        return InteractionResult.SUCCESS;
    }

    //todo check
    public void onBlockHarvest(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        ((MMTileMainPartBase) worldIn.getBlockEntity(mainPart)).onBlockHarvest(worldIn, pos, state);
    }
}
