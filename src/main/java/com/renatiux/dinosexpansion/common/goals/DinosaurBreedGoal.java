package com.renatiux.dinosexpansion.common.goals;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DinosaurBreedGoal extends Goal {
	   private static final EntityPredicate field_220689_d = (new EntityPredicate()).setDistance(8.0D).allowInvulnerable().allowFriendlyFire();
	   protected final Dinosaur animal;
	   private final Class<? extends Dinosaur> mateClass;
	   protected final World world;
	   protected Dinosaur targetMate;
	   private int spawnBabyDelay;
	   private final double moveSpeed;

	   /**
	    * mates with the same class
	    */
	   public DinosaurBreedGoal(Dinosaur animal, double speedIn) {
	      this(animal, speedIn, animal.getClass());
	   }
	   
	   /**
	    * mates with the given class
	    */
	   public DinosaurBreedGoal(Dinosaur animal, double moveSpeed, Class<? extends Dinosaur> mateClass) {
	      this.animal = animal;
	      this.world = animal.world;
	      this.mateClass = mateClass;
	      this.moveSpeed = moveSpeed;
	      this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	   }

	   /**
	    * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	    * method as well.
	    */
	   public boolean shouldExecute() {
	      if (!this.animal.isReadyToBreed()) {
	         return false;
	      } else {
	         this.targetMate = this.getNearbyMate();
	         return this.targetMate != null;
	      }
	   }

	   /**
	    * Returns whether an in-progress EntityAIBase should continue executing
	    */
	   public boolean shouldContinueExecuting() {
	      return this.targetMate.isAlive() && this.targetMate.isReadyToBreed() && this.spawnBabyDelay < 60;
	   }

	   /**
	    * Reset the task's internal state. Called when this task is interrupted by another one
	    */
	   public void resetTask() {
	      this.targetMate = null;
	      this.spawnBabyDelay = 0;
	   }

	   /**
	    * Keep ticking a continuous task that has already been started
	    */
	   public void tick() {
	      this.animal.getLookController().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.animal.getVerticalFaceSpeed());
	      this.animal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
	      ++this.spawnBabyDelay;
	      if (this.spawnBabyDelay >= 60 && this.animal.getDistanceSq(this.targetMate) < 9.0D) {
	         this.spawnBaby();
	      }

	   }

	   /**
	    * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
	    * valid mate found.
	    */
	   @Nullable
	   private Dinosaur getNearbyMate() {
	      List<Dinosaur> list = this.world.getTargettableEntitiesWithinAABB(this.mateClass, field_220689_d, this.animal, this.animal.getBoundingBox().grow(8.0D));
	      double d0 = Double.MAX_VALUE;
	      Dinosaur animalentity = null;

	      for(Dinosaur animalentity1 : list) {
	         if (this.animal.canBreedWith(animalentity1) && this.animal.getDistanceSq(animalentity1) < d0) {
	            animalentity = animalentity1;
	            d0 = this.animal.getDistanceSq(animalentity1);
	         }
	      }

	      return animalentity;
	   }

	   /**
	    * Spawns a baby animal of the same type.
	    */
	   protected void spawnBaby() {
	      this.animal.spawnChild((ServerWorld)this.world, this.targetMate);
	   }
	}

