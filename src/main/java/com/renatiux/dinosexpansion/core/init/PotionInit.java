package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.effect.Bleeding;
import com.renatiux.dinosexpansion.common.effect.ClimbEffect;
import com.renatiux.dinosexpansion.common.effect.DeathBlow;
import com.renatiux.dinosexpansion.common.effect.RadiationEffect;
import com.sun.jna.platform.win32.WinDef;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Dinosexpansion.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Dinosexpansion.MODID);

    public static final RegistryObject<Effect> CLIMB_EFFECT = EFFECTS.register("climb_effect", () -> new ClimbEffect(EffectType.BENEFICIAL, 0xd4ff00));
    public static final RegistryObject<Effect> BLEEDING = EFFECTS.register("bleeding", Bleeding::new);
    public static final RegistryObject<Effect> DEATHBLOW = EFFECTS.register("death_blow", DeathBlow::new);
    public static final RegistryObject<RadiationEffect> RADIATION = EFFECTS.register("radiation", RadiationEffect::new);

    //Potions
    public static final RegistryObject<Potion> RADIATION_POTION = POTIONS.register("radiation", () -> new Potion(new EffectInstance(RADIATION.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_RADIATION_POTION = POTIONS.register("long_radiation", () -> new Potion(new EffectInstance(RADIATION.get(), 2400, 0)));
    public static final RegistryObject<Potion> STRONG_RADIATION_POTION = POTIONS.register("strong_radiation", () -> new Potion(new EffectInstance(RADIATION.get(), 1200, 2)));


    public static final void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, ItemInit.URANIUM_INGOT.get(), RADIATION_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(RADIATION_POTION.get(), Items.GLOWSTONE_DUST, STRONG_RADIATION_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(RADIATION_POTION.get(), Items.REDSTONE, LONG_RADIATION_POTION.get()));
    }

    private static class BetterBrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BetterBrewingRecipe(Potion bottleInput, Item itemInput, Potion output) {
            this.bottleInput = bottleInput;
            this.itemInput = itemInput;
            this.output = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), output);
        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotionFromItem(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return this.output.copy();
            } else
                return ItemStack.EMPTY;
        }
    }

}
