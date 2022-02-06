package com.renatiux.dinosexpansion.util.datagen;

import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.GeneratorRecipe;
import net.minecraft.advancements.AdvancementRewards;
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

public class GeneratorRecipeBuilder {

    private final Ingredient item;
    private final int count;
    private int processTime = 200, energy = 300;
    private ResourceLocation id;

    /**
     *
     * @param item - the item that should be processed in the generator
     */
    public static final GeneratorRecipeBuilder recipe(IItemProvider item, int count){
        return recipe(Ingredient.fromItems(item), count);
    }

    public static final GeneratorRecipeBuilder recipe(ITag.INamedTag<Item> item){
        return recipe(Ingredient.fromTag(item), 1).setID(item.getName());
    }

    private static final GeneratorRecipeBuilder recipe(Ingredient item, int count){
        return new GeneratorRecipeBuilder(item, count);
    }

    public static final GeneratorRecipeBuilder recipe(ITag.INamedTag<Item> item, int count){
        return recipe(Ingredient.fromTag(item), count).setID(item.getName());
    }


    /**
     *
     *this will generate a recipe that gives u the amount of energy with this item in the generator after 200 ticks
     */
    public static final GeneratorRecipeBuilder recipe(IItemProvider item){
        return recipe(Ingredient.fromItems(item), 1);
    }

    protected GeneratorRecipeBuilder(Ingredient item, int count){
        this.item = item;
        this.count = count;
    }

    private GeneratorRecipeBuilder setID(ResourceLocation id){
        this.id = id;
        return this;
    }

    public GeneratorRecipeBuilder energy(int energy){
        this.energy = energy;
        return this;
    }

    public GeneratorRecipeBuilder processTime(int processTime){
        this.processTime = processTime;
        return this;
    }

    public void build(Consumer<IFinishedRecipe> consumerIn) {
        if (id == null)
            this.build(consumerIn, Dinosexpansion.modLoc("generator/" + ForgeRegistries.ITEMS.getKey(this.item.getMatchingStacks()[0].getItem()).getPath()));
        else
            this.build(consumerIn, Dinosexpansion.modLoc("generator/" + id.getPath()));
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}. Use {@link #build(Consumer)} if save is the same as the ID for
     * the result.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.item.getMatchingStacks()[0].getItem());
        if ((new ResourceLocation(save)).equals(resourcelocation) || new ResourceLocation(save).equals(this.id)) {
            throw new IllegalStateException("Generator Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        consumerIn.accept(new GeneratorRecipeBuilder.Result(id, this.energy, this.processTime,this.count, this.item));
    }

    public class Result implements IFinishedRecipe{

        private final ResourceLocation id;
        private final int energy, processTime, count;
        private final Ingredient input;

        public Result(ResourceLocation id, int energy, int processTime, int count, Ingredient input) {
            this.id = id;
            this.energy = energy;
            this.processTime = processTime;
            this.count = count;
            this.input = input;
        }


        @Override
        public void serialize(JsonObject json) {
            JsonObject input = this.input.serialize().getAsJsonObject();
            if (this.count > 1)
                input.addProperty("count", this.count);
            json.add("ingredient", input);
            json.addProperty("energy", this.energy);
            json.addProperty("processTime", this.processTime);

        }

        @Override
        public ResourceLocation getID() {
            return this.id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return GeneratorRecipe.SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject getAdvancementJson() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementID() {
            return null;
        }
    }
}
