package org.moddedmite.modslinker.mixin;

import net.minecraft.Damage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityPhaseSpider;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityWoodSpider;
import net.minecraft.ItemStack;
import net.minecraft.World;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPhaseSpider.class)
public abstract class EntityPhaseSpiderMixin extends EntityWoodSpider {
	protected EntityPhaseSpiderMixin(World world) {
		super(world);
	}

	@Shadow int num_evasions;

	@Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
	private void disableEvasionFromPhaseCounter(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
		Entity responsible = damage.getSource().getResponsibleEntity();
		if (responsible instanceof EntityPlayer) {
			ItemStack held = ((EntityPlayer) responsible).getHeldItemStack();
			if (held != null && EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentPhaseCounter.effectId, held) > 0) {
				this.num_evasions = 0;
				cir.setReturnValue(super.attackEntityFrom(damage));
			}
		}
	}
}
