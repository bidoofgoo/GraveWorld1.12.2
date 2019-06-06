package com.bidoofgoo.graveworld.util.handlers;

import com.bidoofgoo.graveworld.Main;
import com.bidoofgoo.graveworld.init.ModBlocks;
import com.bidoofgoo.graveworld.init.ModEntities;
import com.bidoofgoo.graveworld.init.ModItems;
import com.bidoofgoo.graveworld.util.IHasModel;
import com.bidoofgoo.graveworld.world.gen.WorldGenStructures;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event){
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event){
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event){
		for(Item item : ModItems.ITEMS){
			if(item instanceof IHasModel){
				((IHasModel) item).registerModel();
			}
		}

		for(Block block : ModBlocks.BLOCKS){
			if(block instanceof IHasModel){
				((IHasModel) block).registerModel();
			}
		} 
	}

	public static void preInitRegister() {
		ModEntities.registerEntities();
		RenderHandler.registryEntityRenders();
		GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
		SmeltingHandler.registerSmeltings();
	}

	public static void initRegister() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new DropHandler());
	}

	public static void postInitRegister() {

	}

}
