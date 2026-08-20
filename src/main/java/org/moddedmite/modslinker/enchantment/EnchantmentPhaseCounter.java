package org.moddedmite.modslinker.enchantment;

import net.minecraft.*;
import net.oilcake.mitelros.mixin.interfaces.ITFEnchantment;
import net.xiaoyu233.mitemod.miteite.api.ITEItem;

public class EnchantmentPhaseCounter extends Enchantment implements ITFEnchantment {
	protected EnchantmentPhaseCounter(int id, EnumRarity rarity, int difficulty) {
		super(id, rarity, difficulty);
	}

	@Override
	public int getNumLevels() {
		return 1;
	}

	@Override
	public String getNameSuffix() {
		return "phase_counter";
	}

	@Override
	public boolean canEnchantItem(Item item) {
		return ((ITEItem) item).isWeapon(item);
	}

	@Override
	public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
		return creativeModeTab == CreativeTabs.tabCombat;
	}
	
	@Override
	public boolean isTreasure() {
		return false;
	}
}
