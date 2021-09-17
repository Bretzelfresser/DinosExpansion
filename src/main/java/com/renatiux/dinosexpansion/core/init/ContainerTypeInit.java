package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.AdvancedSmithingTableContainer;
import com.renatiux.dinosexpansion.common.container.AllosaurusContainer;
import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.container.DodoContainer;
import com.renatiux.dinosexpansion.common.container.IndustrialGrillContainer;
import com.renatiux.dinosexpansion.common.container.MortarContainer;
import com.renatiux.dinosexpansion.common.container.OrderContainer;
import com.renatiux.dinosexpansion.common.container.RaftContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Dinosexpansion.MODID);

	public static final RegistryObject<ContainerType<AllosaurusContainer>> ALLOSAURUS_CONTAINER_TYPE = CONTAINER_TYPES
			.register("allosaurus_container", () -> IForgeContainerType.create(AllosaurusContainer::new));
	//machines
	public static final RegistryObject<ContainerType<MortarContainer>> MORTAR_CONTAINER_TYPE = CONTAINER_TYPES
			.register("mortar_container", () -> IForgeContainerType.create(MortarContainer::new));
	public static final RegistryObject<ContainerType<AdvancedSmithingTableContainer>> ADVANCED_SMITHING_TABLE_CONTAINER_TYPE = CONTAINER_TYPES.register("advanced_smithing_table_container",
			() -> IForgeContainerType.create(AdvancedSmithingTableContainer::new));
	public static final RegistryObject<ContainerType<IndustrialGrillContainer>> INDUSTRIAL_GRILL_CONTAINER_TYPE = CONTAINER_TYPES.register("industrial_grill_container", 
			() -> IForgeContainerType.create(IndustrialGrillContainer::new));

	public static final RegistryObject<ContainerType<RaftContainer>> RAFT = CONTAINER_TYPES.register("raft_container",
			() -> IForgeContainerType.create(RaftContainer::new));
	
	public static final RegistryObject<ContainerType<DinosaurTamingInventory>> DINOSAUR_TAMING_INVENTORY = CONTAINER_TYPES.register("taming", 
			() -> IForgeContainerType.create(DinosaurTamingInventory::new));
	
	public static final RegistryObject<ContainerType<OrderContainer>> DINOSAUR_ORDER_CONTAINER = CONTAINER_TYPES.register("order", 
			() -> IForgeContainerType.create(OrderContainer::new));
	
	public static final RegistryObject<ContainerType<DodoContainer>> DODO_CONTAINER = CONTAINER_TYPES.register("dodo_container", () ->
	IForgeContainerType.create(DodoContainer::new));
}
