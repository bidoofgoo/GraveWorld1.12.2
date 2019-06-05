package com.bidoofgoo.graveworld.blocks.basic;

import com.bidoofgoo.graveworld.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGrave extends BlockBase {

	public static final AxisAlignedBB GRAVE_AABB = new AxisAlignedBB((double)1/16, 0, (double)0, (double)15/16, (double)1, (double)2/16);
	
	public BlockGrave(String name) {
		super(name, Material.ROCK);
		setSoundType(SoundType.STONE);
	
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		// TODO Auto-generated method stub
		return GRAVE_AABB;
	}
}
