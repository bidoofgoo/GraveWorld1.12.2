package com.bidoofgoo.graveworld.world.gen;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.bidoofgoo.graveworld.world.gen.generators.WorldGenStructure;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenStructures implements IWorldGenerator{

	public static final ArrayList<WorldGenStructure> worldStructures = new ArrayList<WorldGenStructure>();
	
	public static final WorldGenStructure ALTAR = new WorldGenStructure("grave", 1, Blocks.GRASS);
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		
		case 0:
			worldStructures.forEach(struct ->{
				generateStructure(struct, world, random, chunkX, chunkZ, struct.chance, struct.topBlock);
			});
			break;
		
		default:
			break;
		}
		
	}
	
	private void generateStructure(WorldGenStructure generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock) {
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biomeClass = world.provider.getBiomeForCoords(pos).getClass();
		if((generator).biomeSpecific) {
			ArrayList<Class<?>> classList = new ArrayList<Class<?>>(Arrays.asList((generator).biome));
			if(classList.contains(biomeClass)) {
				if (random.nextInt(chance) == 0) {
					generator.generate(world, random, pos);
				}
			}
		}else {
			if (random.nextInt(chance) == 0) {
				generator.generate(world, random, pos);
			}
		}
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0) {
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}

}
