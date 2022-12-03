package mfrf.dbydd.micro_machinery.items;

import mfrf.dbydd.micro_machinery.Config;
import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.utils.EnergyItemHandler;
import mfrf.dbydd.micro_machinery.utils.FEContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemLaserDrill extends MMItemBase {

    public ItemLaserDrill(String name) {
        super(new Properties().group(Micro_Machinery.MMTAB).maxStackSize(1), name);
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
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos playerPos = player.getPosition();
        double dist = Math.sqrt(playerPos.distanceSq(pos));
        ItemStack heldItem = player.getHeldItem(hand);
        Vec3d hitVec = context.getHitVec();

        if (!world.isRemote()) {
            if (heldItem.getItem() instanceof ItemLaserDrill) {
//            heldItem.getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
//                Optional<Pair<Entity, Double>> min = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos, playerPos))
//                        .stream()
//                        .map(entity -> new Pair<Entity, Double>(entity, lookatEntity(player, entity, dist)))
//                        .filter(pair -> pair.getValue() != -1)
//                        .min(Comparator.comparingDouble(Pair::getValue));
//                min.ifPresent(entityDoublePair -> {
//                    Entity key = entityDoublePair.getKey();
//                    if (key instanceof LivingEntity) {
//                        try {
//                            Method damageEntity = LivingEntity.class.getMethod("damageEntity", DamageSource.class, float.class);
//                            damageEntity.invoke(key, DamageSource.causePlayerDamage(player), Config.LASER_DRILL_DAMAGE.get());
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//            });

                BlockState blockState = world.getBlockState(pos);


//todo complete mine
            }
        } else {
            for (double d = 0; d < dist; d += 0.2) {
                Vec3d playerPositionVec = player.getPositionVec();
                Vec3d partPos = playerPositionVec.add(hitVec.scale(d));
                Vec3d speed = hitVec.scale(2.5);
                world.addParticle(ParticleTypes.FLAME, partPos.x, partPos.y, partPos.z, speed.x, speed.y, speed.z);
                double[] randPosOffs = random.doubles(30, 0.1, 0.3).toArray();
                double[] randSpeedOffsets = random.doubles(30, 0, 0.5).toArray();
                for (int i = 0; i < 10; i++) {
                    world.addParticle(
                            ParticleTypes.FLAME,
                            true,
                            partPos.x + randPosOffs[random.nextInt(30)],
                            partPos.y + randPosOffs[random.nextInt(30)],
                            partPos.z + randPosOffs[random.nextInt(30)],
                            speed.x + randSpeedOffsets[random.nextInt(30)],
                            speed.y + randSpeedOffsets[random.nextInt(30)],
                            speed.z + randSpeedOffsets[random.nextInt(30)]);
                }
            }
        }
        return super.onItemUse(context);
    }

    private double lookatEntity(PlayerEntity player, Entity entity, Double max) {
        Vec3d vec3d = player.getLook(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(entity.getPosX() - player.getPosX(), entity.getPosYEye() - player.getPosYEye(), entity.getPosZ() - player.getPosZ());
        double d0 = vec3d1.length();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        double length = vec3d.length();
        return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(entity) || length <= max ? vec3d1.length() : -1.0;
    }
}
