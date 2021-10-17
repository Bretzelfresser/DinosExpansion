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
		if(idleAnimation != null)
			this.idleAnimation = new AnimationBuilder();
		else
			this.idleAnimation = idleAnimation;
		this.controllerName = controllerName;
	}
	/**
	 * enqueues the animation, when all animations before it were played this animation will be played too
	 */
	public void enqueueAnimation(AnimationBuilder animation) {
		animationQueue.add(animation);
	}
	/**
	 * plays the give animation as soon as possible
	 */
	public void playASAP(AnimationBuilder animation) {
		animationQueue.addFirst(animation);
		refreshAnimation();
	}
	
	/**
	 * plays this animation when it is not already playing
	 * @param animation
	 * @param transition
	 */
	public void playASAPIfNotAlready(AnimationBuilder animation, int transition) {
		@SuppressWarnings("unchecked")
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		if (animation.getRawAnimationList().get(0).animationName.equals(controller.getCurrentAnimation().animationName))
			return;
		animationQueue.addFirst(animation);
		refreshAnimation(transition);
	}
	
	/**
	 *  plays the next animation on the queue, when nothing is on the queue it is playing the idle animation
	 * instantly stops the current animation
	 */
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
	/**
	 * plays the next animation on the queue, when nothing is on the queue it is playing the idle animation
	 * instantly stops the current animation
	 * @param transition - the time it takes to switch from the old animation to the new animation
	 */
	@SuppressWarnings("unchecked")
	public void refreshAnimation(int transition) {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		controller.transitionLengthTicks = transition;
		if (!animationQueue.isEmpty()) {
			controller.setAnimation(animationQueue.poll());
		} else {
			controller.setAnimation(idleAnimation);
		}
	}
	/**
	 * plays the queue when no other animation is played or the idle animation is played
	 */
	public void playQueue() {
		@SuppressWarnings("unchecked")
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, dino.getEntityId(),
				controllerName);
		if (isPlayingIdleAnimation(controller) || controller.getAnimationState() == AnimationState.Stopped) {
			refreshAnimation();
		}

	}
	
	/**
	 * plays the given animation as soon as possible with a transition
	 * @param animation
	 * @param transition
	 */
	public void playASAP(AnimationBuilder animation, int transition) {
		animationQueue.addFirst(animation);
		refreshAnimation(transition);
	}
	
	/**
	 * checks whetehr the idle Animation is played currently
	 * uses {@link AnimationQueue#isPlayingAnimation(AnimationController, AnimationBuilder)}
	 */
	public boolean isPlayingIdleAnimation(AnimationController<Allosaurus> controller) {
		return isPlayingAnimation(controller, idleAnimation);
	}
	
	/**
	 * checks if this animation is currently playing
	 * this only checks if the current played animation is present in the animation builder
	 */
	public boolean isPlayingAnimation(AnimationController<Allosaurus> controller, AnimationBuilder animation) {
		return animation.getRawAnimationList().stream().map(anim -> anim.animationName).anyMatch((name) -> name.equals(controller.getCurrentAnimation().animationName));
	}
}
