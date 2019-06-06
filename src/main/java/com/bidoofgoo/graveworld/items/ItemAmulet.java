package com.bidoofgoo.graveworld.items;

import java.util.ArrayList;

import net.minecraft.item.Item;

public class ItemAmulet extends ItemBase {

	public static ArrayList<ItemAmulet> amulets = new ArrayList<ItemAmulet>();
	
	public ItemAmulet(String name) {
		super(name);
		amulets.add(this);
		this.setMaxStackSize(1);
	}
}
