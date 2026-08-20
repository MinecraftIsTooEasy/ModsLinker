package org.moddedmite.modslinker.mixin.itfr;

import net.minecraft.Item;
import net.minecraft.ItemBattleAxe;
import net.oilcake.mitelros.enchantment.EnchantmentSweeping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentSweeping.class)
public class EnchantmentSweepingMixin {
	@Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
	private void addBattleAxeToEnchant(Item item, CallbackInfoReturnable<Boolean> cir) {
		if (item instanceof ItemBattleAxe) cir.setReturnValue(true);
	}
}
