package com.renatiux.dinosexpansion.common.entities.environment;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.controller.InEarthPathNavigator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.particle.DiggingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.NoteBlockEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class Xenocranium extends CreatureEntity implements IAnimatable {

    protected static final DataParameter<Boolean> DIGGING = EntityDataManager.createKey(Xenocranium.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> CAN_COME_UP = EntityDataManager.createKey(Xenocranium.class, DataSerializers.BOOLEAN);

    public static final String CONTROLLER_NAME = "controller";
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
            Dinosexpansion.modLoc("textures/entity/environment/xenocranium/xenocranium1.png"),
            Dinosexpansion.modLoc("textures/entity/environment/xenocranium/xenocranium2.png"),
            Dinosexpansion.modLoc("textures/entity/environment/xenocranium/xenocranium3.png")};

    protected final ResourceLocation texture;

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return CreatureEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 5d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.5d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3d);
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @OnlyIn(Dist.CLIENT)
    private int digdownAnimationCooldown = 0, digUpAnimationCooldown = 0;

    public Xenocranium(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        texture = chooseRandom();
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (world.isRemote) {
            if (this.digdownAnimationCooldown > 0)
                this.digdownAnimationCooldown--;
            if (this.digUpAnimationCooldown > 0)
                this.digUpAnimationCooldown--;
        }
    }

    private ResourceLocation chooseRandom() {
        return TEXTURES[this.world.rand.nextInt(TEXTURES.length)];
    }

    /**
     * synced with the client, returns the path to the current used texture
     */
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return texture;
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnDirtParticles() {
        for (int i = 0; i < 10; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            BlockPos diggingBlock = new BlockPos(0, 0, 0);
            for (BlockPos pos = this.getPosition(); !this.world.isAirBlock(pos); pos = pos.down()) {
                diggingBlock = pos;
            }
            this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, this.world.getBlockState(diggingBlock)).setPos(diggingBlock),
                    this.getPosX() + ((double) this.rand.nextFloat() - 0.5D) * (double) this.getWidth(),
                    this.getPosY() + 0.1D,
                    this.getPosZ() + ((double) this.rand.nextFloat() - 0.5D) * (double) this.getWidth(),
                    4.0D * ((double) this.rand.nextFloat() - 0.5D),
                    0.5D,
                    ((double) this.rand.nextFloat() - 0.5D) * 4.0D);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void digDown() {
        this.digdownAnimationCooldown = 22;
        spawnDirtParticles();
    }

    @OnlyIn(Dist.CLIENT)
    private void digUp() {
        this.digUpAnimationCooldown = 30;
        spawnDirtParticles();
    }

    public boolean isInEarth(){
        return this.world.getBlockState(this.getPosition()).isIn(Tags.Blocks.DIRT);
    }

    private void switchNavigator(boolean digging) {
        if (digging) {
            this.navigator = new InEarthPathNavigator(this, world);
        } else {
            this.navigator = new GroundPathNavigator(this, world);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DiggingGoal(this));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new XenocraniumMoveRandomlyGoal(this, 1));
        this.goalSelector.addGoal(1, new DigUpGoal(this));

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(DIGGING, compound.getBoolean("digging"));
        this.dataManager.set(CAN_COME_UP, compound.getBoolean("can_come_up"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("digging", this.isDigging());
        compound.putBoolean("can_come_up", this.canComeUp());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DIGGING, false);
        this.dataManager.register(CAN_COME_UP, false);
    }

    public boolean canComeUp() {
        return this.dataManager.get(CAN_COME_UP);
    }

    protected void setComeUp(boolean comeUp) {
        this.dataManager.set(CAN_COME_UP, canComeUp());
    }

    @Override
    public boolean hasNoGravity() {
        return this.isDigging();
    }

    public boolean isDigging() {
        return this.dataManager.get(DIGGING);
    }

    public void setDigging(boolean digging) {
        if (isDigging() != digging) {
            this.dataManager.set(DIGGING, digging);
            this.noClip = digging;
            if (digging) {
                this.world.setEntityState(this, (byte) 7);
            } else {
                this.world.setEntityState(this, (byte) 8);
            }

            switchNavigator(digging);
        }

    }

    private ResourceLocation setInitialTexture() {
        int index = this.rand.nextInt(TEXTURES.length);
        return TEXTURES[index];
    }

    private PlayState predicate(AnimationEvent<Xenocranium> event) {
        if (this.digdownAnimationCooldown > 0) {
            if (event.getController().getCurrentAnimation() != null && !event.getController().getCurrentAnimation().animationName.equals("new.xenocranium.digdown.new"))
                event.getController().setAnimation(new AnimationBuilder().addAnimation("new.xenocranium.digdown.new", false));
            System.out.println("should play DigDown");
            return PlayState.CONTINUE;
        }
        if(this.digUpAnimationCooldown > 0){
            if (event.getController().getCurrentAnimation() != null && !event.getController().getCurrentAnimation().animationName.equals("Xenocranium_DigUp.new")){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Xenocranium_DigUp.new", false));
            }
            System.out.println("should play Dig Up");
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            if (isDigging()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("new.xenocranium.digfront", false));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Xenocranium_Walk.new", false));
            }
            return PlayState.CONTINUE;
        }


        return PlayState.CONTINUE;
    }


    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 7) {
            digDown();
        } else if (id == 8) {
            digUp();
        } else
            super.handleStatusUpdate(id);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private static class DiggingGoal extends Goal {

        private final Xenocranium owner;
        private final World world;
        private int upwardsCooldown = 0;

        public DiggingGoal(Xenocranium owner) {
            this.owner = owner;
            this.world = owner.world;
        }

        @Override
        public boolean shouldExecute() {
            boolean first = (this.world.isAirBlock(this.owner.getPosition()) && this.hostileCreaturesNearby()) || this.world.isNightTime();
            return first && !this.owner.isDigging();
        }

        private boolean hostileCreaturesNearby() {
            List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.owner.getBoundingBox().grow(5), livingEntity -> true);
            return list.size() > 0;
        }

        @Override
        public void startExecuting() {
            this.owner.setDigging(true);
            System.out.println("startedDiggingDown");
            this.owner.setComeUp(false);
            this.upwardsCooldown = 1200;
        }

        @Override
        public void tick() {
            if (!world.isRemote) {
                if (this.upwardsCooldown > 0 && !this.world.isNightTime())
                    this.upwardsCooldown--;
                if (this.upwardsCooldown == 0) {
                    this.owner.setComeUp(true);
                }
            }
        }
    }

    private static class DigUpGoal extends Goal{

        private final Xenocranium owner;
        private final World world;

        public DigUpGoal(Xenocranium owner){
            this.owner = owner;
            world = owner.world;
        }
        @Override
        public boolean shouldExecute() {
            return this.owner.canComeUp() && this.owner.isDigging();
        }

        @Override
        public void tick() {
            this.tryDigUp();
        }

        private void tryDigUp(){
            if (!this.world.isAirBlock(this.owner.getPosition().up())) {
                BlockPos p = this.owner.getPosition().up();
                this.owner.move(MoverType.SELF, new Vector3d(p.getX(), p.getY(), p.getZ()));
            }
            else{
                this.owner.setDigging(false);
            }
        }


    }

    private static final class XenocraniumMoveRandomlyGoal extends RandomWalkingGoal {

        private final Xenocranium owner;

        public XenocraniumMoveRandomlyGoal(Xenocranium creatureIn, double speedIn) {
            super(creatureIn, speedIn);
            this.owner = creatureIn;
        }

        @Override
        public void startExecuting() {
            if (!this.owner.isDigging())
                super.startExecuting();
            this.owner.move(MoverType.SELF, new Vector3d(this.x, this.y, this.z));
        }

        @Override
        public void resetTask() {
            super.resetTask();
            this.owner.setMotion(Vector3d.ZERO);
        }
    }
}
