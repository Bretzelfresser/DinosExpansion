package com.renatiux.dinosexpansion.util.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
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
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MortarRecipeBuilder {

    private final Item output;
    private final int count, workTimeInTicks;
    private int countInput1, countInput2;
    private Ingredient input1, input2;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();

    public static MortarRecipeBuilder recipe(IItemProvider item, int count, int workTimeInTicks) {return new MortarRecipeBuilder(item, count, workTimeInTicks);}
    public static MortarRecipeBuilder recipe(IItemProvider item, int workTimeInTicks){ return recipe(item, 1, workTimeInTicks);}
    public static MortarRecipeBuilder recipe(IItemProvider item){return recipe(item, 200);}

    protected MortarRecipeBuilder(IItemProvider item, int count, int workTimeInTicks){
        this.output = item.asItem();
        this.count = count;
        this.workTimeInTicks = workTimeInTicks;
    }
    public MortarRecipeBuilder addInput(IItemProvider input, int count){
        return addInput(Ingredient.fromItems(input), count);
    }

    public MortarRecipeBuilder addInput(IItemProvider input){
        return addInput(Ingredient.fromItems(input), 1);
    }

    public MortarRecipeBuilder addInput(ITag<Item> input, int count){
        return addInput(Ingredient.fromTag(input), count);
    }
    public MortarRecipeBuilder addInput(ITag<Item> input){
        return addInput(Ingredient.fromTag(input), 1);
    }

    public MortarRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }


    public MortarRecipeBuilder addInput(Ingredient input, int count){
        if (input1 == null) {
            this.input1 = input;
            this.countInput1 = count;
            return this;
        }else if(input2 == null){
            this.input2 = input;
            this.countInput2 = count;
            return this;
        }
        throw new IllegalArgumentException("cant add more than 2 inputs");
    }

    public void build(Consumer<IFinishedRecipe> consumerIn) {
        this.build(consumerIn, ForgeRegistries.ITEMS.getKey(this.output));
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}. Use {@link #build(Consumer)} if save is the same as the ID for
     * the result.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.output);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Mortar Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        if (this.advancementBuilder != null) {
            this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
            consumerIn.accept(new MortarRecipeBuilder.Result(id, this.input1, this.input2, this.count, this.countInput1, this.countInput2, this.workTimeInTicks,this.output, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.output.getGroup().getPath() + "/" + id.getPath())));
        }else{
            consumerIn.accept(new MortarRecipeBuilder.Result(id, this.input1, this.input2, this.count, this.countInput1, this.countInput2, this.workTimeInTicks,this.output));
        }
    }


    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient input1, input2;
        private final int resultCount, countInput1, countInput2, workTime;
        private final Item result;
        private Advancement.Builder advancementBuilder;
        private ResourceLocation advancementId;

        public Result(ResourceLocation id, Ingredient input1, Ingredient input2, int resultCount, int countInput1, int countInput2, int workTime, Item result){
            this.id = id;
            this.input1 = input1;
            this.input2 = input2;
            this.resultCount = resultCount;
            this.countInput1 = countInput1;
            this.countInput2 = countInput2;
            this.workTime = workTime;
            this.result = result;
        }

        public Result(ResourceLocation id, Ingredient input1, Ingredient input2, int resultCount, int countInput1, int countInput2,int workTime, Item result, Advancement.Builder builder, ResourceLocation advancementId){
           this(id, input1, input2, resultCount, countInput1, countInput2, workTime, result);
            this.advancementBuilder = builder;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonObject input1 = this.input1.serialize().getAsJsonObject();
            if (this.countInput1 > 1)
                input1.addProperty("count", this.countInput1);
            json.add("input1", input1);
            JsonObject input2 = this.input2.serialize().getAsJsonObject();
            if (this.countInput2 > 1)
                input2.addProperty("count", this.countInput2);
            json.add("input2", input2);
            JsonObject result = new JsonObject();
            result.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.resultCount > 1)
                result.addProperty("count", this.resultCount);
            json.add("output", result);
            json.addProperty("workTime", this.workTime);

        }

        @Override
        public ResourceLocation getID() {
            return this.id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return MortarRecipe.SERIALIZER;
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
