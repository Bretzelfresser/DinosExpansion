package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.RaftEntity;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.common.entities.enviroment.Charnia;
import com.renatiux.dinosexpansion.common.entities.enviroment.Pycnophlebia;
import com.renatiux.dinosexpansion.common.entities.enviroment.Thaumaptilon;
import com.renatiux.dinosexpansion.common.entities.poop.Poop;
import com.renatiux.dinosexpansion.common.entities.projectiles.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Dinosexpansion.MODID);
	public static final DeferredRegister<EntityType<?>> ARROW_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Dinosexpansion.MODID);
	
	public static final RegistryObject<EntityType<Allosaurus>> ALLOSAURUS = register("allosaurus", EntityType.Builder.<Allosaurus>create(Allosaurus::new, EntityClassification.MONSTER).size(1.8f, 2.7f));
	public static final RegistryObject<EntityType<Dodo>> DODO = register("dodo", EntityType.Builder.<Dodo>create(Dodo::new, EntityClassification.CREATURE).size(1.0f, 1.5f));
	public static final RegistryObject<EntityType<Chimerarachne>> CHIMERARACHNE = register("chimerarachne", EntityType.Builder.<Chimerarachne>create(Chimerarachne::new, EntityClassification.MONSTER).size(2.0f, 0.7f));

	public static final RegistryObject<EntityType<TranquilizerArrowEntity>> TRANQUILIZER_ARROW = ARROW_ENTITY_TYPES.register("tranquillizer_arrow", 
			() -> EntityType.Builder.<TranquilizerArrowEntity>create(TranquilizerArrowEntity::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("tranquillizer_arrow").toString()));
	public static final RegistryObject<EntityType<MegalodonToothArrowEntity>> MEGALODON_ARROW = ARROW_ENTITY_TYPES.register("megalodon_arrow",
			() -> EntityType.Builder.<MegalodonToothArrowEntity>create(MegalodonToothArrowEntity::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("megalodon_arrow").toString()));

	public static final RegistryObject<EntityType<RaftEntity>> RAFT = ENTITY_TYPES.register("raft", 
			() -> EntityType.Builder.<RaftEntity>create(RaftEntity::new, EntityClassification.MISC).size(1.0F, 0.5f).trackingRange(10).setCustomClientFactory(RaftEntity::new).build(Dinosexpansion.modLoc("raft").toString()));
	public static final RegistryObject<EntityType<Poop>> POOP = ENTITY_TYPES.register("poop", () -> EntityType.Builder.<Poop>create(Poop::new, EntityClassification.MISC).size(0.5f, 0.3f).trackingRange(10).build(Dinosexpansion.modLoc("poop").toString()));
	
	public static final RegistryObject<EntityType<WoodBoomerang>> WOOD_BOOMERANG = ENTITY_TYPES.register("wood_boomerang", 
			() -> EntityType.Builder.<WoodBoomerang>create(WoodBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("wood_boomerang").toString()));
	
	public static final RegistryObject<EntityType<IronBoomerang>> IRON_BOOMERANG = ENTITY_TYPES.register("iron_boomerang", 
			() -> EntityType.Builder.<IronBoomerang>create(IronBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("iron_boomerang").toString()));
	
	public static final RegistryObject<EntityType<DiamondBoomerang>> DIAMOND_BOOMERANG = ENTITY_TYPES.register("diamond_boomerang", 
			() -> EntityType.Builder.<DiamondBoomerang>create(DiamondBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("diamond_boomerang").toString()));
	
	public static final RegistryObject<EntityType<Thaumaptilon>> THAUMAPTILON = register("thaumaptilon", EntityType.Builder.create(Thaumaptilon::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Charnia>> CHARNIA = register("charnia", EntityType.Builder.create(Charnia::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Pycnophlebia>> PYCNOPHLEBIA = register("pycnophlebia", EntityType.Builder.create(Pycnophlebia::new, EntityClassification.AMBIENT));


	public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builer){
		return ENTITY_TYPES.register(name, () -> builer.build(Dinosexpansion.modLoc(name).toString()));
	}
}
