package org.moddedmite.modslinker.mixin.extreme;

import cn.wensc.mitemod.extreme.item.ItemRingKiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRingKiller.class)
public class ItemRingKillerMixin {
	@Shadow int level;
	
	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	public float getRingKillerSkillDamage() {
		if (this.level == 6) return 8.0F;
		return (float) this.level;
	}
}
