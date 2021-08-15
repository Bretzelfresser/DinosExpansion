package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.RaftEntity;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.projectiles.TranquilizerArrowEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Dinosexpansion.MODID);
	public static final DeferredRegister<EntityType<?>> ARROW_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Dinosexpansion.MODID);
	
	public static final RegistryObject<EntityType<Allosaurus>> ALLOSAURUS = ENTITY_TYPES.register("allosaurus", () -> 
	EntityType.Builder.create(Allosaurus::new, EntityClassification.MONSTER).size(1.8f, 2.7f).build(Dinosexpansion.modLoc("allosaurus").toString()));

	public static final RegistryObject<EntityType<TranquilizerArrowEntity>> TRANQUILIZER_ARROW = ARROW_ENTITY_TYPES.register("tranquillizer_arrow", 
			() -> EntityType.Builder.<TranquilizerArrowEntity>create(TranquilizerArrowEntity::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("tranquillizer_arrow").toString()));
	
	public static final RegistryObject<EntityType<RaftEntity>> RAFT = ENTITY_TYPES.register("raft", 
			() -> EntityType.Builder.<RaftEntity>create(RaftEntity::new, EntityClassification.MISC).size(1.0F, 0.5f).trackingRange(10).setCustomClientFactory(RaftEntity::new).build(Dinosexpansion.modLoc("raft").toString()));
}
