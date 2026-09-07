package org.moddedmite.modslinker.mixin;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import net.minecraft.Damage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityCreature;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.moddedmite.mitemod.bex.api.IBEXEvasions;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityMob.class)
public class EntityMobMixin extends EntityCreature {
	public EntityMobMixin(World par1World) {
		super(par1World);
	}
	
	@Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
	private void disableEvasionFromPhaseCounter(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
		if (ReflectHelper.dyCast(this) instanceof EntityExchanger exchanger) {
			if (damage.getSource().getResponsibleEntity() instanceof EntityPlayer player) {
				ItemStack held = player.getHeldItemStack();
				if (held != null && EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentPhaseCounter.effectId, held) > 0) {
					((IBEXEvasions) exchanger).bex$setNumEvasions(0);
					cir.setReturnValue(super.attackEntityFrom(damage));
				}
			}
		}
	}
}
