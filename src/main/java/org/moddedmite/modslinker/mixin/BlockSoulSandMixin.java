package org.moddedmite.modslinker.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.BlockSoulSand;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.World;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockSoulSand.class)
public class BlockSoulSandMixin {
	@ModifyExpressionValue(method = "onEntityCollidedWithBlock", at = @At(value = "CONSTANT", args = "doubleValue=0.4"))
	private double farTravelSoulSandDrag(double original, World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer player) {
			float efficiency = LinkerModifierTypes.getFarTravelEfficiency(player.getBoots());
			if (efficiency > 0.0F) {
				return 1.0D - (1.0D - original) * (1.0D - efficiency);
			}
		}
		return original;
	}
}
