package com.bidoofgoo.graveworld.util.handlers;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingHandler {

	private static ArrayList<Smelting> allSmeltings = new ArrayList<Smelting>();
	
	public static final Smelting BONEMUSH = new Smelting(ModItems.BONEMUSH, ModItems.AMULET_BONE, 1f);
	
	public static void registerSmeltings() {
		System.out.println("Going to do a smelty now, " + allSmeltings.size() + " smeltings found!");
		allSmeltings.forEach(smelty ->{
			System.out.println("doing a smelty");
			GameRegistry.addSmelting(new ItemStack(smelty.in), new ItemStack(smelty.out), smelty.exp);
		});
		
	}
	
	
	public static class Smelting{
		Item in;
		Item out;
		float exp;
		
		public Smelting(Item in, Item out, float exp) {
			this.in = in; this.out = out;this.exp=exp;
			allSmeltings.add(this);
		}
	}
}
