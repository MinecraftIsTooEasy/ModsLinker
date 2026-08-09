package org.moddedmite.modslinker.mixin.itfr;

import org.moddedmite.modslinker.LinkerConfigs;
import net.oilcake.mitelros.entity.boss.EntityLich;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLich.class)
public class EntityLichMixin {
	@ModifyConstant(method = "applyEntityAttributes", constant = @Constant(doubleValue = 75.0D))
	private double modifyLichHealth(double value) {
		return LinkerConfigs.LichHealth.getDoubleValue();
	}
}
