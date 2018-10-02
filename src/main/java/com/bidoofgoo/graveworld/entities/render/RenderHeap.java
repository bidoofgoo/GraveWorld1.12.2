package com.bidoofgoo.graveworld.entities.render;

import com.bidoofgoo.graveworld.entities.EntityHeap;
import com.bidoofgoo.graveworld.entities.models.ModelHeap;
import com.bidoofgoo.graveworld.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeap extends RenderLiving<EntityHeap>{

	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entity/heap.png");

	public RenderHeap(RenderManager manager) {
		super(manager, new ModelHeap(), .3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHeap entity) {
		// TODO Auto-generated method stub
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityHeap entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		// TODO Auto-generated method stub
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
