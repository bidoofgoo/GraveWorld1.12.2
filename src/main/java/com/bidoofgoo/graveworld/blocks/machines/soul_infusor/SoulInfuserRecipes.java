package com.bidoofgoo.graveworld.blocks.machines.soul_infusor;

import com.bidoofgoo.graveworld.entities.EntityHeap;
import com.bidoofgoo.graveworld.init.ModBlocks;
import com.bidoofgoo.graveworld.init.ModItems;
import com.google.common.collect.Maps;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import scala.Enumeration.Val;
import scala.annotation.meta.companionClass;
import scala.tools.nsc.doc.model.Public;

public class SoulInfuserRecipes
{
    private static final SoulInfuserRecipes SMELTING_BASE = new SoulInfuserRecipes();
    /** The list of smelting results. */
    private final ArrayList<SoulInfuserRecipe> recipes = new ArrayList<SoulInfuserRecipe>();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static SoulInfuserRecipes instance()
    {
        return SMELTING_BASE;
    }

    private SoulInfuserRecipes()
    {
    	// Add recipes here!
        this.addInfusingRecipe(new ItemStack(ModItems.SOUL_DUST), new ItemStack(ModItems.GOOD_SOUL), new ItemStack(ModItems.AMULET_RUSTY), new ItemStack(Items.GLOWSTONE_DUST), 200, 1.0f);
        this.addInfusingRecipe(new ItemStack(ModItems.HEAP_ITEM), new ItemStack(ModItems.ANGRY_SOUL), new ItemStack(ModItems.AMULET_LIFE), EntityHeap.class, 500, .5f);
        this.addInfusingRecipe(new ItemStack(ModItems.CORPSE_ITEM), new ItemStack(ModItems.ANGRY_SOUL), new ItemStack(ModItems.AMULET_LIFE), EntityZombie.class, 1000, 2f);
        this.addInfusingRecipe(new ItemStack(ModItems.SKELETON_ITEM), new ItemStack(ModItems.ANGRY_SOUL), new ItemStack(ModItems.AMULET_LIFE), EntitySkeleton.class, 1000, 2f);
    
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addInfusingRecipe(ItemStack in, ItemStack soul, ItemStack fuel, ItemStack result, int cooktime, float experience)
    {
        recipes.add(new SoulInfuserRecipe(in, soul, fuel, result, cooktime, experience));
    }
    
    public void addInfusingRecipe(ItemStack in, ItemStack soul, ItemStack fuel, Class<? extends Entity> entityClass, int cooktime, float experience)
    {
        recipes.add(new SoulInfuserRecipe(in, soul, fuel, entityClass, cooktime, experience));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getInfusingResult(ItemStack in, ItemStack soul, ItemStack fuel)
    {
    	SoulInfuserRecipe recipe = getInfuserRecipeByStuff(in, soul, fuel);
    	if (recipe == null) {
			return ItemStack.EMPTY;
		}else {
			return recipe.result;
		}
    }
    
    // Returns the cook time of a recipe
    public int getTotalCookTime(ItemStack in, ItemStack soul, ItemStack fuel) {
    	SoulInfuserRecipe recipe = getInfuserRecipeByStuff(in, soul, fuel);
    	if (recipe == null) {
			return 0;
		}else {
			return recipe.cooktime;
		}
    }
    
    //Returns the recipe of the given stuff
    public SoulInfuserRecipe getInfuserRecipeByStuff(ItemStack in, ItemStack soul, ItemStack fuel) {
    	SoulInfuserRecipe[] returnStack = {null};
        
        recipes.forEach(recipe -> {
        	
        	if(recipe.in.getItem() == in.getItem() && recipe.soul.getItem() == soul.getItem() && 
        			(recipe.fuel.getItem() == fuel.getItem() || recipe.fuel.getItem() == ModItems.AMULET_LEGENDARY)) {
        		returnStack[0] = recipe;
        		System.out.println("found recipe!");
        	}
        });

        return returnStack[0];
    }
    
    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public ArrayList<SoulInfuserRecipe> getSmeltingList()
    {
        return this.recipes;
    }

    public float getSmeltingExperience(SoulInfuserRecipe recipe)
    {
        return recipe.experience;
    }
    
    public class SoulInfuserRecipe{
    	
    	public boolean spawnCreature = false;
    	public ItemStack soul, in, fuel, result = ItemStack.EMPTY;
    	public float experience = 1.0f;
    	public int cooktime = 200;
    	public Class<? extends Entity> entityClass = null;
    	
    	public SoulInfuserRecipe(ItemStack in, ItemStack soul, ItemStack fuel, ItemStack result, int cooktime, float exp) {
			this.in = in; this.soul = soul; this.fuel = fuel; this.result = result; this.experience = exp; this.cooktime = cooktime;
		}
    	
    	public SoulInfuserRecipe(ItemStack in, ItemStack soul, ItemStack fuel, Class<? extends Entity> entityClass, int cooktime, float exp) {
			this.in = in; this.soul = soul; this.fuel = fuel; this.entityClass = entityClass; this.experience = exp; this.cooktime = cooktime;
			this.spawnCreature = true;
		}
    }
}