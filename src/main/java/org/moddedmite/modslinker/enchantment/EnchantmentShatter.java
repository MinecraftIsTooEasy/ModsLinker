package org.moddedmite.modslinker.enchantment;

import net.minecraft.*;

public class EnchantmentShatter extends Enchantment {
	protected EnchantmentShatter(int id, EnumRarity rarity, int difficulty) {
		super(id, rarity, difficulty);
	}

	@Override
	public int getNumLevels() {
		return 1;
	}

	@Override
	public String getNameSuffix() {
		return "shatter";
	}

	@Override
	public boolean canEnchantItem(Item item) {
		return item instanceof ItemPickaxe;
	}

	@Override
	public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
		return creativeModeTab == CreativeTabs.tabTools;
	}
}
