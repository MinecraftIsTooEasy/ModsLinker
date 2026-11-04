package org.moddedmite.modslinker.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.Minecraft;
import net.minecraft.MovementInputFromOptions;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MovementInputFromOptions.class)
public class MovementInputFromOptionsMixin {
	@ModifyExpressionValue(method = "updatePlayerMoveState", at = @At(value = "CONSTANT", args = "doubleValue=0.3"))
	private double swiftSneakSpeed(double original) {
		EntityClientPlayerMP player = Minecraft.theMinecraft.thePlayer;
		if (player == null) {
			return original;
		}
		int level = EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentSwiftSneak, player.getLeggings());
		return level > 0 ? original + 0.15D * level : original;
	}
}
