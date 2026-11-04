package org.moddedmite.modslinker.mixin.itfr;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemGoldenAppleLegend.class)
public class ItemGoldenAppleLegendMixin {
	@Inject(method = "onEaten", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;addPotionEffect(Lnet/minecraft/PotionEffect;)V", ordinal = 2))
	private void injectS(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, CallbackInfo ci) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 3600, 0));
	}
}
