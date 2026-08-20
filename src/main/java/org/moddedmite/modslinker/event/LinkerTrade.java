package org.moddedmite.modslinker.event;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import moddedmite.rustedironcore.api.event.events.TradingRegisterEvent;
import moddedmite.rustedironcore.api.util.IdUtilExtra;
import moddedmite.rustedironcore.villager.VillagerSettings;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.MerchantRecipe;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.oilcake.mitelros.registry.item.Items;

import java.util.function.Consumer;

public class LinkerTrade implements Consumer<TradingRegisterEvent> {

	@SuppressWarnings("unchecked")
	@Override
	public void accept(TradingRegisterEvent event) {
		event.registerProfession(IdUtilExtra.getNextVillagerProfessionID(), "villager.profession.mysterious", VillagerSettings.LibrarianTexture)
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(Items.shardAzurite, 32), new ItemStack(Item.emerald.itemID, 1)));
				})
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 8), new ItemStack(Item.appleGold.itemID, 2)));
				})
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(Item.diamond, 32), new ItemStack(Items.ancientMetalArmorPiece, 16), new ItemStack(EXItemsRegistryInit.itemEnhanceGemBox.itemID, 3)));
				})
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(Item.diamond, 32), new ItemStack(Item.ghastTear, 16), new ItemStack(BEXItems.voucherGhast.itemID, 4)));
				})
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(EXItemsRegistryInit.fancyRed, 16), new ItemStack(EXItemsRegistryInit.voucherFishing, 1), new ItemStack(EXItemsRegistryInit.voucherVillager.itemID, 1)));
				})
				.addEntry((recipeList, villager, rand) -> {
					if (rand.nextFloat() < villager.adjustProbability(0.5F))
						recipeList.add(new MerchantRecipe(new ItemStack(BEXItems.voucherZombieBoss, 2), new ItemStack(EXItemsRegistryInit.fancyRed, 16), new ItemStack(BEXItems.voucherSkeletonBoss, 1)));
				});
	}
}
