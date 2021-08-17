package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.AllosaurusContainer;
import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.container.MortarContainer;
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
	
	public static final RegistryObject<ContainerType<MortarContainer>> MORTAR_CONTAINER_TYPE = CONTAINER_TYPES
			.register("mortar_container", () -> IForgeContainerType.create(MortarContainer::new));

	public static final RegistryObject<ContainerType<RaftContainer>> RAFT = CONTAINER_TYPES.register("raft_container",
			() -> IForgeContainerType.create(RaftContainer::new));
	
	public static final RegistryObject<ContainerType<DinosaurTamingInventory>> DINOSAUR_TAMING_INVENTORY = CONTAINER_TYPES.register("taming", 
			() -> IForgeContainerType.create(DinosaurTamingInventory::new));
}
