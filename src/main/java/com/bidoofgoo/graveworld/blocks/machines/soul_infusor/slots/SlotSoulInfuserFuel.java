package com.bidoofgoo.graveworld.blocks.machines.soul_infusor.slots;

import com.bidoofgoo.graveworld.blocks.machines.soul_extractor.TileEntitySoulExtractor;
import com.bidoofgoo.graveworld.blocks.machines.soul_infusor.TileEntitySoulInfuser;
import com.bidoofgoo.graveworld.items.ItemAmulet;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotSoulInfuserFuel extends Slot
{
    public SlotSoulInfuserFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAmulet;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}