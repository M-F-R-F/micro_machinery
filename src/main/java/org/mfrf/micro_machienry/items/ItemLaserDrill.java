package org.mfrf.micro_machienry.items;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.utils.EnergyItemHandler;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ItemLaserDrill extends MMItemBase {

    public ItemLaserDrill(String name) {
        super(new Properties().group(MicroMachinery.MMTAB).maxStackSize(1), name);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (!stack.isEmpty())
            return new EnergyItemHandler(stack, new FEContainer(0, Config.LASER_DRILL_ENERGY_CAP.get()) {
                @Override
                protected int selfSubtractValue() {
                    return Config.LASER_DRILL_ENERGY_CONSUME.get();
                }

                @Override
                public boolean canExtract() {
                    return false;
                }

                @Override
                public boolean canReceive() {
                    return true;
                }

            });
        return null;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        Hand hand = context.getHand();
        PlayerEntity player = context.getPlayer();
        ServerWorld world = ((ServerWorld) context.getWorld());
        Vec3d lookVec = player.getLookVec();
        Vec3d positionVec = player.getPositionVec();
        ItemStack heldItem = player.getHeldItem(hand);

        if (!world.isRemote()) {

            if (heldItem.getItem() instanceof ItemLaserDrill) {

                Vec3d start = positionVec.add(lookVec.scale(0.3));

                AtomicReference<BlockPos> blockPos = null;
                heldItem.getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
                    int extracted = iEnergyStorage.extractEnergy(100, false);
                    if (extracted == 100) {
                        if (world.getDayTime() % 10 == 0) {
                            for (int i = 0; i < 20000; i++) {
                                //step = 0.8
                                Vec3d add = positionVec.add(lookVec.scale(1 + 0.8 * i));
                                blockPos.set(new BlockPos(add));
                                BlockState blockState = world.getBlockState(blockPos.get());
                                if (!blockState.isAir()) {
                                    world.destroyBlock(blockPos.get(), true, player);
                                    break;
                                } else {
                                    blockPos.set(null);
                                }
                                List<LivingEntity> loadedEntitiesWithinAABB = world.getLoadedEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(add.add(-0.3, -0.3, -0.3), add.add(0.3, 0.3, 0.3)));
                                if (!loadedEntitiesWithinAABB.isEmpty()) {
                                    for (LivingEntity livingEntity : loadedEntitiesWithinAABB) {
                                        // stupid
//                                        ((DamageEntity) livingEntity).DamageEntity(DamageSource.MAGIC, 4);
                                        livingEntity.damageEntity(DamageSource.IN_FIRE, 4);
                                    }
                                    break;
                                }
                            }
                        }

                    }
                });
                if (blockPos.get() == null) {
                    Vec3d end = positionVec.add(lookVec.scale(80));
                    world.spawnParticle(ParticleTypes.FLAME, start.x, start.y, start.z, 24, end.x, end.y, end.z, 2.5);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }


//    private  nearEntity(Vec3d sampleP, World world, Double range) {
//    }
}
