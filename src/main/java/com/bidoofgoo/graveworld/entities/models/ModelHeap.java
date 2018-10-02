package com.bidoofgoo.graveworld.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelHeap - Bidoofgoo
 * Created using Tabula 7.0.0
 */
public class ModelHeap extends AdvancedModelBase {
    public AdvancedModelRenderer Pivot;
    public AdvancedModelRenderer Glob1;
    public AdvancedModelRenderer Glob2;
    public AdvancedModelRenderer Glob3;
    public AdvancedModelRenderer SkeletonPivot;
    public AdvancedModelRenderer Organ;
    public AdvancedModelRenderer Bone1;
    public AdvancedModelRenderer Bone2;
    public AdvancedModelRenderer Bone3;

    public ModelHeap() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Glob2 = new AdvancedModelRenderer(this, 0, 5);
        this.Glob2.setRotationPoint(-0.4F, 0.0F, 3.4F);
        this.Glob2.addBox(-0.3F, -3.9F, -0.8F, 6, 5, 4, 0.0F);
        this.setRotateAngle(Glob2, 0.44453536048295567F, 0.601440460237246F, 0.0F);
        this.Bone1 = new AdvancedModelRenderer(this, 0, 0);
        this.Bone1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bone1.addBox(2.4F, -0.9F, 0.0F, 1, 4, 1, 0.0F);
        this.Bone3 = new AdvancedModelRenderer(this, 0, 0);
        this.Bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bone3.addBox(1.8F, 0.0F, 2.1F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Bone3, 0.0F, 0.0F, 0.06648875152605485F);
        this.Glob1 = new AdvancedModelRenderer(this, 20, 9);
        this.Glob1.setRotationPoint(0.8F, 0.0F, -4.6F);
        this.Glob1.addBox(-4.2F, -6.8F, -1.8F, 8, 7, 5, 0.0F);
        this.setRotateAngle(Glob1, -0.7655014099247129F, 0.0F, 0.0F);
        this.SkeletonPivot = new AdvancedModelRenderer(this, 0, 0);
        this.SkeletonPivot.setRotationPoint(0.0F, -7.0F, 0.6F);
        this.SkeletonPivot.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(SkeletonPivot, 0.0F, -2.614503219487506F, -0.1387536755335492F);
        this.Organ = new AdvancedModelRenderer(this, 0, 14);
        this.Organ.setRotationPoint(0.0F, -3.6F, -1.8F);
        this.Organ.addBox(-0.5F, -1.7F, -0.9F, 3, 3, 1, 0.0F);
        this.setRotateAngle(Organ, 0.0F, 0.0F, 0.4363323129985824F);
        this.Pivot = new AdvancedModelRenderer(this, 0, 0);
        this.Pivot.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.Pivot.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.Glob3 = new AdvancedModelRenderer(this, 16, 0);
        this.Glob3.setRotationPoint(-2.7F, 0.0F, -1.2F);
        this.Glob3.addBox(0.0F, -3.9F, 0.0F, 6, 5, 4, 0.0F);
        this.setRotateAngle(Glob3, 0.4363323129985824F, -0.7696902001294993F, 0.0F);
        this.Bone2 = new AdvancedModelRenderer(this, 0, 0);
        this.Bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bone2.addBox(1.8F, 0.0F, -2.1F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Bone2, 0.0F, 0.0F, -0.15514042022746133F);
        this.Pivot.addChild(this.Glob2);
        this.SkeletonPivot.addChild(this.Bone1);
        this.SkeletonPivot.addChild(this.Bone3);
        this.Pivot.addChild(this.Glob1);
        this.Pivot.addChild(this.SkeletonPivot);
        this.Glob1.addChild(this.Organ);
        this.Pivot.addChild(this.Glob3);
        this.SkeletonPivot.addChild(this.Bone2);

        updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Pivot.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	resetToDefaultPose();

    	float idleTime = entityIn.ticksExisted;
    	float idleAmount = 0.5f;

//    	limbSwing = idleTime;
//    	limbSwingAmount = idleAmount;

    	float globalspeed = 0.2f;
    	float globalHeight = 1;
    	float globalDegree = 1;

    	bob(Pivot, 1 * globalspeed, .35f * globalHeight, false, idleTime, idleAmount);
    	walk(Organ, 1 * globalspeed, .25f * globalHeight, false, 0, 0.2f, idleTime * 2, idleAmount);
    	walk(Glob1, 1 * globalspeed, globalDegree, false, 0, 0.2f, limbSwing, limbSwingAmount * 0.2f);
    	walk(Glob2, 1* globalspeed, -globalDegree, false, 0, 0.2f, limbSwing, limbSwingAmount * 0.2f);
    	walk(Glob3, 1* globalspeed, -globalDegree, false, 0, 0.2f, limbSwing, limbSwingAmount * 0.2f);
    	walk(SkeletonPivot, 1* globalspeed, 0.5f * globalDegree, false, 0, 0.2f, limbSwing, limbSwingAmount * 0.4f);
    }
}
