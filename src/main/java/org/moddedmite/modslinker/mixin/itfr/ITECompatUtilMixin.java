package org.moddedmite.modslinker.mixin.itfr;

import org.moddedmite.modslinker.LinkerConfigs;
import net.minecraft.World;
import net.oilcake.mitelros.ModReference;
import net.oilcake.mitelros.compat.ITECompatUtil;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ITECompatUtil.class)
public class ITECompatUtilMixin {

	/**
	 * @author Xy_Luce
	 * @reason
	 */
	@Overwrite
	public static double getAttribute(int day, double base, double ratio) {
		if (!ModReference.hasMod(ModReference.ITE)) {
			return base;
		}
		return base * 2.0D + day * ratio * LinkerConfigs.RateMultiplier.getDoubleValue();
	}
	
	/**
	 * @author Xy_Luce
	 * @reason
	 */
	@Overwrite
	public static int getITEDay(World world) {
		return Math.min(Configs.Entities.ENHANCE_LIMIT.get(), World.getDayOfWorld(world.getWorldInfo().getWorldTotalTime(0)));
	}
}