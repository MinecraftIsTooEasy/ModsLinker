package org.moddedmite.modslinker.mixin.itfr;

import org.moddedmite.modslinker.LinkerConfigs;
import net.oilcake.mitelros.event.listener.OreGenerationRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(OreGenerationRegistry.class)
public class OreGenerationRegistryMixin {
	@ModifyConstant(method = "accept*", constant = @Constant(intValue = 15))
	private int modifyOverworldNickelFrequency(int value) {
		return LinkerConfigs.OverworldNickelFrequency.getIntegerValue();
	}
	
	@ModifyConstant(method = "accept*", constant = @Constant(intValue = 25))
	private int modifyUnderworldNickelFrequency(int value) {
		return LinkerConfigs.UnderworldNickelFrequency.getIntegerValue();
	}

	@ModifyConstant(method = "accept*", constant = @Constant(intValue = 5))
	private int modifyUnderworldTungstenFrequency(int value) {
		return LinkerConfigs.UnderworldTungstenFrequency.getIntegerValue();
	}
}
