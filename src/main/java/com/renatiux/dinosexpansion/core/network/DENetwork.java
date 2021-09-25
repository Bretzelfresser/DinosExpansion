package com.renatiux.dinosexpansion.core.network;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class DENetwork {
	
	public static final SimpleChannel CHANNEL1 = registerSimpleChannel("order_channel", "0.1.0");
			
			
			
	protected static SimpleChannel registerSimpleChannel(String id, String networkVersion) {
		return NetworkRegistry.newSimpleChannel(Dinosexpansion.modLoc(id), () -> networkVersion, version -> version.equals(networkVersion), version -> version.equals(networkVersion));
	}
	
	
	public static void init() {
		CHANNEL1.registerMessage(0, DinoaurStatusPacket.class, DinoaurStatusPacket::encode,DinoaurStatusPacket::decode, DinoaurStatusPacket::handle);
		CHANNEL1.registerMessage(1, AttackPacket.class, AttackPacket::write, AttackPacket::read, AttackPacket::handle);
	}
}
