package org.moddedmite.modslinker.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Damage;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityWither;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import net.moddedmite.mitemod.bex.register.BEXItems;
import org.moddedmite.modslinker.LinkerConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityWither.class)
public class EntityWitherMixin {
	@ModifyConstant(method = "applyEntityAttributes", constant = @Constant(doubleValue = 300.0F))
	private double modifyWitherHealth(double constant) {
		return LinkerConfigs.WitherHealth.getDoubleValue();
	}
	
	@WrapOperation(method = "attackEntityFrom",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/EntityMob;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
	private EntityDamageResult avoidInfSwordDamage(EntityWither instance, Damage damage, Operation<EntityDamageResult> original) {
		ItemStack item_stack = damage.getSource().getItemAttackedWith();
		if (item_stack != null && item_stack.getItem() instanceof ItemInfinitySword && item_stack.itemID == BEXItems.infinitySword.itemID) {
			return null;
		}
		return original.call(instance, damage);
	}
	
	
	@ModifyConstant(method = "func_82206_m", constant = @Constant(intValue = 220))
	private int modifyHealth(int constant) {
		return (int) (LinkerConfigs.WitherHealth.getDoubleValue() / 3);
	}
	
	@ModifyConstant(method = "updateAITasks", constant = @Constant(floatValue = 10.0F, ordinal = 0))
	private float modifyHeal(float constant) {
		return (float) (LinkerConfigs.WitherHealth.getDoubleValue() / 30);
	}
}
