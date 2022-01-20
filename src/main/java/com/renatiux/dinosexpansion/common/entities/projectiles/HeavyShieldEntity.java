package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.common.items.shields.HeavyShieldDummy;
import com.renatiux.dinosexpansion.common.items.shields.HeavyShieldItem;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.EnchantmentUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HeavyShieldEntity extends Entity {

    private static final DataParameter<ItemStack> SHIELD = EntityDataManager.createKey(HeavyShieldEntity.class, DataSerializers.ITEMSTACK);
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(HeavyShieldEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    private static final ItemStackCompare HEAVY_SHIELD = (first, second) -> {
        if (first.isEmpty() || second.isEmpty()) {
            return false;
        }
        if (first.getItem() != ItemInit.HEAVY_SHIELD_DUMMY.get() || second.getItem() != ItemInit.HEAVY_SHIELD_DUMMY.get())
            return false;
        return HeavyShieldDummy.getHeavyShieldEntityId(first) == HeavyShieldDummy.getHeavyShieldEntityId(second);
    };

    private int cooldown, returningTicks = 0;
    private boolean infinite, callback = false;

    public HeavyShieldEntity(EntityType<?> type, World world) {
        super(type, world);
        cooldown = 0;
    }

    public HeavyShieldEntity(World world, Vector3d position, ItemStack shield, PlayerEntity owner) {
        this(world, position.x, position.y, position.z, shield, owner);
    }

    public HeavyShieldEntity(World world, double x, double y, double z, ItemStack shield, PlayerEntity owner) {
        super(EntityTypeInit.HEAVY_SHIELD_ENTITY_TYPE.get(), world);
        setPosition(x, y, z);
        setMotion(Vector3d.ZERO);
        this.dataManager.set(SHIELD, shield);
        this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
        if (DEModConfig.SHIELD_CONFIG.canBeInfinte.get().booleanValue() && EnchantmentUtils.getShieldStrenghLevel(shield) >= 5) {
            this.infinite = true;
        }
        cooldown = DEModConfig.SHIELD_CONFIG.heavyShieldBaseCooldownOnGround.get().intValue() + 100 * EnchantmentUtils.getShieldStrenghLevel(shield) + 100;
    }

    public void pushAway() {
        if (HeavyShieldItem.getCooldown(getShield()) <= 0) {
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(6, 1, 6), EntityPredicates.pushableBy(this));
            for (Entity e : list) {
                if (e instanceof PlayerEntity && isOwner((PlayerEntity) e)) {
                    continue;
                }
                if (e instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) e;
                    double x = this.getPosX() - e.getPosX();
                    double z = this.getPosZ() - e.getPosZ();
                    float strength = 3f * (EnchantmentUtils.getShieldStrenghLevel(getShield()) + 1f);
                    living.applyKnockback(strength, x, z);
                }
            }
            HeavyShieldItem.setPushAwayCooldown(getShield(), DEModConfig.SHIELD_CONFIG.heavyShieldCooldownPushAway.get().intValue());
        }
    }

    public int getCooldown(){
        return HeavyShieldItem.getCooldown(getShield());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isRemote && HeavyShieldItem.getCooldown(getShield()) > 0){
            HeavyShieldItem.addCooldown(getShield(), -1);
        }
        if (!infinite && !callback) {
            if (cooldown > 0) {
                cooldown--;
                if (cooldown <= 0) {
                    cooldown = 0;
                    if (EnchantmentUtils.hasBoundEnchantment(getShield())) {
                        callBack();
                    } else
                        this.removeWithDrop();
                }
            }
        }
        if (getShield().isEmpty()) {
            this.remove();
        }
        if (callback) {
            this.noClip = true;
            Vector3d toOwner = new Vector3d(getOwner().getPosX() - this.getPosX(), getOwner().getPosYEye() - this.getPosY(), getOwner().getPosZ() - this.getPosZ());
            this.setRawPosition(this.getPosX(), this.getPosY() + toOwner.y * 0.03, this.getPosZ());
            this.setMotion(this.getMotion().scale(0.95D).add(toOwner.normalize().scale(0.2d)));
            if (this.returningTicks == 0) {
                this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
            }
            ++this.returningTicks;

        }

        if (!this.noClip) {
            this.doBlockCollisions();
            List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow((double) 0.2F, (double) -0.01F, (double) 0.2F), EntityPredicates.pushableBy(this));
            if (!list.isEmpty()) {

                for (int j = 0; j < list.size(); ++j) {
                    Entity entity = list.get(j);
                    if (!entity.isPassenger(this)) {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }
        this.move(MoverType.SELF, getMotion());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (!this.getShield().isEmpty() && getOwner() != null) {
            this.getShield().damageItem((int) damage, getOwner(), p -> this.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 10f, 1f));
            System.out.println(getShield().getDamage());
            return true;
        }
        return false;
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        if (!world.isRemote && isOwner(player)) {
            if (!addItemToInventoryWithReturn(player)) {
                this.removeWithDrop();
            }
            this.remove();
        }
        return super.processInitialInteract(player, hand);
    }

    public void callBack() {
        this.callback = true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public void removeWithDrop() {
        for (int i = 0; i < getOwner().inventory.getSizeInventory(); i++) {
            ItemStack stack = getOwner().inventory.getStackInSlot(i);
            if (stack.getItem() == ItemInit.HEAVY_SHIELD_DUMMY.get()) {
                getOwner().inventory.setInventorySlotContents(i, ItemStack.EMPTY);
            }

        }
        this.entityDropItem(getShield());
        this.remove();
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity player) {
        if (!this.world.isRemote && this.noClip & this.callback && isOwner(player)) {
            if (this.addItemToInventoryWithReturn(player)) {
                player.onItemPickup(this, 1);
                this.remove();
            }
        }
    }

    private boolean addItemToInventory(PlayerEntity player) {
        if (player.getHeldItem(Hand.OFF_HAND).isEmpty()) {
            player.inventory.offHandInventory.set(0, this.getShield());
            return true;
        }
        return player.inventory.addItemStackToInventory(this.getShield());
    }

    private boolean addItemToInventoryWithReturn(PlayerEntity player) {
        if (getShield().isEmpty())
            return false;
        if (player.inventory.hasItemStack(new ItemStack(ItemInit.HEAVY_SHIELD_DUMMY.get()))) {
            int i = getSlotForStack(player.inventory, new ItemStack(ItemInit.HEAVY_SHIELD_DUMMY.get()));
            if (i >= 0) {
                player.inventory.setInventorySlotContents(i, getShield());
                return true;
            }
        }
        return player.inventory.addItemStackToInventory(getShield());
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if (!this.isRidingSameEntity(entityIn)) {
            if (!entityIn.noClip && !this.noClip) {
                double d0 = entityIn.getPosX() - this.getPosX();
                double d1 = entityIn.getPosZ() - this.getPosZ();
                double d2 = MathHelper.absMax(d0, d1);
                if (d2 >= (double) 0.01F) {
                    d2 = (double) MathHelper.sqrt(d2);
                    d0 = d0 / d2;
                    d1 = d1 / d2;
                    double d3 = 1.0D / d2;
                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 = d0 * d3;
                    d1 = d1 * d3;
                    d0 = d0 * (double) 0.05F;
                    d1 = d1 * (double) 0.05F;
                    d0 = d0 * (double) (1.0F - this.entityCollisionReduction);
                    d1 = d1 * (double) (1.0F - this.entityCollisionReduction);

                    if (!entityIn.isBeingRidden()) {
                        entityIn.setMotion(0d, entityIn.getMotion().y, 0d);
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }

            }
        }
    }

    @Override
    protected Vector3d func_241839_a(Direction.Axis axis, TeleportationRepositioner.Result result) {
        return LivingEntity.func_242288_h(super.func_241839_a(axis, result));
    }

    @Override
    protected void registerData() {
        this.dataManager.register(SHIELD, ItemStack.EMPTY);
        this.dataManager.register(OWNER, Optional.empty());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(SHIELD, ItemStack.read(compound));
        this.infinite = compound.getBoolean("infinite");
        this.callback = compound.getBoolean("callback");
        this.cooldown = compound.getInt("cooldown");
        if (compound.contains("owner"))
            this.dataManager.set(OWNER, Optional.of(compound.getUniqueId("owner")));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound = getShield().write(compound);
        compound.putBoolean("infinite", this.infinite);
        compound.putBoolean("callback", this.callback);
        compound.putInt("cooldown", this.cooldown);
        if (getOwnerUUID() != null)
            compound.putUniqueId("owner", getOwnerUUID());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isOwner(PlayerEntity player) {
        return this.getOwnerUUID().equals(player.getUniqueID());
    }

    public ItemStack getShield() {
        return this.dataManager.get(SHIELD);
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.dataManager.get(OWNER).orElse(null);
    }

    @Nullable
    public PlayerEntity getOwner() {
        if (getOwnerUUID() != null) {
            return this.world.getPlayerByUuid(getOwnerUUID());
        }
        return null;
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    public static int getSlotForStack(PlayerInventory inventory, ItemStack stack) {
        return getSlotForStack(inventory, stack, (f, s) -> f.isItemEqual(s));
    }

    public static int getSlotForStack(PlayerInventory inventory, ItemStack stack, ItemStackCompare compare) {
        if (stack.isEmpty())
            return -1;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (compare.areEqual(stack, inventory.getStackInSlot(i))) {
                return i;
            }
        }
        return -1;
    }

    public interface ItemStackCompare {
        boolean areEqual(ItemStack first, ItemStack second);
    }
}
