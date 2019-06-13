package com.bidoofgoo.graveworld.items;

import com.bidoofgoo.graveworld.Main;
import com.bidoofgoo.graveworld.init.ModItems;
import com.bidoofgoo.graveworld.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.GRAVETAB);

		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModel() {

		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
