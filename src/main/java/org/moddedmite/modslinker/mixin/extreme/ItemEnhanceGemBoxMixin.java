package org.moddedmite.modslinker.mixin.extreme;

import cn.wensc.mitemod.extreme.item.GemModifierTypes;
import cn.wensc.mitemod.extreme.item.ItemEnhanceGemBox;
import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandom;
import net.minecraft.WeightedRandomChestContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEnhanceGemBox.class)
public class ItemEnhanceGemBoxMixin {
	private static final WeightedRandomChestContent[] GEMS = {
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem), 1, 1, 243),
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem2), 1, 1, 81),
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem3), 1, 1, 27),
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem4), 1, 1, 9),
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem5), 1, 1, 3),
			new WeightedRandomChestContent(new ItemStack(EXItemsRegistryInit.itemEnhanceGem6), 1, 1, 1)
	};

	@WrapOperation(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/InventoryPlayer;addItemStackToInventoryOrDropIt(Lnet/minecraft/ItemStack;)V"))
	private void moreGem(InventoryPlayer instance, ItemStack item_stack, Operation<Void> original, @Local(name = "player") EntityPlayer player) {
		WeightedRandomChestContent chosen = (WeightedRandomChestContent) WeightedRandom.getRandomItem(player.worldObj.rand, GEMS);
		instance.addItemStackToInventoryOrDropIt(new ItemStack(chosen.theItemId.getItem(), 1, player.worldObj.rand.nextInt(GemModifierTypes.values().length)));
	}
}
