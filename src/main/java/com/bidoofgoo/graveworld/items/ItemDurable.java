package com.bidoofgoo.graveworld.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDurable extends ItemBase{

	public ItemDurable(String name, int uses) {
		super(name);
		
		this.setMaxDamage(uses);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		// TODO Auto-generated method stub
		stack.damageItem(1, player);
		return super.onLeftClickEntity(stack, player, entity);
	}

}
