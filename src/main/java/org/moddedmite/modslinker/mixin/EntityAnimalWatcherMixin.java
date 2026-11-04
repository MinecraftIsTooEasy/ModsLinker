package org.moddedmite.modslinker.mixin;

import cn.wensc.mitemod.extreme.entity.EntityZombieBoss;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.EntityAnimalWatcher;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityAnimalWatcher.class)
public class EntityAnimalWatcherMixin {
	@ModifyReturnValue(method = "getCooloffForBlock", at = @At("RETURN"))
	private int speedUpZombieBossDigging(int original) {
		if (ReflectHelper.dyCast(this) instanceof EntityZombieBoss) {
			return 1;
		}
		return original;
	}
}
