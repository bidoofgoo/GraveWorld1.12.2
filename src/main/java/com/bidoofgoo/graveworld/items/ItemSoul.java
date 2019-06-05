package com.bidoofgoo.graveworld.items;

import java.util.ArrayList;

public class ItemSoul extends ItemBase{
	
	public ItemSoul(String name) {
		super(name);
		ItemSoul.allSouls.add(this);
	}

	public static ArrayList<ItemSoul> allSouls = new ArrayList<ItemSoul>();
}
