package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.animation.AnimationQueue;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;

public class Alioramus extends Dinosaur{

    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("030E0FFB-87AE-4653-9556-501010E221A0");
    private static final String CONTROLLER_NAME = "controller";
    public static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(
            SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", 1F, AttributeModifier.Operation.MULTIPLY_BASE);

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0)
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

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
    public int neededHungerToTame() {
        return 0;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int reduceHunger(int hunger) {
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
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }
}
