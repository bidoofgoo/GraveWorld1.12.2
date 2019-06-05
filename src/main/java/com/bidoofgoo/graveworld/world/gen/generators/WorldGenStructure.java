package com.bidoofgoo.graveworld.world.gen.generators;

import java.util.Random;

import com.bidoofgoo.graveworld.util.IStructure;
import com.bidoofgoo.graveworld.util.Reference;
import com.bidoofgoo.graveworld.world.gen.WorldGenStructures;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenStructure extends WorldGenerator implements IStructure{

	public String structureName;
	public int chance;
	public Block topBlock;
	public Class<?> biome;
	public boolean biomeSpecific = false; 
	
	public WorldGenStructure(String name, int chance, Block topBlock) {
		this.structureName = name;
		this.chance = chance;
		this.topBlock = topBlock;
		WorldGenStructures.worldStructures.add(this);
	}
	
	public WorldGenStructure(String name, int chance, Block topBlock, Class<?>... biome) {
		this.structureName = name;
		this.chance = chance;
		this.topBlock = topBlock;
		this.biome = this.biome;
		biomeSpecific = true;
		WorldGenStructures.worldStructures.add(this);
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateStructure(worldIn, position);
		return true;
	}
	
	public void generateStructure(World world, BlockPos pos) {
		MinecraftServer server = world.getMinecraftServer();
		TemplateManager manager= worldServer.getStructureTemplateManager();
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureName);
		Template template = manager.get(server, location);
		
		if (template != null){
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorld(world, pos, settings);
		}
			
	}
}
