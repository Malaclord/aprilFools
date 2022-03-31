package com.malaclord.aprilfools;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;


public class EventListener implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Random rand = new Random();

		Entity entity = event.getEntity();
		World world = entity.getWorld();
		Location location = entity.getLocation();
		EntityType type = entity.getType();

		switch (type) {
			case ZOMBIFIED_PIGLIN:
				PigZombie pigZombie = (PigZombie) entity;

				if(pigZombie.isAdult()) {
					for(int i = 0; i < rand.nextInt(4)+2; i++) {
						PigZombie pigZombieSmall = (PigZombie) world.spawnEntity(location,EntityType.ZOMBIFIED_PIGLIN);
						pigZombieSmall.setBaby();
					}
				}

				if(pigZombie.getVehicle() != null) pigZombie.getVehicle().remove();
				break;
			case SPIDER:
				Spider spider = (Spider) entity;

				if(spider.getVehicle() != null) spider.getVehicle().remove();
				break;
			case CREEPER:
				Creeper creeper = (Creeper) entity;

				if(creeper.getVehicle() != null) creeper.getVehicle().remove();
				break;
			case SKELETON:
				Skeleton skeleton = (Skeleton) entity;

				if(skeleton.getVehicle() != null) skeleton.getVehicle().remove();
				break;
			case BLAZE:
				Blaze blaze = (Blaze) entity;

				if(blaze.getVehicle() != null) blaze.getVehicle().remove();
				break;
		}
	}

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event){
		Entity entity = event.getEntity();
		World world = entity.getWorld();
		Location location = entity.getLocation();
		EntityType type = entity.getType();

		switch (type) {
			case BLAZE:
				Blaze blaze = (Blaze) entity;

				Bat bat = (Bat) world.spawnEntity(location,EntityType.BAT);

				bat.setInvisible(true);
				bat.setInvulnerable(true);
				bat.setSilent(true);
				bat.addPassenger(blaze);
				break;
			case PIGLIN:
				Piglin piglin = (Piglin) entity;

				Strider strider = (Strider) world.spawnEntity(location,EntityType.STRIDER);

				strider.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000000,255));

				strider.setShivering(false);
				strider.setAdult();
				strider.addPassenger(piglin);
				break;
			case ZOMBIFIED_PIGLIN:
				PigZombie pigZombie = (PigZombie) entity;

				pigZombie.setAngry(true);

				MagmaCube magmaCube = (MagmaCube) world.spawnEntity(location,EntityType.MAGMA_CUBE);

				magmaCube.setSize(0);
				magmaCube.setInvisible(true);
				magmaCube.setInvulnerable(true);
				magmaCube.addPassenger(pigZombie);

				break;
			case SPIDER:
				Spider spider = (Spider) entity;

				Vex vex = (Vex) world.spawnEntity(location,EntityType.VEX);

				vex.addPassenger(spider);
				vex.setInvisible(true);
				vex.setInvulnerable(true);
				vex.setSilent(true);
				vex.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(1000000000,255));
				vex.getEquipment().setItem(EquipmentSlot.HAND,null);

				spider.setCustomName("Grumm");
				spider.setCustomNameVisible(false);

				break;
			case PILLAGER:
				Pillager pillager = (Pillager) entity;



				break;
			case SKELETON:
				Skeleton skeleton = (Skeleton) entity;

				Goat goat = (Goat) world.spawnEntity(location,EntityType.GOAT);

				goat.setScreaming(true);
				goat.addPassenger(skeleton);

				skeleton.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(1000000,1));
				skeleton.setFireTicks(1000000000);
				break;
			case ENDERMAN:
				Enderman enderman = (Enderman) entity;

				Guardian head = (Guardian) world.spawnEntity(location,EntityType.GUARDIAN);

				enderman.addPassenger(head);
				break;
			case ZOMBIE:
				Zombie zombie = (Zombie) entity;

				Chicken zombieChicken = (Chicken) world.spawnEntity(location,EntityType.CHICKEN);
				Hoglin smallHoglin = (Hoglin) world.spawnEntity(location,EntityType.HOGLIN);
				smallHoglin.setBaby();
				smallHoglin.setImmuneToZombification(true);
				smallHoglin.setAgeLock(true);

				smallHoglin.setLeashHolder(zombie);
				zombieChicken.addPassenger(zombie);
				break;
			case CREEPER:
				Creeper creeper = (Creeper) entity;

				Axolotl axolotl = (Axolotl) world.spawnEntity(location,EntityType.AXOLOTL);

				axolotl.addPassenger(creeper);
				creeper.setMaxFuseTicks(10);
				break;
		}
	}
}
