package org.mfrf.micro_machienry.mixins;

import mfrf.dbydd.micro_machinery.mixins.interfaces.DamageEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements DamageEntity {

    @Shadow
    protected abstract void damageEntity(DamageSource damageSrc, float damageAmount);

    @Override
    public void DamageEntity(DamageSource damageSrc, float damageAmount) {
        damageEntity(damageSrc, damageAmount);
    }
}


