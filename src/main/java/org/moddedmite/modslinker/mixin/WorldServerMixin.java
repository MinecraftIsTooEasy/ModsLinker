package org.moddedmite.modslinker.mixin;

import net.minecraft.Curse;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityWitch;
import net.minecraft.ServerPlayer;
import net.minecraft.WorldServer;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public class WorldServerMixin {
	@Inject(method = "addCurse", at = @At("HEAD"), cancellable = true)
	private void dispelCurse(ServerPlayer player, EntityWitch witch, Curse curse, int ticksDelay, CallbackInfo ci) {
		if (EnchantmentHelper.hasEnchantment(player.getHelmet(), LinkerEnchantments.enchantmentDispel)) {
			ci.cancel();
		}
	}
}
