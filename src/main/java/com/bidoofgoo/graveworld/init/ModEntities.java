package com.bidoofgoo.graveworld.init;

import com.bidoofgoo.graveworld.Main;
import com.bidoofgoo.graveworld.entities.EntityHeap;
import com.bidoofgoo.graveworld.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.EntityHealth;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void registerEntities(){
		registerEntity("heap", EntityHeap.class, Reference.ENTITY_ID_HEAP, 20, 11030351, 13091249);
	}


	private static void registerEntity(String name, Class<? extends Entity> entityClass, int id, int range, int eggPrimary, int eggSecondary){
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, Main.instance, range, 1, true, eggPrimary, eggSecondary);
	}
}
