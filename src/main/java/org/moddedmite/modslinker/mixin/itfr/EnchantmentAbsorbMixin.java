package org.moddedmite.modslinker.mixin.itfr;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.Enchantment;
import net.oilcake.mitelros.enchantment.EnchantmentAbsorb;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentAbsorb.class)
public class EnchantmentAbsorbMixin {
	@ModifyReturnValue(method = "canApplyTogether", at = @At("RETURN"))
	private boolean conflictWithRestore(boolean original, Enchantment other) {
		return original && other != LinkerEnchantments.enchantmentRestore;
	}
}
