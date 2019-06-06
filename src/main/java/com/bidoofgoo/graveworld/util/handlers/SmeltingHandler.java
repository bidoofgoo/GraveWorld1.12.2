package com.bidoofgoo.graveworld.util.handlers;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingHandler {

	private static ArrayList<Smelting> allSmeltings = new ArrayList<Smelting>();
	
	
	private final Smelting BONEMUSH = new Smelting(ModItems.BONEMUSH, ModItems.AMULET_PECULIAR, 1f);
	
	
	
	public static void registerSmeltings() {
		System.out.println("Going to do a smelty now");
		allSmeltings.forEach(smelty ->{
			System.out.println("doing a smelty");
			GameRegistry.addSmelting(new ItemStack(smelty.in), new ItemStack(smelty.out), smelty.exp);
		});
		
	}
	
	
	private class Smelting{
		Item in;
		Item out;
		float exp;
		
		public Smelting(Item in, Item out, float exp) {
			this.in = in; this.out = out;this.exp=exp;
			allSmeltings.add(this);
		}
	}
}
