package com.renatiux.dinosexpansion.common.entities.dinosaurs.animation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

@OnlyIn(Dist.CLIENT)
public class AnimationQueue<T extends Dinosaur> {

	protected final T dino;
	@Nullable
	protected final AnimationBuilder idleAnimation;
	protected final String controllerName;
	protected final Deque<AnimationBuilder> animationQueue = new ArrayDeque<>();
	protected final AnimationFactory factory;
	
	/**
	 * 
	 * @param dino - the dino this should be played on
	 * @param idleAnimation - the idle Animation, make it null in order to not play an idle animation
	 */
	public AnimationQueue(T dino, @Nullable AnimationBuilder idleAnimation, String controllerName, AnimationFactory factory) {
		Objects.requireNonNull(dino);
		Objects.requireNonNull(controllerName);
		Objects.requireNonNull(factory);
		this.factory = factory;
		this.dino = dino;
		this.idleAnimation = idleAnimation;
		this.controllerName = controllerName;
	}
	
	public void enqueueAnimation(AnimationBuilder animation) {
		animationQueue.add(animation);
	}
	public void playASAP(AnimationBuilder animation) {
		animationQueue.addFirst(animation);
		refreshAnimation();
	}
	public void playASAPIfNotAlready(AnimationBuilder animation, int transition) {
		@SuppressWarnings("unchecked")
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		if (animation.getRawAnimationList().get(0).animationName.equals(controller.getCurrentAnimation().animationName))
			return;
		animationQueue.addFirst(animation);
		refreshAnimation(transition);
	}
	
	@SuppressWarnings("unchecked")
	public void refreshAnimation() {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		controller.transitionLengthTicks = 0;
		if (!animationQueue.isEmpty()) {
			controller.setAnimation(animationQueue.poll());
		} else {
			controller.setAnimation(idleAnimation);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void refreshAnimation(int transition) {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		controller.transitionLengthTicks = transition;
		if (!animationQueue.isEmpty()) {
			controller.setAnimation(animationQueue.poll());
		} else {
			controller.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_IdleContinue.new", true));
		}
	}
	
	public void playQueue() {
		@SuppressWarnings("unchecked")
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		if (isPlayingIdleAnimation(controller) || controller.getAnimationState() == AnimationState.Stopped) {
			refreshAnimation();
		}

	}
	
	public void playASAP(AnimationBuilder animation, int transition) {
		animationQueue.addFirst(animation);
		refreshAnimation(transition);
	}
	
	
	public boolean isPlayingIdleAnimation(AnimationController<Allosaurus> controller) {
		return controller.getCurrentAnimation().animationName.equals("Alt_Allosaurus_IdleContinue.new")
				|| controller.getCurrentAnimation().animationName.equals("Alt_Allosaurus_Idle.new");
	}
}
