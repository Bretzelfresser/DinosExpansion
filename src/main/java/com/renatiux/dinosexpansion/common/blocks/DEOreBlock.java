package com.renatiux.dinosexpansion.common.blocks;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Abs;

import java.util.Random;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class DEOreBlock extends Block {

    private int minXp = 0, maxXp = 0;

    public DEOreBlock(OreProperties properties) {
        super(properties.properties);
        this.maxXp = properties.maxXp;
        this.minXp = properties.minXp;
    }

    protected int getExperience(Random rand) {
      return MathHelper.nextInt(rand, minXp, maxXp);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }


    public static class OreProperties{
        public static OreProperties create(Material material){
            return new OreProperties(AbstractBlock.Properties.create(material));
        }

        public static OreProperties create(Material material, DyeColor color){
            return new OreProperties(AbstractBlock.Properties.create(material, color));
        }
        public static OreProperties create(Material materialIn, MaterialColor mapColorIn) {
            return new OreProperties(AbstractBlock.Properties.create(materialIn, mapColorIn));
        }

        public static OreProperties create(Material material, Function<BlockState, MaterialColor> stateColorFunction) {
            return new OreProperties(AbstractBlock.Properties.create(material, stateColorFunction));
        }

        public static OreProperties from(AbstractBlock block){
            return new OreProperties(AbstractBlock.Properties.from(block));
        }

        private final AbstractBlock.Properties properties;
        private int maxXp = 0, minXp = 0;

        protected OreProperties(AbstractBlock.Properties properties){
            this.properties = properties;
        }

        public OreProperties setXp(int minXp, int maxXp){
            this.maxXp = maxXp;
            this.minXp = minXp;
            return this;
        }

        public OreProperties doesNotBlockMovement() {
            properties.doesNotBlockMovement();
            return this;
        }

        public OreProperties notSolid(){
            properties.notSolid();
            return this;
        }

        public OreProperties harvestLevel(int harvestLevel) {
            this.properties.harvestLevel(harvestLevel);
            return this;
        }

        public OreProperties harvestTool(net.minecraftforge.common.ToolType harvestTool) {
            properties.harvestTool(harvestTool);
            return this;
        }
        public OreProperties slipperiness(float slipperinessIn) {
            properties.slipperiness(slipperinessIn);
            return this;
        }
        public OreProperties speedFactor(float factor) {
            properties.speedFactor(factor);
            return this;
        }
        public OreProperties jumpFactor(float factor) {
            properties.jumpFactor(factor);
            return this;
        }
        public OreProperties sound(SoundType soundTypeIn) {
            properties.sound(soundTypeIn);
            return this;
        }

        public OreProperties setLightLevel(ToIntFunction<BlockState> stateLightFunction) {
            properties.setLightLevel(stateLightFunction);
            return this;
        }
        public OreProperties hardnessAndResistance(float hardnessIn, float resistanceIn) {
            properties.hardnessAndResistance(hardnessIn, resistanceIn);
            return this;
        }
        public OreProperties hardnessAndResistance(float hardnessAndResistance) {
            return hardnessAndResistance(hardnessAndResistance, hardnessAndResistance);
        }
        public OreProperties zeroHardnessAndResistance(){
            return hardnessAndResistance(0.0f);
        }
        public OreProperties tickRandomly(){
            properties.tickRandomly();
            return this;
        }
        public OreProperties variableOpacity(){
            properties.variableOpacity();
            return this;
        }
        public OreProperties noDrops() {
            properties.noDrops();
            return this;
        }
        public OreProperties lootFrom(java.util.function.Supplier<? extends Block> blockIn) {
            properties.lootFrom(blockIn);
            return this;
        }
        public OreProperties setAir(){
            properties.setAir();
            return this;
        }
        public OreProperties setAllowsSpawn(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> spawnPredicate) {
            properties.setAllowsSpawn(spawnPredicate);
            return this;
        }
        public OreProperties setOpaque(AbstractBlock.IPositionPredicate opaquePredicate) {
            properties.setOpaque(opaquePredicate);
            return this;
        }
        public OreProperties setSuffocates(AbstractBlock.IPositionPredicate suffocatesPredicate) {
            properties.setSuffocates(suffocatesPredicate);
            return this;
        }
        public OreProperties setBlocksVision(AbstractBlock.IPositionPredicate blocksVisionPredicate) {
            properties.setBlocksVision(blocksVisionPredicate);
            return this;
        }
        public OreProperties setNeedsPostProcessing(AbstractBlock.IPositionPredicate postProcessingPredicate) {
            properties.setNeedsPostProcessing(postProcessingPredicate);
            return this;
        }
        public OreProperties setEmmisiveRendering(AbstractBlock.IPositionPredicate emmisiveRenderPredicate) {
            properties.setEmmisiveRendering(emmisiveRenderPredicate);
            return this;
        }
        public OreProperties setRequiresTool() {
            properties.setRequiresTool();
            return this;
        }

        public Properties getProperties() {
            return properties;
        }

        public int getMaxXp() {
            return maxXp;
        }

        public int getMinXp() {
            return minXp;
        }
    }


}
