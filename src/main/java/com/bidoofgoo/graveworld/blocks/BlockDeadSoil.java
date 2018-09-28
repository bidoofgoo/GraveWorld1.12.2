package com.bidoofgoo.graveworld.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDeadSoil extends BlockBase{

	public BlockDeadSoil(String name, Material materialIn) {
		super(name, materialIn);
		setSoundType(SoundType.GROUND);
	}

}
