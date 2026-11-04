package org.moddedmite.modslinker.mixin.extreme;

import cn.wensc.mitemod.extreme.entity.EntityZombieBoss;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.moddedmite.modslinker.LinkerConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityZombieBoss.class)
public class EntityZombieBossMixin {
    @ModifyVariable(method = "dropFewItems", at = @At("STORE"), name = "nums")
    private int modifyNums(int originalNums) {
        return Math.min(LinkerConfigs.ZombieBossDropsCount.getIntegerValue(), originalNums);
    }

	@ModifyExpressionValue(method = "applyEntityAttributes", at = @At(value = "CONSTANT", args = "intValue=20"))
	private int modifyHealth(int original) {
		return LinkerConfigs.ZombieBossHealth.getIntegerValue();
	}
}
