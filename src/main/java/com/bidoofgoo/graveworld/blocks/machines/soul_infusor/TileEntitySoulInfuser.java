package com.bidoofgoo.graveworld.blocks.machines.soul_infusor;

import java.lang.reflect.InvocationTargetException;

import com.bidoofgoo.graveworld.blocks.machines.soul_infusor.SoulInfuserRecipes.SoulInfuserRecipe;
import com.bidoofgoo.graveworld.init.ModItems;
import com.bidoofgoo.graveworld.items.ItemSoul;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.This;

public class TileEntitySoulInfuser extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the furnace */
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    /** The number of ticks that the furnace will keep burning */
    private int furnaceBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.furnaceItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.furnaceItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if ((index == 0 || index == 1 || index == 2) && !flag)
        {
        	ItemStack itemin = this.furnaceItemStacks.get(1);
            ItemStack soul = this.furnaceItemStacks.get(0);
            ItemStack itemfuel = this.furnaceItemStacks.get(2);
        
            this.cookTime = 0;
            this.totalCookTime = SoulInfuserRecipes.instance().getTotalCookTime(itemin, soul, itemfuel);
            
            System.out.println("set totalcooktime to " + this.totalCookTime +" and reset the current timer back to 0");
            
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.furnaceCustomName : "container.soul_infuser";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.furnaceCustomName = p_145951_1_;
    }

    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntitySoulInfuser.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(2));

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        //return this.furnaceBurnTime > 0;
    	return this.canSmelt();
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        boolean canSmelt = this.canSmelt();

        if (canSmelt)
        {
            this.furnaceBurnTime = 10;
        }else {
        	this.furnaceBurnTime = 0;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.furnaceItemStacks.get(2);

            if (canSmelt || !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(0)).isEmpty())
            {
                if (canSmelt)
                {
                    this.furnaceBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (canSmelt)
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.furnaceItemStacks.set(2, item1);
                            }
                        }
                    }
                }

                if (canSmelt())
                {
                    ++this.cookTime;
                    System.out.println(this.cookTime + " out of " + this.totalCookTime);

                    if (this.cookTime == this.totalCookTime)
                    {
                    	ItemStack itemin = this.furnaceItemStacks.get(1);
                        ItemStack soul = this.furnaceItemStacks.get(0);
                        ItemStack itemfuel = this.furnaceItemStacks.get(2);
                    
                        this.cookTime = 0;
                        this.totalCookTime = SoulInfuserRecipes.instance().getTotalCookTime(itemin, soul, itemfuel);
                        System.out.println("Set totalCooktime to " + this.totalCookTime);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!canSmelt && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != canSmelt)
            {
                flag1 = true;
                BlockSoulInfuser.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack)
    {
        return 200;
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (((ItemStack)this.furnaceItemStacks.get(0)).isEmpty() || ((ItemStack)this.furnaceItemStacks.get(1)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = SoulInfuserRecipes.instance().getInfusingResult(this.furnaceItemStacks.get(1), this.furnaceItemStacks.get(0), this.furnaceItemStacks.get(2));
            SoulInfuserRecipe recipe = SoulInfuserRecipes.instance().getInfuserRecipeByStuff(this.furnaceItemStacks.get(1), this.furnaceItemStacks.get(0), this.furnaceItemStacks.get(2));
            
            if (recipe == null)
            {
                return false;
            }
            else
            {
                ItemStack outputstack1 = this.furnaceItemStacks.get(3);

                boolean part1 = (outputstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && outputstack1.getCount() + itemstack.getCount() <= outputstack1.getMaxStackSize());
                
                if (outputstack1.isEmpty())
                {
                    return true;
                }
                else if (!outputstack1.isItemEqual(itemstack))
                {
                	System.out.println("Items not equal");
                    return false;
                }
                else if (part1)  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return (outputstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize()); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
    	System.out.println("Smelting item!");
        if (this.canSmelt())
        {
            ItemStack itemin = this.furnaceItemStacks.get(1);
            ItemStack soul = this.furnaceItemStacks.get(0);
            ItemStack itemfuel = this.furnaceItemStacks.get(2);
            ItemStack resultSpot = this.furnaceItemStacks.get(3);
            
            SoulInfuserRecipe recipe = SoulInfuserRecipes.instance().getInfuserRecipeByStuff(itemin, soul, itemfuel);
            
            if (!recipe.spawnCreature) {
            	if (resultSpot.isEmpty())
                {
                    this.furnaceItemStacks.set(3, recipe.result.copy());
                }
                else if (resultSpot.getItem() == recipe.result.getItem())
                {
                	resultSpot.grow(recipe.result.getCount());
                }
			}else {
				try {
					Entity entity = recipe.entityClass.getDeclaredConstructor(World.class).newInstance(this.world);
					entity.setLocationAndAngles((double)this.pos.getX() + 0.5, this.pos.getY() + 1, (double) this.pos.getZ() + .5, 0, 0);
					this.world.spawnEntity(entity);
					if(entity instanceof EntityLiving) {
						((EntityLiving) entity).spawnExplosionParticle();
					}
					this.world.playEvent(2004, this.pos, 0);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}

            itemin.shrink(1);
            soul.shrink(1);
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            Item[] item = {stack.getItem()};
            int[] returnInt = {0};

            SoulInfuserRecipes.instance().getSmeltingList().forEach(recipe->{
            	if (recipe.fuel.getItem() == item[0]) {
					returnInt[0] = 10;
				}
            });
            
            return returnInt[0];
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }
    
    public void dropInventory() {
    	if(!this.world.isRemote) {
    		for (ItemStack stack : furnaceItemStacks) {
        		Entity droppedEntity = new EntityItem(world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), stack);
    			this.world.spawnEntity(droppedEntity);
    		}
    	}
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 2)
        {
            return false;
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.furnaceItemStacks.get(2);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return "gwm:soul-infuser";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerSoulInfuser(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        this.furnaceItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

	public static boolean isItemSoul(ItemStack stack) {
		if (ItemSoul.allSouls.contains(stack.getItem())) {
			System.out.println(stack.getItem().getUnlocalizedName() + " is a soul!");
		}else {
			System.out.println(stack.getItem().getUnlocalizedName() + " is not a soul!");
		}
		return ItemSoul.allSouls.contains(stack.getItem());
	}
}