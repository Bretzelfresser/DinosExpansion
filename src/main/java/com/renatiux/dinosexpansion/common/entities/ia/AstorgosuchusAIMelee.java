package com.renatiux.dinosexpansion.common.entities.ia;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Astorgosuchus;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class AstorgosuchusAIMelee extends MeleeAttackGoal {

    private Astorgosuchus astorgosuchus;

    public AstorgosuchusAIMelee(Astorgosuchus crocodile, double speedIn, boolean useLongMemory) {
        super(crocodile, speedIn, useLongMemory);
        this.astorgosuchus = crocodile;
    }

    public boolean shouldExecute() {

        return super.shouldExecute() && astorgosuchus.getPassengers().isEmpty();
    }

    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting() && astorgosuchus.getPassengers().isEmpty();
    }

    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0) {
            this.resetSwingCooldown();
            this.attacker.swingArm(Hand.MAIN_HAND);
            this.attacker.attackEntityAsMob(enemy);
        }

    }
}
