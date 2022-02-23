package com.renatiux.dinosexpansion.common.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
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
    private final Pair<Item, Float>[] entries;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public ChestLootModifier(ILootCondition[] conditionsIn, Item item, float probability) {
       this(conditionsIn, new Pair[]{Pair.of(item, probability)});
    }

    public ChestLootModifier(ILootCondition[] conditionsIn, Pair<Item, Float>[] entries) {
        super(conditionsIn);
       this.entries = entries;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        for (Pair<Item, Float> entry : this.entries) {
            if (context.getRandom().nextFloat() <= entry.getSecond()) {
                System.out.println(entry.getFirst());
                generatedLoot.add(new ItemStack(entry.getFirst()));
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ChestLootModifier>{

        public Serializer(String registryName){
            setRegistryName(Dinosexpansion.modLoc(registryName));
        }

        @Override
        public ChestLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            JsonArray array = object.getAsJsonArray("entries");
            Pair<Item, Float>[] entries = new Pair[array.size()];
            int i = 0;
            for (JsonElement element : array) {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(element.getAsJsonObject(), "item")));
                float probability = JSONUtils.getFloat(element.getAsJsonObject(), "probability", 0.2F);
                entries[i] = Pair.of(item, probability);
                i++;
            }
            return new ChestLootModifier(ailootcondition, entries);
        }

        @Override
        public JsonObject write(ChestLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            JsonArray array = new JsonArray();
            for(Pair<Item, Float> entry : instance.entries) {
                JsonObject element = new JsonObject();
                element.addProperty("item", ForgeRegistries.ITEMS.getKey(entry.getFirst()).toString());
                element.addProperty("probability", entry.getSecond());
                array.add(element);
            }
            json.add("entries", array);
            return json;
        }
    }
}
