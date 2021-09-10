package com.renatiux.dinosexpansion.mixin;

import com.renatiux.dinosexpansion.core.init.PotionInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Inject(at = @At("RETURN"), method = "isOnLadder", cancellable = true)
    private void isClimbing(CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity) this.getEntity()).isPotionActive(PotionInit.CLIMB_EFFECT.get()) && this.getEntity().collidedHorizontally)
            cir.setReturnValue(true);
    }
}
