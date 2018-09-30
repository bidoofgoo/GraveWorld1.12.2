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
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            
            else
            {
                
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                    {
                        return;
                    }

                    IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                    IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if (iblockstate1.getBlock() == Blocks.DIRT)
                    {
                    	worldIn.setBlockState(blockpos, ModBlocks.DEAD_SOIL_BLOCK.getDefaultState());
                    } else if(iblockstate1.getBlock() == Blocks.GRASS) {
                    	worldIn.setBlockState(blockpos, ModBlocks.DEAD_GRASS_BLOCK.getDefaultState());
                    }
                    
                }
            }
        }
	}

}
