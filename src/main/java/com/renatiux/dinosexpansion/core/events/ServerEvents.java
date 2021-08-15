package com.renatiux.dinosexpansion.core.events;

import java.util.List;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Bus.FORGE)
public class ServerEvents {

	@SubscribeEvent
	public static void onPlayerHurt(LivingHurtEvent event) {
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		alertTamedDinosaurs(player);
	}

	private static void alertTamedDinosaurs(PlayerEntity player) {
		AxisAlignedBB axisalignedbb;
		axisalignedbb = AxisAlignedBB.fromVector(player.getPositionVec()).grow(20d, 10.0D, 20d);
		List<Dinosaur> list = player.world.getLoadedEntitiesWithinAABB(Dinosaur.class, axisalignedbb);
		for (Dinosaur dinosaur : list) {
			System.out.println();
			if (dinosaur.isTame() && dinosaur.isOwner(player) && (dinosaur.getStatus() == DinosaurStatus.HOSTILE || dinosaur.getStatus() == DinosaurStatus.PROTECTION)) {
				if (!dinosaur.isSleeping() && !dinosaur.isKnockout()) {
					setAttackTarget(dinosaur, player.getRevengeTarget());
				}
			}

		}
	}

	private static void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
		mobIn.setAttackTarget(targetIn);
	}

}
