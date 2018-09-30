package com.bidoofgoo.graveworld.util.handlers;


import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.ContainerSoulExtractor;
import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.GuiSoulExtractor;
import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.TileEntitySoulExtractor;
import com.bidoofgoo.graveworld.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import scala.reflect.internal.Trees.New;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SOUL_EXTRACTOR) return new ContainerSoulExtractor(player.inventory, (TileEntitySoulExtractor)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_SOUL_EXTRACTOR) {
			return new GuiSoulExtractor(player.inventory, (TileEntitySoulExtractor)world.getTileEntity(new BlockPos(x,y,z)));
		}
		return null;
	}

}
