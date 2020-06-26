package com.dbydd.micro_machinery.items.foods;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Golden_Apple_Food extends ItemFood implements IHasModel {

	public Golden_Apple_Food(int amount, float saturation, String name, boolean iseatable) {
		super(amount, saturation, false);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Micro_Machinery.Micro_Machinery);
		if (iseatable = true) {
			setAlwaysEdible();
		}

		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Micro_Machinery.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);

		player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 600, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2400, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400, 1));
	}

}
