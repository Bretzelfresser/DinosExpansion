package com.renatiux.dinosexpansion.common.advancement_trigger;

import com.google.gson.JsonObject;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;

public class TameDinosaurCriterion extends AbstractCriterionTrigger<TameDinosaurCriterion.Instance> {

	protected static final ResourceLocation ID = Dinosexpansion.modLoc("tame_dinosaur");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected Instance deserializeTrigger(JsonObject json, AndPredicate predicate,
			ConditionArrayParser conditionsParser) {
		EntityPredicate.AndPredicate entitypredicate$andpredicate = EntityPredicate.AndPredicate
				.deserializeJSONObject(json, "entity", conditionsParser);
		return new TameDinosaurCriterion.Instance(predicate, entitypredicate$andpredicate);
	}
	
	
	public void trigger(ServerPlayerEntity player, Dinosaur dino) {
	      LootContext lootcontext = EntityPredicate.getLootContext(player, dino);
	      this.triggerListeners(player, (instance) -> {
	         return instance.test(lootcontext);
	      });
	   }

	protected static class Instance extends CriterionInstance {

		private final EntityPredicate.AndPredicate dino;

		public Instance(EntityPredicate.AndPredicate player, EntityPredicate.AndPredicate predicate) {
			super(ID, player);
			this.dino = predicate;
		}

		public boolean test(LootContext context) {
			return this.dino.testContext(context);
		}

		@Override
		public JsonObject serialize(ConditionArraySerializer conditions) {
			JsonObject jsonobject = super.serialize(conditions);
			jsonobject.add("entity", this.dino.serializeConditions(conditions));
			return jsonobject;
		}

	}
}
