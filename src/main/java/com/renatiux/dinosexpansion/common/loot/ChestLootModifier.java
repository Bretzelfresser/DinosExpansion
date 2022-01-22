package com.renatiux.dinosexpansion.common.loot;

import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.Dinosexpansion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class ChestLootModifier extends LootModifier {
    private final Item item;
    private final float probability;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public ChestLootModifier(ILootCondition[] conditionsIn, Item item, float probability) {
        super(conditionsIn);
        this.item = item;
        this.probability = probability;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() <= probability){
            generatedLoot.add(new ItemStack(item));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ChestLootModifier>{

        public Serializer(String registryName){
            setRegistryName(Dinosexpansion.modLoc(registryName));
        }

        @Override
        public ChestLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(object, "item")));
            float probability = JSONUtils.getFloat(object, "probability", 0.2F);
            return new ChestLootModifier(ailootcondition, item, probability);
        }

        @Override
        public JsonObject write(ChestLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.item).toString());
            json.addProperty("probability", instance.probability);
            return json;
        }
    }
}
