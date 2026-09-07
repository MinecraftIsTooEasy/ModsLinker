package org.moddedmite.modslinker.mixin.extreme;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import net.minecraft.Damage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.moddedmite.mitemod.bex.api.IBEXEvasions;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityExchanger.class, priority = 2000)
public abstract class EntityExchangerMixin extends EntitySkeleton implements IBEXEvasions {
	protected EntityExchangerMixin(World world) {
		super(world);
	}

	public void disableEvasionFromPhaseCounter(Damage damage) {
		if (damage.getSource().getResponsibleEntity() instanceof EntityPlayer player) {
			ItemStack held = player.getHeldItemStack();
			if (held != null && EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentPhaseCounter.effectId, held) > 0) {
				this.bex$setNumEvasions(0);
			}
		}
	}
}
