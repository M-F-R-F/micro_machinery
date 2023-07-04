package mfrf.micro_machinery.item;

import mfrf.micro_machinery.events.RegistryThingsEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MMHammerBase extends DiggerItem {
    public static Map<String, Supplier<Item>> registeries = new HashMap<>();

    /**
     * @param attackDamageIn
     * @param attackSpeedIn
     * @param tier
     */
    public MMHammerBase(float attackDamageIn, float attackSpeedIn, Tier tier, Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, BlockTags.MINEABLE_WITH_PICKAXE, builder);

        RegistryThingsEvent.getOrCreateItemListToRegisterTab(CreativeModeTabs.TOOLS_AND_UTILITIES).add(() -> this);

    }

    @Override
    public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isClientSide && state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(1, entityLiving, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            Vec3 lookVec = entityLiving.getLookAngle();
            Direction direction = Direction.getNearest(lookVec.x, lookVec.y, lookVec.z);
            if (direction == Direction.DOWN || direction == Direction.UP) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos position = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                        digBlock(worldIn, position, entityLiving);
                        super.mineBlock(stack, worldIn, state, position, entityLiving);
                    }
                }
            } else {
                if (direction == Direction.WEST || direction == Direction.EAST) {
                    for (int z = -1; z <= 1; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos position = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ() + z);
                            digBlock(worldIn, position, entityLiving);
                            super.mineBlock(stack, worldIn, state, position, entityLiving);
                        }
                    }
                } else {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos position = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ());
                            digBlock(worldIn, position, entityLiving);
                            super.mineBlock(stack, worldIn, state, position, entityLiving);
                        }
                    }
                }
            }
        }
        return true;
    }

    private void digBlock(Level worldIn, BlockPos position, LivingEntity entityLiving) {
        BlockState blockstate = worldIn.getBlockState(position);
        if (entityLiving instanceof Player player && canAttackBlock(blockstate, worldIn, position, player)) {
            worldIn.destroyBlock(position, true, entityLiving);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
//        if (!context.getLevel().isClientSide()) {
//            tryConstructNewStructures(context);
//        }
        return InteractionResult.SUCCESS;
    }

    //todo multiblock
//    private boolean tryConstructNewStructures(UseOnContext context) {
//        Level world = context.getLevel();
//        BlockPos pos = context.getClickedPos();
//        Triple<String, MultiblockStructureMaps.StructureMap, Direction> packedData = MultiblockStructureMaps.findStructure(world, pos);
//        if (packedData != null) {
//            String id = packedData.getLeft();
//            MultiblockStructureMaps.StructureMap map = packedData.getMiddle();
//            Direction direction = packedData.getRight();
//            map.construct(direction, world, pos, id);
//            return true;
//        }
//        return false;
//    }

}
