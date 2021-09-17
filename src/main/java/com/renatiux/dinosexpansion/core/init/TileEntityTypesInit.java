package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Dinosexpansion.MODID);
	
	public static final RegistryObject<TileEntityType<MortarTileEntity>> MORTAR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("mortar_tile_entity", 
			() -> TileEntityType.Builder.create(MortarTileEntity::new, BlockInit.MORTAR.get()).build(null));
	public static final RegistryObject<TileEntityType<AdvancedSmithingTableTileEntity>> ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("advanced_smithing_table_tile_entity",
			() -> TileEntityType.Builder.create(AdvancedSmithingTableTileEntity::new, BlockInit.ADVANCED_SMITHING_TABLE.get()).build(null));
	public static final RegistryObject<TileEntityType<IndustrialGrillTileEntity>> INDUSTRIAL_GRILL_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("industrial_grill",
			() -> TileEntityType.Builder.create(IndustrialGrillTileEntity::new, BlockInit.INDUSTRIAL_GRILL.get()).build(null));
}
