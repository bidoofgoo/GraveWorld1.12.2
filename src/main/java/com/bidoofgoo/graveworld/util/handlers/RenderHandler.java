package com.bidoofgoo.graveworld.util.handlers;

import com.bidoofgoo.graveworld.entities.EntityHeap;
import com.bidoofgoo.graveworld.entities.models.ModelHeap;
import com.bidoofgoo.graveworld.entities.render.RenderHeap;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registryEntityRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityHeap.class, new IRenderFactory<EntityHeap>() {

			@Override
			public Render<? super EntityHeap> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderHeap(manager);
			}
		});
	}
}
