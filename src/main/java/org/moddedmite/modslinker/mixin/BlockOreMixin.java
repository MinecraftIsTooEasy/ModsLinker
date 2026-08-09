package org.moddedmite.modslinker.mixin;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import org.moddedmite.modslinker.LinkerConfigs;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockOre;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockOre.class)
public class BlockOreMixin {
	
	@Redirect(method = "dropBlockAsEntityItem",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/Block;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"))
	private int onEmeraldDrop(Block block, BlockBreakInfo info, int id_dropped, int metadata_dropped, int quantity_dropped, float chance) {
		if (id_dropped == Item.shardEmerald.itemID && info.world.rand.nextFloat() * 100.0F < LinkerConfigs.VillagerVoucherFrequency.getIntegerValue()) {
			block.dropBlockAsEntityItem(info, EXItemsRegistryInit.voucherVillager.itemID, 0, 1, 1.0F);
		}
		return block.dropBlockAsEntityItem(info, id_dropped, metadata_dropped, quantity_dropped, chance);
	}
}
