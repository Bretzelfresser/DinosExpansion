package com.renatiux.dinosexpansion.util.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.ResearchTableRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ResearchTableRecipeBuilder {

    public static ResearchTableRecipeBuilder create(IItemProvider inputs){
        return new ResearchTableRecipeBuilder(Ingredient.fromItems(inputs));
    }

    public static ResearchTableRecipeBuilder create(ITag<Item> inputs){
        return new ResearchTableRecipeBuilder(Ingredient.fromTag(inputs));
    }

    private Ingredient input, output;
    private int timeNeeded = 200;
    private float xp = 0;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();

    private ResearchTableRecipeBuilder(Ingredient input){
        this.input = input;
    }

    public ResearchTableRecipeBuilder setOutput(Ingredient output){
        if (this.output == null){
            this.output = output;
        }
        return this;
    }

    public ResearchTableRecipeBuilder setOutput(ITag<Item> output){
        return setOutput(Ingredient.fromTag(output));
    }
    public ResearchTableRecipeBuilder setProcessingTime(int time){
        if (time <= 0)
            throw new IllegalArgumentException("time has to be greater then zero time is: " + time);
        this.timeNeeded = time;
        return this;
    }

    public ResearchTableRecipeBuilder setXp(float xp){
        if (xp < 0){
            throw new IllegalArgumentException("this recipe must at least give 0 experience there are no negative values");
        }
        this.xp = xp;
        return this;
    }

    public ResearchTableRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}. automatically put the {@link com.renatiux.dinosexpansion.Dinosexpansion#MODID} as namspace and the save as path
     * the result.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        this.build(consumerIn, Dinosexpansion.modLoc(save));
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        if (this.advancementBuilder != null) {
            this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
            consumerIn.accept(new Result(id, this.input, this.output, this.timeNeeded, this.xp, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/misc/" + id.getPath())));
        }else{
            consumerIn.accept(new Result(id, this.input, this.output, this.timeNeeded, this.xp));
        }
    }

    private static class Result implements IFinishedRecipe{

        private final Ingredient input, output;
        private final int neededTomeToProcess;
        private final float xp;
        private final ResourceLocation id;
        private Advancement.Builder advancementBuilder;
        private ResourceLocation advancementId;

        private Result(ResourceLocation id, Ingredient input, Ingredient output, int neededTomeToProcess, float xp) {
            this.input = input;
            this.output = output;
            this.neededTomeToProcess = neededTomeToProcess;
            this.xp = xp;
            this.id = id;
        }

        private Result(ResourceLocation id, Ingredient input, Ingredient output, int neededTomeToProcess, float xp, Advancement.Builder advancementBuilder, ResourceLocation advancementId) {
            this(id, input, output, neededTomeToProcess, xp);
            this.advancementBuilder = advancementBuilder;
            this.advancementId = advancementId;
        }


        @Override
        public void serialize(JsonObject json) {
            JsonElement input = this.input.serialize();
            json.add("input", input);
            JsonElement output = this.output.serialize();
            json.add("output", output);
            if (this.neededTomeToProcess != 200 && this.neededTomeToProcess > 0)
                json.addProperty("worktime", this.neededTomeToProcess);
            if (this.xp != 0 && xp > 0)
                json.addProperty("experience", this.xp);
        }

        @Override
        public ResourceLocation getID() {
            return this.id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return ResearchTableRecipe.SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject getAdvancementJson() {
            return advancementBuilder == null ? null : advancementBuilder.serialize();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementID() {
            return this.advancementId;
        }
    }
}
