package org.moddedmite.modslinker;

import net.xiaoyu233.mitemod.miteite.api.ITEPlugin;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import org.moddedmite.modslinker.register.LinkerModifierTypes;

public class LinkerITEPlugin implements ITEPlugin {
	@Override
	public void register(ITERegistry registry) {
		registry.registerArmorModifier(LinkerModifierTypes.ATMOSPHERIC_SHIELD);
		registry.registerArmorModifier(LinkerModifierTypes.HOLY_SPIRIT);
		registry.registerArmorModifier(LinkerModifierTypes.STONE_SKIN);
		registry.registerArmorModifier(LinkerModifierTypes.ANTI_CORROSION);
		registry.registerToolModifier(LinkerModifierTypes.SLAUGHTER);
		registry.registerToolModifier(LinkerModifierTypes.DISINTEGRATION);
	}
}
