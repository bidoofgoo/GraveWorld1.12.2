package com.bidoofgoo.graveworld.init;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.items.ItemBase;
import com.bidoofgoo.graveworld.items.ItemSoul;

import net.minecraft.item.Item;

public class ModItems {

	public static final ArrayList<Item> ITEMS = new ArrayList<Item>();

	public static final Item VOID_DUST = new ItemBase("void_dust");
	public static final Item SOUL_DUST = new ItemBase("soul_dust");
	public static final Item SEEKING_SOULMASS = new ItemBase("seeking_soulmass");
	public static final Item DUST = new ItemBase("dust");
	public static final Item HEAP_ITEM = new ItemBase("heap_item");
	public static final Item CORPSE_ITEM = new ItemBase("corpse_item");
	public static final Item SKELETON_ITEM = new ItemBase("skeleton_item");
	public static final Item AMULET_LIFE = new ItemBase("amulet_life"); 

	public static final Item GOOD_SOUL = new ItemSoul("good_soul");
	public static final Item SOUL = new ItemSoul("neutral_soul");
	public static final Item ANGRY_SOUL = new ItemSoul("angry_soul");
}
