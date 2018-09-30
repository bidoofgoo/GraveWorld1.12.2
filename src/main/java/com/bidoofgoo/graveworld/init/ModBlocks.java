package com.bidoofgoo.graveworld.init;

import java.util.ArrayList;

import com.bidoofgoo.graveworld.blocks.*;
import com.bidoofgoo.graveworld.blocks.basic.BlockDeadGrass;
import com.bidoofgoo.graveworld.blocks.basic.BlockDeadSoil;
import com.bidoofgoo.graveworld.blocks.basic.BlockSoulStone;
import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.BlockSoulExtractor;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();

	public static final Block SOUL_STONE_BLOCK = new BlockSoulStone("soul_stone_block", Material.GLASS);
	public static final Block KUUB = new BlockBase("kuub", Material.CAKE).setLightLevel(1f);
	public static final Block DEAD_SOIL_BLOCK = new BlockDeadSoil("dead_soil_block", Material.GROUND).setHardness(0.5F);
	public static final Block DEAD_GRASS_BLOCK = new BlockDeadGrass("dead_grass_block");
	public static final Block SOUL_EXTRACTOR_BLOCK = new BlockSoulExtractor("soul_extractor");
}
