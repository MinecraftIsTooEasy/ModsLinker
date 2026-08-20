package org.moddedmite.modslinker.mixin.itfr;

import net.minecraft.Damage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.oilcake.mitelros.entity.mob.EntityLichShadow;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLichShadow.class)
public abstract class EntityLichShadowMixin extends EntitySkeleton {
	protected EntityLichShadowMixin(World world) {
		super(world);
	}

	@Shadow private int num_evasions;

	@Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
	private void disableEvasionFromPhaseCounter(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
		if (damage.getSource().getResponsibleEntity() instanceof EntityPlayer player) {
			ItemStack held = player.getHeldItemStack();
			if (held != null && EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentPhaseCounter.effectId, held) > 0) {
				this.num_evasions = 0;
				cir.setReturnValue(super.attackEntityFrom(damage));
			}
		}
	}
}
