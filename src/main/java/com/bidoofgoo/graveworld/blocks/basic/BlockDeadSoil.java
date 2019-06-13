package com.bidoofgoo.graveworld.blocks.basic;

import java.util.Random;

import com.bidoofgoo.graveworld.blocks.BlockBase;
import com.bidoofgoo.graveworld.init.ModBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDeadSoil extends BlockBase{

	public BlockDeadSoil(String name, Material materialIn) {
		super(name, materialIn);
		setSoundType(SoundType.GROUND);
		setTickRandomly(true);
	}
	

}
