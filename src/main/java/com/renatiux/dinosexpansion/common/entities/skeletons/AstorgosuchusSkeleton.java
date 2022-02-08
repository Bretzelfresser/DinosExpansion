package com.renatiux.dinosexpansion.common.entities.skeletons;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.entities.creatures.Astorgosuchus.*;
import com.renatiux.dinosexpansion.common.container.AstorgosuchusPoseContainer;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import javax.xml.crypto.Data;
import java.util.ArrayList;

public class AstorgosuchusSkeleton extends Entity{

    private static final DataParameter<Integer> MODEL = EntityDataManager.createKey(AstorgosuchusSkeleton.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> DARK = EntityDataManager.createKey(AstorgosuchusSkeleton.class, DataSerializers.BOOLEAN);
    private static final ArrayList<EntityModel<?>> MODELS = Lists.newArrayList(new AstorgosuchusAdultSkeleton1(), new AstorgosuchusAdultSkeleton2(), new AstorgosuchusAdultSkeleton3(), new AstorgosuchusAdultSkeleton4(), new AstorgosuchusAdultSkeleton5(), new AstorgosuchusAdultSkeleton6(), new AstorgosuchusAdultSkeleton7(), new AstorgosuchusAdultSkeleton8(), new AstorgosuchusAdultSkeleton9(), new AstorgosuchusAdultSkeleton10());

    public AstorgosuchusSkeleton(EntityType<?> type, World world) {
        this(type, world, true);

    }

    public AstorgosuchusSkeleton(EntityType<?> type, World world, boolean dark) {
        super(type, world);
        this.dataManager.set(DARK, dark);

    }

    private AstorgosuchusSkeleton(EntityType<?> type, World world, int model, boolean dark){
        super(type, world);
        if (model < 0 || model >= MODELS.size())
            throw new IllegalArgumentException("model number has to be between 0 and " + (MODELS.size() - 1) + " not: " + model);
        setModel(model);
        this.dataManager.set(DARK, dark);
    }

    private AstorgosuchusSkeleton(EntityType<?> type, World world, EntityModel<?> model, boolean isDark){
        super(type, world);
        if (!MODELS.contains(model))
            throw new IllegalArgumentException("model has to be in MODELS which this model isnt: " + model);
        setModel(model);
        this.dataManager.set(DARK, isDark);
    }

    public AstorgosuchusSkeleton with(int model){
        return new AstorgosuchusSkeleton(getType(), world, model, isDark());
    }
    public AstorgosuchusSkeleton with(EntityModel<?> model){
        return new AstorgosuchusSkeleton(getType(), world, model, isDark());
    }

    public boolean isDark(){
        return this.dataManager.get(DARK);
    }


    @Override
    protected void registerData() {
        this.dataManager.register(MODEL, 0);
        this.dataManager.register(DARK, false);
    }

    /**
     * synced with the client
     * @return the model this entity is using
     */
    public EntityModel<?> getModel(){
        return MODELS.get(this.dataManager.get(MODEL));
    }

    /**
     * @return the id 4 the model
     */
    public int getModelId(){
        return this.dataManager.get(MODEL);
    }

    public void setModel(EntityModel<?> model){
        this.dataManager.set(MODEL, MODELS.indexOf(model));
    }

    public void setModel(int modelNumer){
        if (modelNumer < 0 || modelNumer >= MODELS.size())
            throw new IllegalArgumentException("model number has to be between 0 and " + (MODELS.size() - 1) + " not: " + modelNumer);
        this.dataManager.set(MODEL, modelNumer);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(MODEL, compound.getInt("model"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("model", this.dataManager.get(MODEL));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d hit, Hand hand) {
        if (!world.isRemote){
            System.out.println("right clicked");
            ItemStack heldItem = player.getHeldItem(hand);
            if (getRightClickItem().test(heldItem)){
                NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent( Dinosexpansion.MODID + "astorgosuchus.container");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
                        return getContainer(id, inv);
                    }
                }, buf -> buf.writeVarInt(getEntityId()));
            }
        }
        return super.applyPlayerInteraction(player, hit, hand);
    }

    private Container getContainer(int id, PlayerInventory playerInv){
        return new AstorgosuchusPoseContainer(id, playerInv, this);
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
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

    protected Ingredient getRightClickItem(){
        return Ingredient.fromTag(Tags.Items.PALEONTOLOGIC_TOOLS);
    }

    @Override
    protected Vector3d func_241839_a(Direction.Axis axis, TeleportationRepositioner.Result result) {
        return LivingEntity.func_242288_h(super.func_241839_a(axis, result));
    }

}
