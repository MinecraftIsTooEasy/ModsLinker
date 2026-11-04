package org.moddedmite.modslinker.mixin;

import net.minecraft.EntityLivingBase;
import net.minecraft.ItemStack;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
	@Shadow public abstract ItemStack getBoots();
	@Shadow public abstract float getAIMoveSpeed();

	@ModifyConstant(method = "moveEntityWithHeading", constant = @Constant(doubleValue = 0.800000011920929D, ordinal = 0))
	private double farTravelWaterDragX(double constant) {
		float efficiency = LinkerModifierTypes.getFarTravelEfficiency(this.getBoots());
		return efficiency > 0.0F ? constant + (0.546D - constant) * efficiency : constant;
	}

	@ModifyConstant(method = "moveEntityWithHeading", constant = @Constant(doubleValue = 0.800000011920929D, ordinal = 2))
	private double farTravelWaterDragZ(double constant) {
		float efficiency = LinkerModifierTypes.getFarTravelEfficiency(this.getBoots());
		return efficiency > 0.0F ? constant + (0.546D - constant) * efficiency : constant;
	}

	@ModifyArg(method = "moveEntityWithHeading", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;moveFlying(FFF)V", ordinal = 0), index = 2)
	private float farTravelWaterAcceleration(float acceleration) {
		float efficiency = LinkerModifierTypes.getFarTravelEfficiency(this.getBoots());
		return efficiency > 0.0F ? acceleration + (this.getAIMoveSpeed() - acceleration) * efficiency : acceleration;
	}
}
