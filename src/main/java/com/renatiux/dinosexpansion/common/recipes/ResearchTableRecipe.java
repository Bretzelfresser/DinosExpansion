package com.renatiux.dinosexpansion.common.recipes;

import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.common.tileEntities.ResearchTableTileEntity;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Random;

public class ResearchTableRecipe implements IRecipe<ResearchTableTileEntity> {

    public static final Serializer SERIALIZER = new Serializer();
    private final Ingredient input, output;
    private final ResourceLocation id;
    private final int neededTime;
    private final float xp;

    public ResearchTableRecipe(Ingredient input, Ingredient output, ResourceLocation id, int neededTime, float xp) {
        this.input = input;
        this.output = output;
        this.id = id;
        this.neededTime = neededTime;
        this.xp = xp;
    }

    @Override
    public boolean matches(ResearchTableTileEntity inv, World worldIn) {
        //defines whether the current te with its ingredients can be applied with this recipe
        return input.test(inv.getStackInSlot(0));
    }

    /**
     * this will get u the output, keep in mind that this will get a random item from a tag, so if u call it twice it may give u a differen value
     */
    @Override
    public ItemStack getCraftingResult(ResearchTableTileEntity inv) {
        return getRecipeOutput();
    }

    @Override
    public boolean canFit(int width, int height) {
        //not important just for crafting recipes in case u want to make a 4x4 crafting table
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(this.input);
    }

    /**
     * this will get u the output, keep in mind that this will get a random item from a tag, so if u call it twice it may give u a differen value
     */
    @Override
    public ItemStack getRecipeOutput() {
        ItemStack[] outputs = output.getMatchingStacks();
        if (outputs.length == 1){
            return outputs[0];
        }
        return outputs[new Random().nextInt(outputs.length)].copy();
    }

    public Ingredient getOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    public float getXp() {
        return xp;
    }

    public int getNeededTime() {
        return neededTime;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeInit.RESEARCH_TABLE_RECIPE;
    }


    private static final class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ResearchTableRecipe>{

        public Serializer(){
            /** this must match the same as in the {@link ResearchTableRecipeType#toString} */
            setRegistryName("research_recipe");
        }

        @Override
        public ResearchTableRecipe read(ResourceLocation recipeId, JsonObject json) {
            //here u define how this should be serialized from a json
            Ingredient input = Ingredient.deserialize(MortarRecipe.getJsonElement(json, "input"));
            Ingredient output = Ingredient.deserialize(MortarRecipe.getJsonElement(json, "output"));
            int timeNeeded = JSONUtils.getInt(json, "worktime", 200);
            float experience = JSONUtils.getFloat(json, "experience", 0);
            return new ResearchTableRecipe(input, output, recipeId, timeNeeded, experience);
        }

        @Nullable
        @Override
        public ResearchTableRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            //this should read from the buffer, must be in the same order like u write it in the write method
            Ingredient input = Ingredient.read(buffer);
            Ingredient output = Ingredient.read(buffer);
            int timeNeeded = buffer.readInt();
            float xp = buffer.readFloat();
            return new ResearchTableRecipe(input, output, recipeId, timeNeeded, xp);
        }

        @Override
        public void write(PacketBuffer buffer, ResearchTableRecipe recipe) {
            recipe.input.write(buffer);
            recipe.output.write(buffer);
            buffer.writeInt(recipe.neededTime);
            buffer.writeFloat(recipe.xp);
        }
    }
}
