package com.bidoofgoo.graveworld.init;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();

	public static final Block SOUL_STONE_BLOCK = new BlockSoulStone("soul_stone_block", Material.GLASS);
	public static final Block KUUB = new BlockBase("kuub", Material.CAKE).setLightLevel(1f);
	public static final Block DEAD_SOIL_BLOCK = new BlockDeadSoil("dead_soil_block", Material.GROUND).setHardness(0.5F);
}
