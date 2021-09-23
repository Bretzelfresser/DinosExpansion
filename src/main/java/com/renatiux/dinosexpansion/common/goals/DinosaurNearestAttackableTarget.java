package com.renatiux.dinosexpansion.common.goals;

import java.util.Iterator;
import java.util.List;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class DinosaurNearestAttackableTarget<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	protected final Dinosaur dinosaurGowalOwner;
	protected boolean callsForHelp;
	protected Class<?>[] reinforcementTypes;
	protected double range;
	protected int maxNumOfReinforcements;

	public DinosaurNearestAttackableTarget(Dinosaur goalOwnerIn, Class<T> attackingClass, boolean checkSight) {
		super(goalOwnerIn, attackingClass, checkSight);
		this.dinosaurGowalOwner = goalOwnerIn;
		callsForHelp = false;
		range = 0;
	}

	/**
	 * 
	 * @param range            - the range in which other will be alerted, make it 0
	 *                         in order to use the distance to the target(vanilla)
	 * @param reinformentTypes - the class of the Allies
	 * @return the new Goal
	 */
	public DinosaurNearestAttackableTarget<T> setCallsForHelp(double range, Class<?>... reinformentTypes) {
		callsForHelp = true;
		this.reinforcementTypes = reinformentTypes;
		this.range = range;
		this.maxNumOfReinforcements = 0;
		return this;
	}

	/**
	 * 
	 * @param reinformentTypes - the class of the Allies
	 * @return the new Goal
	 */
	public DinosaurNearestAttackableTarget<T> setCallsForHelp(Class<?>... reinformentTypes) {
		callsForHelp = true;
		this.reinforcementTypes = reinformentTypes;
		this.range = 0;
		this.maxNumOfReinforcements = 0;
		return this;
	}

	/**
	 * 
	 * @param range                 - the range in which other will be alerted, make
	 *                              it 0 in order to use the distance to the
	 *                              target(vanilla)
	 * @param maxNumOfReinforements - max number of reinforcements that can be
	 *                              alerted, make it 0 in order to alert all in
	 *                              range
	 * @param reinformentTypes      - the class of the Allies
	 * @return the new Goal
	 */
	public DinosaurNearestAttackableTarget<T> setCallsForHelp(final double range, final int maxNumOfReinforements,
			final Class<?>... reinformentTypes) {
		callsForHelp = true;
		this.reinforcementTypes = reinformentTypes;
		this.range = range;
		this.maxNumOfReinforcements = maxNumOfReinforements;
		return this;
	}

	@Override
	public boolean shouldExecute() {
		if ((!this.dinosaurGowalOwner.isTame() || this.dinosaurGowalOwner.getStatus() == DinosaurStatus.HOSTILE)
				&& !dinosaurGowalOwner.isChild() && !dinosaurGowalOwner.isSleeping() && !dinosaurGowalOwner.isKnockout()
				&& dinosaurGowalOwner.deathTime <= 0)
			return super.shouldExecute() && checkOwner();
		return false;
	}

	private boolean checkOwner() {
		if (this.target == null)
			return false;
		else if (this.target instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) this.target;
			return this.dinosaurGowalOwner.isOwner(player);
		}
		return false;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		if (callsForHelp)
			alertOthers();
	}

	@Override
	protected void findNearestTarget() {
		if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
			this.nearestTarget = this.goalOwner.world.getClosestEntity(this.targetClass, this.targetEntitySelector,
					this.goalOwner, this.goalOwner.getPosX(), this.goalOwner.getPosYEye(), this.goalOwner.getPosZ(),
					this.getTargetableArea(this.getTargetDistance()));
			if (this.nearestTarget != null)
				foundNonPlayer();
		} else {
			this.nearestTarget = this.goalOwner.world.getClosestPlayer(this.targetEntitySelector, this.goalOwner,
					this.goalOwner.getPosX(), this.goalOwner.getPosYEye(), this.goalOwner.getPosZ());
			if (this.nearestTarget != null) {
				foundPlayer();
			}
		}

		if (this.nearestTarget != null)
			foundTarget();

	}

	protected void alertOthers() {
		AxisAlignedBB axisalignedbb;
		if (range == 0)
			axisalignedbb = AxisAlignedBB.fromVector(this.goalOwner.getPositionVec()).grow(getTargetDistance(), 10.0D,
					getTargetDistance());
		else
			axisalignedbb = AxisAlignedBB.fromVector(this.goalOwner.getPositionVec()).grow(range, 10.0D, range);
		List<MobEntity> list = this.goalOwner.world.getLoadedEntitiesWithinAABB(this.goalOwner.getClass(),
				axisalignedbb);
		Iterator<MobEntity> iterator = list.iterator();
		int numberOfAlertedAllies = 0;
		while (true) {
			MobEntity mobentity;
			while (true) {
				if (!iterator.hasNext()) {
					return;
				}
				if (maxNumOfReinforcements != 0 && numberOfAlertedAllies >= maxNumOfReinforcements)
					return;
				mobentity = (MobEntity) iterator.next();
				if (this.dinosaurGowalOwner.isTame() && this.goalOwner != mobentity) {
					if (mobentity instanceof Dinosaur) {
						Dinosaur dinosaurEntity = (Dinosaur) mobentity;
						if (this.dinosaurGowalOwner.getOwnerUniqueId().equals(dinosaurEntity.getOwnerUniqueId())) {
							if (this.reinforcementTypes == null) {
								break;
							}

							boolean flag = false;

							for (Class<?> oclass : this.reinforcementTypes) {
								if (mobentity.getClass() == oclass) {
									flag = true;
									break;
								}
							}

							if (!flag) {
								break;
							}
						} else
							break;
					} else
						break;

				}
				// vergess die Tribes nicht
				else if (this.goalOwner != mobentity && mobentity instanceof Dinosaur
						&& this.dinosaurGowalOwner.getOwner() != ((Dinosaur) mobentity).getOwner()) {
					if (this.reinforcementTypes == null) {
						break;
					}

					boolean flag = false;

					for (Class<?> oclass : this.reinforcementTypes) {
						if (mobentity.getClass() == oclass) {
							flag = true;
							break;
						}
					}

					if (!flag) {
						break;
					}
				}
			}

			this.setAttackTarget(mobentity, this.goalOwner.getRevengeTarget());
			numberOfAlertedAllies++;
		}
	}

	protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
		mobIn.setAttackTarget(targetIn);
	}

	/**
	 * when the Goal has found a player as a target
	 */
	protected void foundPlayer() {
	}

	/**
	 * executed when the Goal has found something else as a Player as a target
	 */
	protected void foundNonPlayer() {
	}

	/**
	 * executed when found a target, after found Player or foundNonPlayer
	 */
	protected void foundTarget() {
	}

}
