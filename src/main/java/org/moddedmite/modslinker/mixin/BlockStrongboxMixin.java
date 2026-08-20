package org.moddedmite.modslinker.mixin;

import net.minecraft.BlockStrongbox;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemStack;
import net.minecraft.TileEntityStrongbox;
import org.moddedmite.modslinker.enchantment.LinkerEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockStrongbox.class)
public class BlockStrongboxMixin {
	@Redirect(method = "onBlockActivated", at = @At(value = "INVOKE", target = "Lnet/minecraft/TileEntityStrongbox;isOwner(Lnet/minecraft/EntityPlayer;)Z"))
	private boolean allowShatterOpen(TileEntityStrongbox strongbox, EntityPlayer player) {
		if (strongbox.isOwner(player)) return true;
		ItemStack held = player.getHeldItemStack();
		return held != null && held.getItem() instanceof ItemPickaxe && EnchantmentHelper.getEnchantmentLevel(LinkerEnchantments.enchantmentShatter.effectId, held) > 0;
	}
}
