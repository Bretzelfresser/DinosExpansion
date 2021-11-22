package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.animation.AnimationQueue;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class Alioramus extends Dinosaur{
    /**
     * @param type
     * @param worldIn
     * @param sizeInventory - the size for slots that should be available, also
     *                      counting saddle, armor and chest slot
     * @param child         - whether the Dino should be a child or not
     */
    public Alioramus(EntityType<? extends Dinosaur> type, World worldIn, int sizeInventory, boolean child) {
        super(type, worldIn, sizeInventory, child);
    }

    @Override
    protected AnimationQueue<Dinosaur> createAnimationQueue(AnimationFactory factory) {
        return null;
    }

    @Override
    protected AxisAlignedBB getChildBoundingBox(AxisAlignedBB superBox) {
        return null;
    }

    @Override
    protected AxisAlignedBB getYoungBoundingBox(AxisAlignedBB superBox) {
        return null;
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public boolean canEat(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxHunger() {
        return 0;
    }

    @Override
    public int getMaxNarcotic() {
        return 0;
    }

    @Override
    public int getTimeBetweenEating() {
        return 0;
    }

    @Override
    protected String getContainerName() {
        return null;
    }

    @Override
    public int shrinkNarcotic(int narcotic) {
        return 0;
    }

    @Override
    protected <T extends Dinosaur> TamingBahviour<T> getTamingBehaviour() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }
}
