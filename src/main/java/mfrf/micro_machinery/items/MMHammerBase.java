package mfrf.micro_machinery.items;

import mfrf.micro_machinery.blocks.machines.multi_block_old_system.MMMultiBlockHolderBase;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockPlaceHolder;
import mfrf.micro_machinery.utils.DeprecatedMultiBlockStructureMaps;
import mfrf.micro_machinery.utils.MultiblockStructureMaps;
import mfrf.micro_machinery.utils.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InteractionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
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
    public MMHammerBase(float attackDamageIn, float attackSpeedIn, Tier tier, Item.Properties builder, String name) {
        super(attackDamageIn, attackSpeedIn, tier, BlockTags.MINEABLE_WITH_PICKAXE, builder);
        registeries.put(name, () -> this);
        this.addPropertyOverride(new ResourceLocation("damage_tier"), (p_call_1_, p_call_2_, p_call_3_) -> {
            float value = (float) p_call_1_.getDamage() / (float) p_call_1_.getMaxDamage();
            return (float) (value <= 0.4 ? 1.0 : value <= 0.6 ? 2.0 : 3.0);
        });
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isClientSide && state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(1, entityLiving, (player) -> {
                player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });

            Vec3d lookVec = entityLiving.getLookVec();
            Direction direction = Direction.getFacingFromVector(lookVec.x, lookVec.y, lookVec.z);
            if (direction == Direction.DOWN || direction == Direction.UP) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos position = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                        digBlock(worldIn, position, entityLiving);
                    }
                }
            } else {
                if (direction == Direction.WEST || direction == Direction.EAST) {
                    for (int z = -1; z <= 1; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos position = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ() + z);
                            digBlock(worldIn, position, entityLiving);
                        }
                    }
                } else {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos position = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ());
                            digBlock(worldIn, position, entityLiving);
                        }
                    }
                }
            }
        }
        return true;
    }

    private void digBlock(World worldIn, BlockPos position, LivingEntity entityLiving) {
        BlockState blockstate = worldIn.getBlockState(position);
        if (canHarvestBlock(blockstate)) {
            worldIn.destroyBlock(position, true, entityLiving);
        }
    }

    @Override
    public InteractionResult onItemUse(ItemUseContext context) {
        if (!context.getWorld().isClientSide()) {
            boolean success = readOldStructures(context);
            if (!success) {
                success = readNewStructures(context);
            }

        }
        return InteractionResult.SUCCESS;
    }

    private boolean readOldStructures(ItemUseContext context) {
        for (Map.Entry<String, DeprecatedMultiBlockStructureMaps.MultiBlockPosBox> stringMultiBlockPosBoxEntry : DeprecatedMultiBlockStructureMaps.getStructureMaps().entrySet()) {
            Direction face = context.getFace();
            DeprecatedMultiBlockStructureMaps.MultiBlockPosBox multiBlockPosBox = stringMultiBlockPosBoxEntry.getValue().rotateTo(context.getFace().getOpposite());
            if (multiBlockPosBox.matchAll(context.getPos(), context.getWorld())) {
                BlockPos pos = context.getPos();
                World world = context.getWorld();

                CompoundTag blockPackNBT = NBTUtil.getBlockPackNBT(world, pos);
                world.setBlockState(pos, MMMultiBlockHolderBase.MAIN_PART_LIST.get(stringMultiBlockPosBoxEntry.getKey()).defaultBlockState().setValue(MMMultiBlockHolderBase.FACING, face));
                MMMultiBlockTileMainPartBase mainPartBase = (MMMultiBlockTileMainPartBase) world.getBlockEntity(pos);
                mainPartBase.saveBlockBeenReplaced(blockPackNBT);

                ArrayList<nodeToBeProcess> posToBeLink = new ArrayList<>();

                for (DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.BlockNode blockNode : multiBlockPosBox.getBlockNodes()) {
                    BlockPos blockNodePos = new BlockPos(blockNode.getPos());
                    if (!(blockNodePos.getX() == 0 && blockNodePos.getY() == 0 && blockNodePos.getZ() == 0)) {
                        BlockPos posInProgress = pos.add(blockNodePos);
                        if (!multiBlockPosBox.getAccessories().values().stream().anyMatch(access -> access.getPos().equals(blockNodePos))) {

                            BlockPlaceHolder.packageBlock(world, posInProgress, pos);

                        } else {
                            DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode node = (DeprecatedMultiBlockStructureMaps.MultiBlockPosBox.AccessoryNode) blockNode;
                            posToBeLink.add(new nodeToBeProcess(pos, node.getArg1(), node.getArg2(), node.getArg3()));
                        }
                    }
                }

                //there is no accessories in old structures
//                for (nodeToBeProcess nodeToBeProcess : posToBeLink) {
//                    ((BlockAccessoryPlaceHolder) world.getBlockState(nodeToBeProcess.pos).getBlock()).LinkToMainPart(pos, world, nodeToBeProcess.arg1, nodeToBeProcess.arg2, nodeToBeProcess.arg3);
//                }
                return true;
            }
        }
        return false;
    }

    private boolean readNewStructures(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Triple<String, MultiblockStructureMaps.StructureMap, Direction> packedData = MultiblockStructureMaps.findStructure(world, pos);
        if (packedData != null) {
            String id = packedData.getLeft();
            MultiblockStructureMaps.StructureMap map = packedData.getMiddle();
            Direction direction = packedData.getRight();
            map.construct(direction, world, pos, id);
            return true;
        }
        return false;
    }

    class nodeToBeProcess {
        private final BlockPos pos;
        private final String arg1;
        private final String arg2;
        private final String arg3;

        nodeToBeProcess(BlockPos pos, String arg1, String arg2, String arg3) {
            this.pos = pos;
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.arg3 = arg3;
        }
    }

}
