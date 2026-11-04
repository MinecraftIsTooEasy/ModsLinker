package org.moddedmite.modslinker.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemWarHammer;
import net.oilcake.mitelros.enchantment.Enchantments;

public class EnchantmentRestore extends Enchantment {
	protected EnchantmentRestore(int id, EnumRarity rarity, int difficulty) {
		super(id, rarity, difficulty);
	}

	@Override
	public int getNumLevels() {
		return 1;
	}

	@Override
	public String getNameSuffix() {
		return "restore";
	}

	@Override
	public boolean canApplyTogether(Enchantment other) {
		return super.canApplyTogether(other) && other.effectId != silkTouch.effectId && other != Enchantments.enchantmentAbsorb;
	}

	@Override
	public boolean canEnchantItem(Item item) {
		return item instanceof ItemPickaxe && !(item instanceof ItemWarHammer);
	}

	@Override
	public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
		return creativeModeTab == CreativeTabs.tabTools;
	}
}
