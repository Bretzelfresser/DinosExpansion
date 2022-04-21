package com.renatiux.dinosexpansion.core.init;

import com.google.common.collect.Sets;
import com.mojang.datafixers.types.Type;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.*;
import com.renatiux.dinosexpansion.common.tileEntities.cable.BasicEnergyCableTileEntity;

import com.renatiux.dinosexpansion.core.tags.Tags;
import com.renatiux.dinosexpansion.util.WoodTypeDE;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileEntityTypesInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Dinosexpansion.MODID);
	
	public static final RegistryObject<TileEntityType<MortarTileEntity>> MORTAR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("mortar_tile_entity", () -> TileEntityType.Builder.create(MortarTileEntity::new, BlockInit.MORTAR.get()).build(null));
	public static final RegistryObject<TileEntityType<AdvancedSmithingTableTileEntity>> ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("advanced_smithing_table_tile_entity", () -> TileEntityType.Builder.create(AdvancedSmithingTableTileEntity::new, BlockInit.ADVANCED_SMITHING_TABLE.getPrimary(), BlockInit.STRUCTURE_SMITHING_TABLE.get()).build(null));
	public static final RegistryObject<TileEntityType<IndustrialGrillTileEntity>> INDUSTRIAL_GRILL_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("industrial_grill", () -> TileEntityType.Builder.create(IndustrialGrillTileEntity::new, BlockInit.INDUSTRIAL_GRILL.getPrimary(), BlockInit.STRUCTURE_INDUSTRIAL_GRILL.get()).build(null));
	public static final RegistryObject<TileEntityType<IncubatorTileEntity>> INCUBATOR = TILE_ENTITY_TYPES.register("incubator", () -> TileEntityType.Builder.create(IncubatorTileEntity::new, BlockInit.INCUBATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<BasicEnergyCableTileEntity>> BASIC_ENERGY_CABLE = TILE_ENTITY_TYPES.register("basic_energy_cable", () -> TileEntityType.Builder.create(BasicEnergyCableTileEntity::new, BlockInit.BASIC_ENERGY_CABLE.getPrimary()).build(null));
	public static final RegistryObject<TileEntityType<GeneratorTileEntity>> GENERATOR = TILE_ENTITY_TYPES.register("generator", () -> TileEntityType.Builder.create(GeneratorTileEntity::new, BlockInit.GENERATOR.getPrimary(), BlockInit.STRUCTURE_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<FeederTileEntity>> FEEDER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("feeder", () -> TileEntityType.Builder.create(FeederTileEntity::new, BlockInit.FEEDER.getPrimary(), BlockInit.STRUCTURE_FEEDER.get()).build(null));
	public static final RegistryObject<TileEntityType<SkeletalAssemblyTableTile>> SKELETAL_ASSEMBLY_TABLE_TILE = TILE_ENTITY_TYPES.register("skeletal_assembly_table", () -> TileEntityType.Builder.create(SkeletalAssemblyTableTile::new, BlockInit.SKELETAL_ASSEMBLY_TABLE.getPrimary(), BlockInit.STRUCTURE_SKELETAL_ASSEMBLY_TABLE.get()).build(null));
	public static final RegistryObject<TileEntityType<CabinetTileEntity>> CABINET_TILE_ENTITY = TILE_ENTITY_TYPES.register("cabinet", () -> TileEntityType.Builder.create(CabinetTileEntity::new, BlockInit.CABINET.getPrimary()).build(null));
	public static final RegistryObject<TileEntityType<PrehistoricBedTileEntity>> PREHISTORIC_BED = TILE_ENTITY_TYPES.register("prehistoric_bed", () -> new TileEntityType<>(PrehistoricBedTileEntity::new, Sets.newHashSet(BlockInit.PREHISTORIC_BED.get()), null));
	public static final RegistryObject<TileEntityType<ResearchTableTileEntity>> RESEARCH_TABLE_TILE = TILE_ENTITY_TYPES.register("research_table", () -> TileEntityType.Builder.create(ResearchTableTileEntity::new, BlockInit.RESEARCH_TABLE.get()).build(null));
	public static final RegistryObject<TileEntityType<RadiationTileEntity>> RADIATION_TE = TILE_ENTITY_TYPES.register("radiation_te", () -> new BetterTileEntityType<>(RadiationTileEntity::new, block -> block instanceof RadiationTileEntity.IRadiationBlock, null));

	public static final RegistryObject<TileEntityType<DESignTileEntity>> SIGN_TILE_ENTITIES = TILE_ENTITY_TYPES.register("sign",
			()-> TileEntityType.Builder.create(DESignTileEntity::new, BlockInit.PALM_SIGN.get(), BlockInit.PALM_WALL_SIGN.get()).build(null));

	private static final class BetterTileEntityType<T extends TileEntity> extends TileEntityType<T>{
		private final Predicate<Block> acceptor;

		public BetterTileEntityType(Supplier<? extends T> factoryIn, Predicate<Block> acceptor, Type<?> dataFixerType) {
			super(factoryIn, Sets.newHashSet(), dataFixerType);
			this.acceptor = acceptor;
		}

		@Override
		public boolean isValidBlock(Block blockIn) {
			return acceptor.test(blockIn);
		}
	}
}
