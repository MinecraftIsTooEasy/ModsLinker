package org.moddedmite.modslinker.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemHelmet;

public class EnchantmentDispel extends Enchantment {
	protected EnchantmentDispel(int id, EnumRarity rarity, int difficulty) {
		super(id, rarity, difficulty);
	}

	@Override
	public int getNumLevels() {
		return 1;
	}

	@Override
	public String getNameSuffix() {
		return "dispel";
	}

	@Override
	public boolean canEnchantItem(Item item) {
		return item instanceof ItemHelmet;
	}

	@Override
	public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
		return creativeModeTab == CreativeTabs.tabCombat;
	}
}
