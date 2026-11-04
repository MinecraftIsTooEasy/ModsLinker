package org.moddedmite.modslinker.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentUntouching;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentUntouching.class)
public class EnchantmentUntouchingMixin {
	@ModifyReturnValue(method = "canApplyTogether", at = @At("RETURN"))
	private boolean conflictWithRestore(boolean original, Enchantment other) {
		return original && other != LinkerEnchantments.enchantmentRestore;
	}
}
