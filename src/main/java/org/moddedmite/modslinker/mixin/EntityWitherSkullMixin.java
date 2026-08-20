package org.moddedmite.modslinker.mixin;

import net.minecraft.EntityWitherSkull;
import org.moddedmite.modslinker.LinkerConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityWitherSkull.class)
public class EntityWitherSkullMixin {
	@ModifyConstant(method = "onImpact", constant = @Constant(floatValue = 8.0F))
	private float modifyDamage(float damage) {
		return (float) LinkerConfigs.WitherDamage.getDoubleValue();
	}
}
