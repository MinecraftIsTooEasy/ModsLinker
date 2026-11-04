package org.moddedmite.modslinker.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemLeggings;

public class EnchantmentSwiftSneak extends Enchantment {
	protected EnchantmentSwiftSneak(int id, EnumRarity rarity, int difficulty) {
		super(id, rarity, difficulty);
	}

	@Override
	public int getNumLevels() {
		return 3;
	}

	@Override
	public String getNameSuffix() {
		return "swift_sneak";
	}

	@Override
	public boolean canEnchantItem(Item item) {
		return item instanceof ItemLeggings;
	}

	@Override
	public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
		return creativeModeTab == CreativeTabs.tabCombat;
	}
}
