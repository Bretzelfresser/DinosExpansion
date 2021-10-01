package com.renatiux.dinosexpansion.util.registration;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class DoubleDeferredRegister<PRIM extends IForgeRegistryEntry<PRIM>, SECOND extends IForgeRegistryEntry<SECOND>> {

	private final DeferredRegister<PRIM> primary;
	private final DeferredRegister<SECOND> secondary;
	private final List<DoubleRegistryObject<? extends IForgeRegistryEntry<PRIM>, ? extends IForgeRegistryEntry<SECOND>>> list = new LinkedList<>();

	public DoubleDeferredRegister(String modid, IForgeRegistry<PRIM> primaryRegistry,
			IForgeRegistry<SECOND> secondaryRegistry) {
		primary = DeferredRegister.create(primaryRegistry, modid);
		secondary = DeferredRegister.create(secondaryRegistry, modid);
	}

	public <P extends PRIM, S extends SECOND, W extends DoubleRegistryObject<P, S>> W register(String name,Supplier<? extends P> primarySupplier, Supplier<? extends S> secondarySupplier,
			BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
		W toRegister = objectWrapper.apply(primary.register(name, primarySupplier),
				secondary.register(name, secondarySupplier));
		list.add(toRegister);
		return toRegister;

	}

	public <P extends PRIM, S extends SECOND, W extends DoubleRegistryObject<P, S>> W register(String name,
			Supplier<? extends P> primarySupplier, Function<P, S> secondarySupplier,
			BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
		RegistryObject<P> primaryObject = primary.register(name, primarySupplier);
		W toRegister = objectWrapper.apply(primaryObject,
				secondary.register(name, () -> secondarySupplier.apply(primaryObject.get())));
		list.add(toRegister);
		return toRegister;
	}

	public void register(IEventBus bus) {
		primary.register(bus);
		secondary.register(bus);
	}
	
	public List<DoubleRegistryObject<? extends IForgeRegistryEntry<PRIM>, ? extends IForgeRegistryEntry<SECOND>>> getEntries(){
		return list;
	}

}
