package org.moddedmite.modslinker.mixin;

import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import net.oilcake.mitelros.mixin.interfaces.ITFFoodStats;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public class EntityLivingMixin {
	@Inject(method = "onMeleeAttacked(Lnet/minecraft/EntityLivingBase;Lnet/minecraft/EntityDamageResult;)V", at = @At("HEAD"))
	private void natureBlessingAddWater(EntityLivingBase attacker, EntityDamageResult result, CallbackInfo c) {
		if (result != null && attacker != null && result.entityLostHealth() && attacker instanceof EntityPlayer player) {
			ItemStack stack = attacker.getHeldItemStack();
			if (stack != null) {
				ItemStack heldItemStack = player.getHeldItemStack();
				float bless_of_nature = 0;
				if (heldItemStack != null) {
					bless_of_nature = BEXToolModifierTypes.NATURE_BLESSING.getModifierValue(heldItemStack.getTagCompound());
				}
				if ((double) bless_of_nature > Math.random() * 5) {
					((ITFFoodStats) player.getFoodStats()).itf$AddWater(1);
				}
			}
		}
	}

	@Inject(method = "onMeleeAttacked(Lnet/minecraft/EntityLivingBase;Lnet/minecraft/EntityDamageResult;)V", at = @At("HEAD"))
	private void disintegrationAddWeakness(EntityLivingBase attacker, EntityDamageResult result, CallbackInfo c) {
		if (result != null && attacker instanceof EntityPlayer player && result.entityLostHealth()) {
			ItemStack held = player.getHeldItemStack();
			if (held != null) {
				int level = LinkerModifierTypes.DISINTEGRATION.getModifierLevel(held.getTagCompound());
				if (level > 0) {
					((EntityLivingBase)(Object)this).addPotionEffect(new PotionEffect(Potion.weakness.id, level * 100, 0));
				}
			}
		}
	}
}
