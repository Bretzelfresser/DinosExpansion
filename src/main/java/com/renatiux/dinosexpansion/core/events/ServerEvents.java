package com.renatiux.dinosexpansion.core.events;

import java.util.List;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.machine.PrehistoricBed;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;
import com.renatiux.dinosexpansion.common.entities.projectiles.NarcoticArrowEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
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
		
		
		if(event.getSource().getImmediateSource() instanceof NarcoticArrowEntity) {
			System.out.println("hi");
			event.setAmount(2);
		}
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

	@SubscribeEvent
	public void onPlayerSetSpawn(PlayerSetSpawnEvent event){

		final PlayerEntity player = event.getPlayer();
		final World world = player.getEntityWorld();
		final BlockPos pos = event.getNewSpawn();

		if (pos != null && !world.isRemote) {
			Block block = world.getBlockState(pos).getBlock();

			if (block instanceof PrehistoricBed) {
				event.setCanceled(true);
			}
		}

	}
}
