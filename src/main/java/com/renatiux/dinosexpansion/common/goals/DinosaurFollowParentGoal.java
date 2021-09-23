package com.renatiux.dinosexpansion.common.goals;

import java.util.List;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.ai.goal.Goal;

public class DinosaurFollowParentGoal extends Goal{

	 private final Dinosaur childDino;
	   private Dinosaur parentDino;
	   private final double moveSpeed;
	   private int delayCounter;

	   public DinosaurFollowParentGoal(Dinosaur dino, double speed) {
	      this.childDino = dino;
	      this.moveSpeed = speed;
	   }

	   /**
	    * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	    * method as well.
	    */
	   public boolean shouldExecute() {
	      if (!this.childDino.isChild()) {
	         return false;
	      } else {
	         List<Dinosaur> list = this.childDino.world.getEntitiesWithinAABB(this.childDino.getClass(), this.childDino.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
	         Dinosaur tempDino = null;
	         double d0 = Double.MAX_VALUE;

	         for(Dinosaur dino : list) {
	            if (!dino.isChild()) {
	               double d1 = this.childDino.getDistanceSq(dino);
	               if (d1 <= d0) {
	                  d0 = d1;
	                  tempDino = dino;
	               }
	            }
	         }

	         if (tempDino == null) {
	            return false;
	         } else if (d0 < 9.0D) {
	            return false;
	         } else {
	            this.parentDino = tempDino;
	            return true;
	         }
	      }
	   }

	   /**
	    * Returns whether an in-progress EntityAIBase should continue executing
	    */
	   public boolean shouldContinueExecuting() {
	      if (!this.childDino.isChild()) {
	         return false;
	      } else if (!this.parentDino.isAlive()) {
	         return false;
	      } else {
	         double d0 = this.childDino.getDistanceSq(this.parentDino);
	         return !(d0 < 9.0D) && !(d0 > 256.0D);
	      }
	   }

	   /**
	    * Execute a one shot task or start executing a continuous task
	    */
	   public void startExecuting() {
	      this.delayCounter = 0;
	   }

	   /**
	    * Reset the task's internal state. Called when this task is interrupted by another one
	    */
	   public void resetTask() {
	      this.parentDino = null;
	   }

	   /**
	    * Keep ticking a continuous task that has already been started
	    */
	   public void tick() {
	      if (--this.delayCounter <= 0) {
	         this.delayCounter = 10;
	         this.childDino.getNavigator().tryMoveToEntityLiving(this.parentDino, this.moveSpeed);
	      }
	   }

	

}
