package org.moddedmite.modslinker.mixin;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.entity.EntityZombieBoss;
import net.minecraft.AttributeInstance;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityCreature;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.IBossDisplayData;
import net.minecraft.Item;
import net.minecraft.ItemBattleAxe;
import net.minecraft.ItemCudgel;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.ItemTool;
import net.minecraft.ItemWarHammer;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.moddedmite.mitemod.bex.api.IBEXEvasions;
import net.moddedmite.mitemod.bex.entity.EntitySpiderQueen;
import net.moddedmite.mitemod.bex.register.BEXEnchantments;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.moddedmite.modslinker.LinkerConfigs;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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

	@Inject(method = "attackEntityAsMob(Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At("RETURN"))
	private void chipDamageThroughArmor(Entity target, CallbackInfoReturnable<EntityDamageResult> cir) {
		if (cir.getReturnValue() == null || !(target instanceof EntityPlayer player)) return;
		if (player.worldObj.isRemote || !player.isEntityAlive() || player.capabilities.disableDamage) return;
		if (ReflectHelper.dyCast(this) instanceof IBossDisplayData) return;
		for (ItemStack armor : player.getWornItems()) {
			if (armor != null && EnchantmentHelper.getEnchantmentLevel(BEXEnchantments.enchantmentUnassailable, armor) > 0) {
				return;
			}
		}
		DamageSource source = DamageSource.causeMobDamage(ReflectHelper.dyCast(this));
		if (this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() >= player.getProtectionFromArmor(source, true) ||
				this.getRNG().nextFloat() >= LinkerConfigs.MobArmorPiercingChance.getDoubleValue()) {
			player.onEntityDamaged(source, 1.0F);
		}
	}
}
