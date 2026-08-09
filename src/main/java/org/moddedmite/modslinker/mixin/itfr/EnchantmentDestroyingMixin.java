package org.moddedmite.modslinker.mixin.itfr;

import cn.wensc.mitemod.extreme.item.ItemClubMetal;
import net.minecraft.Item;
import net.oilcake.mitelros.enchantment.EnchantmentDestroying;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentDestroying.class)
public class EnchantmentDestroyingMixin {
	@Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
	private void applyClubToEnchant(Item item, CallbackInfoReturnable<Boolean> cir) {
		if (item instanceof ItemClubMetal) {
			cir.setReturnValue(true);
		}
	}
}
