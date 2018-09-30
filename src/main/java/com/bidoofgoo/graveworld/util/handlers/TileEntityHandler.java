package com.bidoofgoo.graveworld.util.handlers;

import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.TileEntitySoulExtractor;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySoulExtractor.class, new ResourceLocation("gwm:soul_extractor"));
	}
}
