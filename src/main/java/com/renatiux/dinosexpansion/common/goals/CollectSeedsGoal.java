package com.renatiux.dinosexpansion.common.goals;

import java.util.List;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CollectSeedsGoal extends Goal {

	private Dodo dodo;
	private int cooldown, boxX = 15, boxZ = 15;
	private BlockPos foundBush;
	private final World world;
	private double speed;

	public CollectSeedsGoal(Dodo dino, double speed) {
		this.dodo = dino;
		cooldown = 0;
		this.world = dino.world;
		foundBush = null;
		this.speed = speed;

	}

	@Override
	public boolean shouldExecute() {
		if (dodo.isKnockout() || dodo.isSleeping() || dodo.getStatus() != DinosaurStatus.WANDER)
			return false;
		return findBush();
	}

	@Override
	public void tick() {
		if (cooldown > 0)
			cooldown--;
		else {
			if(dodo.getNavigator().canEntityStandOnPos(foundBush)) {
				dodo.getNavigator().setPath(dodo.getNavigator().getPathToPos(foundBush, 1), speed);
			}
			if(dodo.getDistanceSq(foundBush.getX(), foundBush.getY(), foundBush.getZ()) <= 2) {
				List<Item> possibleItems = Tags.Items.DODO_COLLECTABLES.getAllElements();
				int randIndex = world.rand.nextInt(possibleItems.size() - 1);
				dodo.getDinosaurInventory().addItem(new ItemStack(possibleItems.get(randIndex)));
				resetTask();
			}
		}
	}

	private boolean findBush() {
		for (int x = -boxX; x < boxX; x++) {
			for (int z = -boxZ; z < boxZ; z++) {
				BlockPos currentPos = dodo.getPosition().add(x, 0, z);
				while(world.getBlockState(currentPos.up()).isSolid()) {
					currentPos = currentPos.up();
					System.out.println("what");
				}
				if(world.getBlockState(currentPos).isIn(Tags.Blocks.DODO_BUSHES)) {
					foundBush = currentPos;
					System.out.println("bush found!");
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void resetTask() {
		cooldown = 1200;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !dodo.isInventoryFull();
	}

}
