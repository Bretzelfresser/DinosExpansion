package com.renatiux.dinosexpansion.datagen.server;

import java.util.function.Consumer;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import com.renatiux.dinosexpansion.util.datagen.AdvancedSmithingTableRecipeBuilder;
import com.renatiux.dinosexpansion.util.datagen.GeneratorRecipeBuilder;
import com.renatiux.dinosexpansion.util.datagen.MortarRecipeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        machineRecipes(consumer);
        shieldBoomerangRecipes(consumer);
        generator(consumer);
        addTools(consumer);
        //shapeless recipe, the criterion determines when the recipe is unlocked in the recipe book, there has to be one
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.TRANQUILIZER_ARROW.get()).addIngredient(Items.ARROW).addIngredient(ItemInit.NARCOTICS.get()).addCriterion("hasItem", hasItem(ItemInit.NARCOTICS.get())).build(consumer);
        MortarRecipeBuilder.recipe(ItemInit.NARCOTICS.get(), 300).addInput(Items.ROTTEN_FLESH).addInput(ItemInit.NARCOTIC_BERRY.get(), 3).addCriterion("hasItem", hasItem(ItemInit.NARCOTIC_BERRY.get())).build(consumer);


        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 200).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_smelting"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 100).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_blasting"));

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 200).addCriterion("has_purple_gem_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_smelting"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 100).addCriterion("has_purple_gem_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_blasting"));

    }

    private void generator(Consumer<IFinishedRecipe> consumer) {
        GeneratorRecipeBuilder.recipe(Items.COAL_BLOCK).energy(5000).processTime(800).build(consumer);
        GeneratorRecipeBuilder.recipe(Items.COAL).energy(500).processTime(100).build(consumer);
        GeneratorRecipeBuilder.recipe(Items.CHARCOAL).energy(500).processTime(100).build(consumer);
        GeneratorRecipeBuilder.recipe(Items.MAGMA_BLOCK).energy(4000).processTime(700).build(consumer);
        GeneratorRecipeBuilder.recipe(Items.MAGMA_CREAM).energy(400).processTime(70).build(consumer);
        GeneratorRecipeBuilder.recipe(ItemTags.LOGS).energy(200).processTime(50).build(consumer);
        GeneratorRecipeBuilder.recipe(ItemTags.PLANKS).energy(50).processTime(13).build(consumer);
        GeneratorRecipeBuilder.recipe(Tags.Items.RODS_WOODEN).energy(25).processTime(7).build(consumer);
        GeneratorRecipeBuilder.recipe(com.renatiux.dinosexpansion.core.tags.Tags.Items.WOOD_TOOLS).energy(200).processTime((50)).build(consumer);
        GeneratorRecipeBuilder.recipe(Items.LAVA_BUCKET).energy(7000).processTime(1000).build(consumer);
    }

    private void machineRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(BlockInit.ADVANCED_SMITHING_TABLE.getPrimary())
                .key('x', ItemTags.PLANKS)
                .key('a', Items.CRAFTING_TABLE)
                .key('b', Items.ANVIL)
                .patternLine("xxx")
                .patternLine("bab")
                .patternLine("xxx")
                .addCriterion("hasItem", hasItem(Items.CRAFTING_TABLE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.BASIC_ENERGY_CABLE.getPrimary(), 6)
                .key('i', Items.IRON_INGOT)
                .key('w', ItemTags.WOOL)
                .patternLine("www")
                .patternLine("iii")
                .patternLine("www")
                .addCriterion("hasItem", hasItem(ItemTags.WOOL)).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(BlockInit.INCUBATOR.get().asItem())
                .key('g', Items.GOLD_BLOCK)
                .key('h', Items.HAY_BLOCK)
                .key('t', Items.GLASS)
                .key('r', Items.REDSTONE)
                .patternLine("tttt")
                .patternLine("thht")
                .patternLine("grrg")
                .patternLine("gggg")
                .addCriterion("hasItem", hasItem(Items.GOLD_BLOCK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.GENERATOR.getPrimary())
                .key('f', Items.FURNACE)
                .key('r', Items.REDSTONE)
                .key('i', Items.IRON_INGOT)
                .patternLine("iii")
                .patternLine("rfr")
                .patternLine("iii")
                .addCriterion("hasItem", hasItem(Items.FURNACE)).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(BlockInit.MORTAR.get().asItem())
                .key('s', Items.STONE)
                .key('l', Items.STICK)
                .patternLine("  l")
                .patternLine("sls")
                .patternLine("sss")
                .addCriterion("hasItem", hasItem(Items.STICK)).build(consumer);
    }

    private void addTools(Consumer<IFinishedRecipe> consumer){
        addToolsForMaterial(Items.EMERALD, ItemInit.EMERALD_PICKAXE.get(), ItemInit.EMERALD_AXE.get(), ItemInit.EMERALD_SWORD.get(), ItemInit.EMERALD_HOE.get(), ItemInit.EMERALD_SHOVEL.get(), consumer);
    }

    private void addToolsForMaterial(Item material, Item pickaxe, Item axe, Item sword, Item hoe, Item shovel, Consumer<IFinishedRecipe> consumer) {
        if (pickaxe != null)
            addPickaxeRecipe(material, pickaxe, consumer);
        if (hoe != null)
            addHoeRecipe(material, hoe, consumer);
        if (axe != null)
            addAxeRecipe(material, axe, consumer);
        if (shovel != null)
            addShovelRecipe(material, shovel, consumer);
        if (sword != null)
            addSwordRecipe(material, sword, consumer);
    }

    private void shieldBoomerangRecipes(Consumer<IFinishedRecipe> consumer) {
        AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.HULLBREAKER.get())
                .key('s', Items.SHIELD)
                .key('p', Items.PISTON)
                .key('i', Items.IRON_INGOT)
                .key('w', ItemTags.PLANKS)
                .patternLine("ippi")
                .patternLine("issi")
                .patternLine("wwww")
                .addCriterion("hasItem", hasItem(Items.SHIELD)).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.HEAVY_SHIELD.get())
                .key('h', ItemInit.HULLBREAKER.get())
                .key('i', Items.IRON_BLOCK)
                .key('p', Items.PISTON)
                .patternLine("iiii")
                .patternLine("ippi")
                .patternLine("ihhi")
                .patternLine("iiii")
                .addCriterion("hasItem", hasItem(ItemInit.HULLBREAKER.get())).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.SPIKES_SHIELD.get())
                .key('s', Items.SHIELD)
                .key('b', ItemInit.IRON_BOOMERANG.get())
                .key('i', Items.IRON_INGOT)
                .key('w', ItemTags.PLANKS)
                .patternLine("iwwi")
                .patternLine("wsbw")
                .patternLine("wiiw")
                .patternLine("iwwi")
                .addCriterion("hasItem", hasItem(Items.SHIELD)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.WOOD_BOOMERANG.get())
                .key('s', Items.STICK)
                .key('f', Items.FEATHER)
                .patternLine(" s ")
                .patternLine("s s")
                .patternLine("f f")
                .addCriterion("hasItem", hasItem(Items.STICK)).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.IRON_BOOMERANG.get())
                .key('b', ItemInit.WOOD_BOOMERANG.get())
                .key('i', Items.IRON_INGOT)
                .patternLine("iii")
                .patternLine("ibi")
                .patternLine("iii")
                .addCriterion("hasItem", hasItem(ItemInit.WOOD_BOOMERANG.get())).build(consumer);
        AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.DIAMOND_BOOMERANG.get())
                .key('d', Items.DIAMOND)
                .key('b', ItemInit.IRON_BOOMERANG.get())
                .patternLine("ddd")
                .patternLine("dbd")
                .patternLine("ddd")
                .addCriterion("hasItem", hasItem(ItemInit.IRON_BOOMERANG.get())).build(consumer);
    }


    private void addPickaxeRecipe(Item toolMaterial, Item pickaxe, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(pickaxe)
                .key('t', toolMaterial)
                .key('s', Tags.Items.RODS_WOODEN)
                .patternLine("ttt")
                .patternLine(" s ")
                .patternLine(" s ")
                .addCriterion("hasItem", hasItem(toolMaterial)).build(consumer);
    }

    private void addAxeRecipe(Item toolMaterial, Item axe, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(axe)
                .key('t', toolMaterial)
                .key('s', Tags.Items.RODS_WOODEN)
                .patternLine("tt ")
                .patternLine("ts ")
                .patternLine(" s ")
                .addCriterion("hasItem", hasItem(toolMaterial)).build(consumer);
    }

    private void addSwordRecipe(Item toolMaterial, Item sword, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(sword)
                .key('t', toolMaterial)
                .key('s', Tags.Items.RODS_WOODEN)
                .patternLine("t")
                .patternLine("t")
                .patternLine("s")
                .addCriterion("hasItem", hasItem(toolMaterial)).build(consumer);
    }

    private void addShovelRecipe(Item toolMaterial, Item shovel, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(shovel)
                .key('t', toolMaterial)
                .key('s', Tags.Items.RODS_WOODEN)
                .patternLine("t")
                .patternLine("s")
                .patternLine("s")
                .addCriterion("hasItem", hasItem(toolMaterial)).build(consumer);
    }

    private void addHoeRecipe(Item toolMaterial, Item hoe, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(hoe)
                .key('t', toolMaterial)
                .key('s', Tags.Items.RODS_WOODEN)
                .patternLine("tt ")
                .patternLine(" s ")
                .patternLine(" s ")
                .addCriterion("hasItem", hasItem(toolMaterial)).build(consumer);
    }


}
