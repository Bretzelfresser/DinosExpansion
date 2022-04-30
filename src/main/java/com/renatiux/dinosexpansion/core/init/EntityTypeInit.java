package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.RaftEntity;
import com.renatiux.dinosexpansion.common.entities.aquatic.Aegirocassis;
import com.renatiux.dinosexpansion.common.entities.aquatic.Eosqualodon;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Astorgosuchus;
import com.renatiux.dinosexpansion.common.entities.environment.*;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.common.entities.environment.myrmex.ArchimyrmexLarvae;
import com.renatiux.dinosexpansion.common.entities.poop.Poop;
import com.renatiux.dinosexpansion.common.entities.projectiles.*;

import com.renatiux.dinosexpansion.common.entities.skeletons.AstorgosuchusSkeleton;
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
	public static final RegistryObject<EntityType<CompoundArrowEntity>> COMPOUND_ARROW_ENTITY = ARROW_ENTITY_TYPES.register("compound_arrow",
			()-> EntityType.Builder.<CompoundArrowEntity>create(CompoundArrowEntity::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("compound_arrow").toString()));

	public static final RegistryObject<EntityType<RaftEntity>> RAFT = ENTITY_TYPES.register("raft", 
			() -> EntityType.Builder.<RaftEntity>create(RaftEntity::new, EntityClassification.MISC).size(1.0F, 0.5f).trackingRange(10).setCustomClientFactory(RaftEntity::new).build(Dinosexpansion.modLoc("raft").toString()));
	public static final RegistryObject<EntityType<Poop>> POOP = ENTITY_TYPES.register("poop", () -> EntityType.Builder.<Poop>create(Poop::new, EntityClassification.MISC).size(0.5f, 0.3f).trackingRange(10).build(Dinosexpansion.modLoc("poop").toString()));
	
	public static final RegistryObject<EntityType<WoodBoomerang>> WOOD_BOOMERANG = ENTITY_TYPES.register("wood_boomerang", 
			() -> EntityType.Builder.<WoodBoomerang>create(WoodBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("wood_boomerang").toString()));
	
	public static final RegistryObject<EntityType<IronBoomerang>> IRON_BOOMERANG = ENTITY_TYPES.register("iron_boomerang", 
			() -> EntityType.Builder.<IronBoomerang>create(IronBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("iron_boomerang").toString()));
	
	public static final RegistryObject<EntityType<DiamondBoomerang>> DIAMOND_BOOMERANG = ENTITY_TYPES.register("diamond_boomerang", 
			() -> EntityType.Builder.<DiamondBoomerang>create(DiamondBoomerang::new, EntityClassification.MISC).build(Dinosexpansion.modLoc("diamond_boomerang").toString()));

	public static final RegistryObject<EntityType<SpearEntity>> SPEAR_ENTITY = ENTITY_TYPES.register("spear",
			()-> EntityType.Builder.<SpearEntity>create(SpearEntity::new, EntityClassification.MISC).size(0.5f, 0.5f).setTrackingRange(4).setUpdateInterval(20).build("spear"));

	public static final RegistryObject<EntityType<SpikesShieldEntity>> SPIKE_SHIELD_ENTITY_TYPE = register("spike_shield", EntityType.Builder.<SpikesShieldEntity>create(SpikesShieldEntity::new, EntityClassification.MISC).size(0.75F, 0.3F));
	public static final RegistryObject<EntityType<HeavyShieldEntity>> HEAVY_SHIELD_ENTITY_TYPE = register("heavy_shield", EntityType.Builder.<HeavyShieldEntity>create(HeavyShieldEntity::new, EntityClassification.MISC).size(1.5f, 3f).immuneToFire().trackingRange(10));
	public static final RegistryObject<EntityType<AstorgosuchusSkeleton>> ASTORGOSUCHUS_SKELETON = register("astorgosuchus_skeleton", EntityType.Builder.<AstorgosuchusSkeleton>create(AstorgosuchusSkeleton::new ,EntityClassification.MISC).size(2f, 1f).immuneToFire());
	
	public static final RegistryObject<EntityType<Thaumaptilon>> THAUMAPTILON = register("thaumaptilon", EntityType.Builder.create(Thaumaptilon::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Charnia>> CHARNIA = register("charnia", EntityType.Builder.create(Charnia::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Pycnophlebia>> PYCNOPHLEBIA = register("pycnophlebia", EntityType.Builder.create(Pycnophlebia::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Aegirocassis>> AEGIROCASSIS = register("aegirocassis", EntityType.Builder.create(Aegirocassis::new, EntityClassification.WATER_CREATURE));

	public static final RegistryObject<EntityType<Eosqualodon>> EOSQUALODON = register("eosqualodon", EntityType.Builder.create(Eosqualodon::new, EntityClassification.WATER_CREATURE));

	public static final RegistryObject<EntityType<Opabinia>> OPABINIA = register("opabinia", EntityType.Builder.create(Opabinia::new, EntityClassification.WATER_CREATURE));

	public static final RegistryObject<EntityType<ArchimyrmexLarvae>> ARCHIMYRMEX_LARVAE = register("archimyrmex_larvae", EntityType.Builder.create(ArchimyrmexLarvae::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Xenocranium>> XENOCRANIUM = register("xenocranium", EntityType.Builder.create(Xenocranium::new, EntityClassification.AMBIENT));

	public static final RegistryObject<EntityType<Astorgosuchus>> ASTORGOSUCHUS = register("astorgosuchus", EntityType.Builder.<Astorgosuchus>create(Astorgosuchus::new, EntityClassification.MONSTER).size(1.5f, 0.6f));

	public static final RegistryObject<EntityType<Wheterellus>> WHETERELLUS = register("wheterellus", EntityType.Builder.create(Wheterellus::new, EntityClassification.WATER_AMBIENT).size(0.5F, 0.3F));


	public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder){
		return ENTITY_TYPES.register(name, () -> builder.build(Dinosexpansion.modLoc(name).toString()));
	}
}
