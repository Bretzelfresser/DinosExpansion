package com.renatiux.dinosexpansion.core.network;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class DENetwork {
	
	public static final SimpleChannel CHANNEL1 = registerSimpleChannel("order_channel", "0.1.0");
			
			
			
	protected static SimpleChannel registerSimpleChannel(String id, String networkVersion) {
		return NetworkRegistry.newSimpleChannel(Dinosexpansion.modLoc(id), () -> networkVersion, networkVersion::equals, networkVersion::equals);
	}
	
	
	public static void init() {
		CHANNEL1.registerMessage(0, DinoaurStatusPacket.class, DinoaurStatusPacket::encode,DinoaurStatusPacket::decode, DinoaurStatusPacket::handle);
		CHANNEL1.registerMessage(1, AttackPacket.class, AttackPacket::write, AttackPacket::read, AttackPacket::handle);
		CHANNEL1.registerMessage(2,IncubatorSliderPacket.class, IncubatorSliderPacket::write, IncubatorSliderPacket::read, IncubatorSliderPacket::handle);
		CHANNEL1.registerMessage(3, PosePacket.class, PosePacket::write, PosePacket::read, PosePacket::handle);
		CHANNEL1.registerMessage(4, OpenTribeGuiPacket.class, OpenTribeGuiPacket::write, OpenTribeGuiPacket::read, OpenTribeGuiPacket::handle);
	}
}
