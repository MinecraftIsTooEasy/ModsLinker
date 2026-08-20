package org.moddedmite.modslinker.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.SpawnerAnimals;
import org.moddedmite.modslinker.LinkerConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SpawnerAnimals.class)
public class SpawnerAnimalsMixin {
	@ModifyReturnValue(method = "calcEffectiveHostileMobSpawningRateModifier", at = @At("RETURN"))
	private float mobSpawnOffset(float original) {
		return (float) (original + LinkerConfigs.mobSpawningOffset.getDoubleValue());
	}
}
