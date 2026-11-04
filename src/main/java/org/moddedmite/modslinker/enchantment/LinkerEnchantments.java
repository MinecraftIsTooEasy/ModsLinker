package org.moddedmite.modslinker.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EnumRarity;
import net.minecraft.ItemStack;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class LinkerEnchantments {
	public static final Enchantment enchantmentShatter = new EnchantmentShatter(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 15);
	public static final Enchantment enchantmentPhaseCounter = new EnchantmentPhaseCounter(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 300);
	public static final Enchantment enchantmentRestore = new EnchantmentRestore(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 30);
	public static final Enchantment enchantmentDispel = new EnchantmentDispel(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
	public static final Enchantment enchantmentSwiftSneak = new EnchantmentSwiftSneak(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 10);

	public static void register(EnchantmentRegistryEvent event) {
		event.registerEnchantment(enchantmentShatter, enchantmentPhaseCounter, enchantmentRestore, enchantmentDispel, enchantmentSwiftSneak);
	}
}
