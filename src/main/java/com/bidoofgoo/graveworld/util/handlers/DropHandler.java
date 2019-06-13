package com.bidoofgoo.graveworld.util.handlers;

import java.util.ArrayList;
import java.util.Random;

import com.bidoofgoo.graveworld.entities.EntityHeap;
import com.bidoofgoo.graveworld.init.ModItems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandler {

	ArrayList<Drop> dropjes = new ArrayList<Drop>();

	public DropHandler() {
		new Drop(ModItems.VOID_DUST, EntityEnderman.class, 0.25f, 3);
		new Drop(Items.ROTTEN_FLESH, EntityHeap.class, 0.5f, 2);
		new Drop(Items.BONE, EntityHeap.class, 0.25f, 2);
		
		// Tool drops
		// Seeking Soulstick
		new Drop(ModItems.SOUL, EntityPlayer.class, 1f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.ANGRY_SOUL, EntityZombie.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.ANGRY_SOUL, EntitySkeleton.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.ANGRY_SOUL, EntityCreeper.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.ANGRY_SOUL, EntityWither.class, 1f, 2, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.SOUL, EntitySpider.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.SOUL, EntityEnderman.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
		new Drop(ModItems.GOOD_SOUL, EntityWolf.class, 0.3f, 1, ModItems.EXTRACTING_STICK);
	}

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event){
		Random random = new Random();
		for(Drop dropje : dropjes){
			try {
				if(Class.forName(dropje.entityClass.getName()).isInstance(event.getEntityLiving()) ){
					float rand = random.nextFloat();

					int dropped = random.nextInt(2) + 1;

					if (rand < dropje.chance) {
				
						int amount = 1 + random.nextInt(dropje.maxamount);
						
						if (!dropje.isToolDrop()) {
							event.getEntityLiving().entityDropItem(new ItemStack(dropje.item, amount), dropped);
						}else if (wasKilledByPlayerWielding(event, dropje.usedTool)) {
							event.getEntityLiving().entityDropItem(new ItemStack(dropje.item, amount), dropped);
						}
					}
				}
			} catch (ClassNotFoundException e) {
				System.out.println("I just did a fucking dead whoops");
			}
		}
	}
	
	public boolean wasKilledByPlayerWielding(LivingDropsEvent event, Item tool) {
		DamageSource source = event.getSource();
		if (source instanceof EntityDamageSource) {
			if (((EntityDamageSource) source).getTrueSource() instanceof EntityPlayer) {
				Item holdingItem = ((EntityPlayer)((EntityDamageSource) source).getTrueSource()).getHeldItemMainhand().getItem();
				if (holdingItem == tool) {
					return true;
				}
			}
		}
		return false;
	}

	public class Drop{

		public Item item;
		public Item usedTool = null;
		public Class entityClass;
		public float chance;
		public int maxamount;

		public Drop(Item item, Class entityClass, float chance, int maxamount) {
			this.item = item;
			this.entityClass = entityClass;
			this.chance = chance;
			this.maxamount = maxamount;
			dropjes.add(this);
		}
		
		public Drop(Item item, Class entityClass, float chance, int maxamount, Item usedTool) {
			this.item = item;
			this.entityClass = entityClass;
			this.chance = chance;
			this.maxamount = maxamount;
			this.usedTool = usedTool;
			dropjes.add(this);
		}
		
		public boolean isToolDrop() {
			return this.usedTool != null;
		}

	}
}
