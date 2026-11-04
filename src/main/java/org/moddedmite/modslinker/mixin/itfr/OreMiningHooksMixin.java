package org.moddedmite.modslinker.mixin.itfr;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockOre;
import net.minecraft.EnchantmentHelper;
import net.oilcake.mitelros.feat.OreDropHelper;
import net.oilcake.mitelros.feat.OreMiningHooks;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreMiningHooks.class)
public class OreMiningHooksMixin {
	@Inject(method = "setIdMeta", at = @At("HEAD"), cancellable = true)
	private static void restoreOriginalOreDrop(BlockBreakInfo info, BlockOre ore, LocalIntRef idDropped, LocalIntRef quantityDropped, CallbackInfo ci) {
		if (!info.wasExploded() && EnchantmentHelper.hasEnchantment(info.responsible_item_stack, LinkerEnchantments.enchantmentRestore)) {
			ci.cancel();
		}
	}

	@ModifyExpressionValue(method = "modifyFinalDropId", at = @At(value = "INVOKE", target = "Lnet/oilcake/mitelros/feat/OreDropHelper;getMeltPieceItemID(Lnet/minecraft/BlockOre;)I"))
	private static int restoreMeltsToIngot(int original, @Local(argsOnly = true) BlockOre ore, @Local(argsOnly = true) BlockBreakInfo info) {
		if (EnchantmentHelper.hasEnchantment(info.responsible_item_stack, LinkerEnchantments.enchantmentRestore)) {
			return OreDropHelper.getMeltIngotItemID(ore);
		}
		return original;
	}
}
