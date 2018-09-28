package com.bidoofgoo.graveworld.init;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.items.ItemBase;

import net.minecraft.item.Item;

public class ModItems {

	public static final ArrayList<Item> ITEMS = new ArrayList<Item>();

	public static final Item GOOD_SOUL = new ItemBase("good_soul");
	public static final Item SOUL = new ItemBase("neutral_soul");
	public static final Item ANGRY_SOUL = new ItemBase("angry_soul");
}
