package com.bidoofgoo.graveworld.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockBaseModel extends BlockBase{

	public BlockBaseModel(String name, Material materialIn) {
		super(name, materialIn);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
