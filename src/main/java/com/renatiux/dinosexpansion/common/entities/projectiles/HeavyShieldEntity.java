package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.common.items.shields.HeavyShieldDummy;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.EnchantmentUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HeavyShieldEntity extends Entity {

    private static final DataParameter<ItemStack> SHIELD = EntityDataManager.createKey(HeavyShieldEntity.class, DataSerializers.ITEMSTACK);

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
    private PlayerEntity owner;

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
        this.owner = owner;
        if (DEModConfig.SHIELD_CONFIG.canBeInfinte.get().booleanValue() && EnchantmentUtils.getShieldStrenghLevel(shield) >= 5) {
            this.infinite = true;
        }
        cooldown = DEModConfig.SHIELD_CONFIG.heavyShieldBaseCoodlwonOnGround.get().intValue() + 60 * EnchantmentUtils.getShieldStrenghLevel(shield) + 60;
    }

    @Override
    public void tick() {
        if (!infinite && !callback) {
            if (cooldown > 0) {
                cooldown--;
                if (cooldown <= 0) {
                    cooldown = 0;
                    this.entityDropItem(getShield());
                    this.remove();
                }
            }
        }
        if (getShield().isEmpty()) {
            this.remove();
        }
        if (callback) {
            this.noClip = true;
            Vector3d toOwner = new Vector3d(owner.getPosX() - this.getPosX(), owner.getPosYEye() - this.getPosY(), owner.getPosZ() - this.getPosZ());
            this.setRawPosition(this.getPosX(), this.getPosY() + toOwner.y * 0.03, this.getPosZ());
            double d0 = 0.2D;
            this.setMotion(this.getMotion().scale(0.95D).add(toOwner.normalize().scale(d0)));
            if (this.returningTicks == 0) {
                this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
            }
            ++this.returningTicks;

        }
        super.tick();
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        System.out.println("hi");
        if (!player.world.isRemote)
            System.out.println(isOwner(player));
        if (!world.isRemote && isOwner(player)){
            if(!addItemToInventory(player)){
                this.entityDropItem(getShield());
            }
            this.remove();
        }
        return super.processInitialInteract(player, hand);
    }

    public void callBack() {
        this.callback = true;
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
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }

            }
        }
    }

    @Override
    protected void registerData() {
        this.dataManager.register(SHIELD, ItemStack.EMPTY);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(SHIELD, ItemStack.read(compound));
        this.infinite = compound.getBoolean("infinite");
        this.callback = compound.getBoolean("callback");
        this.cooldown = compound.getInt("cooldown");
        this.owner = world.getPlayerByUuid(compound.getUniqueId("owner"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        getShield().write(compound);
        compound.putBoolean("infinite", this.infinite);
        compound.putBoolean("callback", this.callback);
        compound.putInt("cooldown", this.cooldown);
        compound.putUniqueId("owner", this.owner.getUniqueID());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isOwner(PlayerEntity player) {
        return this.owner == player;
    }

    public ItemStack getShield() {
        return this.dataManager.get(SHIELD);
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

    public static interface ItemStackCompare {
        public boolean areEqual(ItemStack first, ItemStack second);
    }
}
