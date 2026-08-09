package org.moddedmite.modslinker.mixin.extreme;

import cn.wensc.mitemod.extreme.events.listener.EXLootTable;
import org.moddedmite.modslinker.LinkerConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EXLootTable.class)
public class EXLootTableMixin {
	@ModifyConstant(method = "onFishingRegister", constant = @Constant(intValue = 2))
	private int modifyFishingWeight(int original) {
		return LinkerConfigs.FishVoucherFrequency.getIntegerValue();
	}
}
