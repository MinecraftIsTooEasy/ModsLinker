package org.moddedmite.modslinker.mixin;

import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.IMob;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import net.oilcake.mitelros.mixin.interfaces.ITFFoodStats;
import net.xiaoyu233.fml.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.item.enchantment.MITEITEEnchantmentRegistryInit;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class EntityLivingMixin extends EntityLivingBase {
	public EntityLivingMixin(World par1World) {
		super(par1World);
	}

	@Shadow public abstract ItemStack getHeldItemStack();
	@Shadow public abstract float getReach();
	@Shadow public abstract boolean hasLineOfStrike(Entity target);
	
	@Inject(method = "hasLineOfStrikeAndTargetIsWithinStrikingDistance", at = @At("RETURN"), cancellable = true)
	private void extendMobAttackReach(EntityLivingBase target, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ() || target == null) return;
		if (!(ReflectHelper.dyCast(this) instanceof IMob)) return;
		int level = EnchantmentHelper.getEnchantmentLevel(MITEITEEnchantmentRegistryInit.EXTEND, this.getHeldItemStack());
		if (this.getDistance(target.posX, target.boundingBox.minY, target.posZ) <= (double) this.getReach() + 0.5D * level && this.hasLineOfStrike(target)) {
			cir.setReturnValue(true);
		}
	}

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
