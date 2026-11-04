package org.moddedmite.modslinker.mixin;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemAppleGold;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAppleGold.class)
public class ItemAppleGoldMixin {
	@Inject(method = "onEaten", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;addPotionEffect(Lnet/minecraft/PotionEffect;)V", ordinal = 0))
	private void injectS(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, CallbackInfo ci) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 1200, 0));
	}
}
