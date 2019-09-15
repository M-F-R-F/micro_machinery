package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.blocks.machine.BlockKlin;
import com.dbydd.micro_machinery.recipes.KlinRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Objects;

public class TileEntityKlin extends TileEntity implements IInventory, ITickable, IFluidTank{

	private ItemStackHandler handler = new ItemStackHandler(5);
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	private IFluidHandler tank = new FluidTank(8000);
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 200;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	/**
	 * Get the name of this object. For players this returns their username
	 */
	@Override
	public String getName() {
		return null;
	}

	public boolean hasCustomName()
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.klin");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime(this.handler.getStackInSlot(2));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityKlin te) 
	{
		return te.getField(0) > 0;
	}
	
	public void update() 
	{	
		if(this.isBurning())
		{
			--this.burnTime;
			BlockKlin.setState(true, world, pos);
		}
		
		ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0), handler.getStackInSlot(1)};
		ItemStack fuel = this.handler.getStackInSlot(2);
		
		if(this.isBurning() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty())
		{
			if(!this.isBurning() && this.canSmelt())
			{
				this.burnTime = getItemBurnTime(fuel);
				this.currentBurnTime = burnTime;
				
				if(this.isBurning() && !fuel.isEmpty())
				{
					Item item = fuel.getItem();
					fuel.shrink(1);
					
					if(fuel.isEmpty())
					{
						ItemStack item1 = item.getContainerItem(fuel);
						this.handler.setStackInSlot(2, item1);
					}
				}
			}
		}
		
		if(this.isBurning() && this.canSmelt() && cookTime > 0)
		{
			cookTime++;
			if(cookTime == totalCookTime)
			{
				if(handler.getStackInSlot(3).getCount() > 0)
				{
					handler.getStackInSlot(3).grow(1);
				}
				else
				{
					handler.insertItem(3, smelting, false);
				}
				
				smelting = ItemStack.EMPTY;
				cookTime = 0;
				return;
			}
		}
		else
		{
			if(this.canSmelt() && this.isBurning())
			{
				ItemStack output = KlinRecipes.getInstance().getKlinResult(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]);
				if(!output.isEmpty())
				{
					smelting = output;
					cookTime++;
					inputs[0].shrink(1);
					inputs[1].shrink(1);
					inputs[2].shrink(1);
					inputs[3].shrink(1);
					inputs[4].shrink(1);
					handler.setStackInSlot(0, inputs[0]);
					handler.setStackInSlot(1, inputs[1]);
					handler.setStackInSlot(2, inputs[2]);
					handler.setStackInSlot(3, inputs[3]);
					handler.setStackInSlot(4, inputs[4]);
				}
			}
		}
	}

	private boolean canSmelt() {
		if (this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty()) return false;
		else 
		{
			ItemStack result = KlinRecipes.getInstance().getKlinResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));	
			if(result.isEmpty()) return false;
			else {
				ItemStack output = this.handler.getStackInSlot(3);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) 
			{
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB) return 150;
				if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				if (block == Blocks.COAL_BLOCK) return 16000;
			}

			if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
			if (item == Items.STICK) return 100;
			if (item == Items.COAL) return 1600;
			if (item == Items.LAVA_BUCKET) return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if (item == Items.BLAZE_ROD) return 2400;

			return GameRegistry.getFuelValue(fuel);
		}
	}
		
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns the stack in the given slot.
	 *
	 * @param index
	 */
	@Override
	public ItemStack getStackInSlot(int index) {
		return handler.getStackInSlot(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
	 *
	 * @param index
	 * @param count
	 */
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 *
	 * @param index
	 */
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = handler.getStackInSlot(index);
		handler.setStackInSlot(index, ItemStack.EMPTY);
		return stack;
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 *
	 * @param index
	 * @param stack
	 */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		handler.setStackInSlot(index, Objects.requireNonNull(stack));
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
	 */
	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
	 * guis use Slot.isItemValid
	 *
	 * @param index
	 * @param stack
	 */
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
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
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
			case 3:
				this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	/**
	 * @return FluidStack representing the fluid in the tank, null if the tank is empty.
	 */
	@Nullable
	@Override
	public FluidStack getFluid() {
		return null;
	}

	/**
	 * @return Current amount of fluid in the tank.
	 */
	@Override
	public int getFluidAmount() {
		return 0;
	}

	/**
	 * @return Capacity of this fluid tank.
	 */
	@Override
	public int getCapacity() {
		return 0;
	}

	/**
	 * Returns a wrapper object {@link FluidTankInfo } containing the capacity of the tank and the
	 * FluidStack it holds.
	 * <p>
	 * Should prevent manipulation of the IFluidTank. See {@link FluidTank}.
	 *
	 * @return State information for the IFluidTank.
	 */
	@Override
	public FluidTankInfo getInfo() {
		return null;
	}

	/**
	 * @param resource FluidStack attempting to fill the tank.
	 * @param doFill   If false, the fill will only be simulated.
	 * @return Amount of fluid that was accepted by the tank.
	 */
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	/**
	 * @param maxDrain Maximum amount of fluid to be removed from the container.
	 * @param doDrain  If false, the drain will only be simulated.
	 * @return Amount of fluid that was removed from the tank.
	 */
	@Nullable
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}
}
