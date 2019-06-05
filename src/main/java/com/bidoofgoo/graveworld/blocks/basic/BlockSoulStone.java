package com.bidoofgoo.graveworld.blocks.basic;

import java.util.Random;

import com.bidoofgoo.graveworld.blocks.BlockBase;
import com.bidoofgoo.graveworld.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulStone extends BlockBase{

	private static final int MAX_BOUNCE = 10;
	
	public BlockSoulStone(String name, Material materialIn) {
		super(name, materialIn);
		this.setSoundType(SoundType.GLASS);
		this.setLightLevel(1.0F);
		this.setHardness(0.3F);
		this.setTickRandomly(true);
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

                    Block blonky =  iblockstate1.getBlock();
                    if (blonky == Blocks.DIRT)
                    {
                    	worldIn.setBlockState(blockpos, ModBlocks.DEAD_SOIL_BLOCK.getDefaultState());
                    } else if(blonky == Blocks.GRASS) {
                    	worldIn.setBlockState(blockpos, ModBlocks.DEAD_GRASS_BLOCK.getDefaultState());
                    }
                    
                }
            }
        }
	}
	
	
	/**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.GLOWSTONE_DUST;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.SNOW;
    }

}
