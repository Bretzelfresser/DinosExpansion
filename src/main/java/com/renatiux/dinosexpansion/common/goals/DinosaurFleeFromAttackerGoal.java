package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;
import com.renatiux.dinosexpansion.common.entities.util.IFleeingDinosaur;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class DinosaurFleeFromAttackerGoal extends Goal {
	private Dinosaur dino;

	public DinosaurFleeFromAttackerGoal(Dinosaur dino) {
		this.dino = dino;
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
		LivingEntity livingentity = dino.getRevengeTarget();
		if (livingentity != null && dino.getStatus() == DinosaurStatus.WANDER && dino.getDistanceSq(livingentity) < 100.0D) {
			setFleeingIfPossible(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
	}
	
	@Override
	public void resetTask() {
		super.resetTask();
		setFleeingIfPossible(false);
	}
	
	protected void setFleeingIfPossible(boolean fleeing){
		if(dino instanceof IFleeingDinosaur) {
			((IFleeingDinosaur) dino).setFleeing(false);
		}
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		LivingEntity livingentity = dino.getRevengeTarget();
		if (livingentity != null) {
			Vector3d vector3d = new Vector3d(dino.getPosX() - livingentity.getPosX(),
					dino.getPosY() - livingentity.getPosY(), dino.getPosZ() - livingentity.getPosZ());
			BlockState blockstate = dino.world.getBlockState(new BlockPos(dino.getPosX() + vector3d.x,
					dino.getPosY() + vector3d.y, dino.getPosZ() + vector3d.z));
			FluidState fluidstate = dino.world.getFluidState(new BlockPos(dino.getPosX() + vector3d.x,
					dino.getPosY() + vector3d.y, dino.getPosZ() + vector3d.z));
			if (fluidstate.isTagged(FluidTags.WATER) || blockstate.isAir()) {
				double d0 = vector3d.length();
				if (d0 > 0.0D) {
					vector3d.normalize();
					float f = 3.0F;
					if (d0 > 5.0D) {
						f = (float) ((double) f - (d0 - 5.0D) / 5.0D);
					}

					if (f > 0.0F) {
						vector3d = vector3d.scale((double) f);
					}
				}

				if (blockstate.isAir()) {
					vector3d = vector3d.subtract(0.0D, vector3d.y, 0.0D);
				}

				dino.setMotion((float) vector3d.x / 20.0F, (float) vector3d.y / 20.0F, (float) vector3d.z / 20.0F);
			}

		}
	}
}
