package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.cable.BasicEnergyCableTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Dinosexpansion.MODID);
	
	public static final RegistryObject<TileEntityType<MortarTileEntity>> MORTAR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("mortar_tile_entity", 
			() -> TileEntityType.Builder.create(MortarTileEntity::new, BlockInit.MORTAR.get()).build(null));
	public static final RegistryObject<TileEntityType<AdvancedSmithingTableTileEntity>> ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("advanced_smithing_table_tile_entity",
			() -> TileEntityType.Builder.create(AdvancedSmithingTableTileEntity::new, BlockInit.ADVANCED_SMITHING_TABLE.getPrimary()).build(null));
	public static final RegistryObject<TileEntityType<IndustrialGrillTileEntity>> INDUSTRIAL_GRILL_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("industrial_grill",
			() -> TileEntityType.Builder.create(IndustrialGrillTileEntity::new, BlockInit.INDUSTRIAL_GRILL.getPrimary()).build(null));
	public static final RegistryObject<TileEntityType<IncubatorTileEntity>> INCUBATOR = TILE_ENTITY_TYPES.register("incubator",
			() -> TileEntityType.Builder.create(IncubatorTileEntity::new, BlockInit.INCUBATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<BasicEnergyCableTileEntity>> BASIC_ENERGY_CABLE = TILE_ENTITY_TYPES.register("basic_energy_cable",
			() -> TileEntityType.Builder.create(BasicEnergyCableTileEntity::new, BlockInit.BASIC_ENERGY_CABLE.getPrimary()).build(null));
}
