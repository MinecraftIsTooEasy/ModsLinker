package org.moddedmite.modslinker.register;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import org.moddedmite.modslinker.LinkerConfigs;
import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.oilcake.mitelros.registry.item.Items;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

import java.util.function.Consumer;

public class LinkerRecipeRegister implements Consumer<CraftingRecipeRegisterEvent> {
	@Override
	public void accept(CraftingRecipeRegisterEvent event) {
		event.getShaped().removeIf(recipe -> {
			ItemStack result = recipe.result;
			Object[] inputs = recipe.inputs;
			if (result.getItem() == EXItemsRegistryInit.clubAdamantium) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubAncientMetal) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubCopper) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubGold) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubMithril) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubSilver) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubVibranium) {
				return true;
			}
			if (result.getItem() == EXItemsRegistryInit.clubIron) {
				return true;
			}
			if (result.getItem() == BEXItems.clubEnchant) {
				return true;
			}

			if (result.getItem() == Items.tungstenBoots) {
				return true;
			}
			if (result.getItem() == Items.tungstenHelmet) {
				return true;
			}
			if (result.getItem() == Items.tungstenChestplate) {
				return true;
			}
			if (result.getItem() == Items.tungstenLeggings) {
				return true;
			}

			if (result.getItem() == MITEITEItemRegistryInit.VIBRANIUM_BOOTS) {
				return true;
			}
			if (result.getItem() == MITEITEItemRegistryInit.VIBRANIUM_HELMET) {
				return true;
			}
			if (result.getItem() == MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE) {
				return true;
			}
			if (result.getItem() == MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS) {
				return true;
			}
			if (result.getItem() == MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE) {
				return true;
			}
			if (result.getItem() == Item.bootsAdamantium) {
				return true;
			}
			if (result.getItem() == Item.helmetAdamantium) {
				return true;
			}
			if (result.getItem() == Item.plateAdamantium) {
				return true;
			}
			if (result.getItem() == Item.legsAdamantium) {
				return true;
			}

			return false;
		});
		
		event.getVanilla().removeIf(recipe -> {
			ItemStack result = recipe.getRecipeOutput();
			if (result == null) return false;
			if (result.getItem() == Item.bootsAdamantium) {
				return true;
			}
			if (result.getItem() == Item.helmetAdamantium) {
				return true;
			}
			if (result.getItem() == Item.plateAdamantium) {
				return true;
			}
			if (result.getItem() == Item.legsAdamantium) {
				return true;
			}
			return false;
		});

		if (LinkerConfigs.CustomNuggetRecipes.getBooleanValue()) {
			event.getVanilla().removeIf(recipe -> {
				ItemStack result = recipe.getRecipeOutput();
				ItemStack[] inputs = recipe.getComponents();
				if (result == null) return false;
				if (result.getItem() == Item.ingotIron && hasIngredients(inputs, Item.ironNugget)) return true;
				if (result.getItem() == Item.ingotGold && hasIngredients(inputs, Item.goldNugget)) return true;
				if (result.getItem() == Item.ingotCopper && hasIngredients(inputs, Item.copperNugget)) return true;
				if (result.getItem() == Item.ingotSilver && hasIngredients(inputs, Item.silverNugget)) return true;
				if (result.getItem() == Item.ingotAncientMetal && hasIngredients(inputs, Item.ancientMetalNugget)) return true;
				if (result.getItem() == Item.ingotMithril && hasIngredients(inputs, Item.mithrilNugget)) return true;
				if (result.getItem() == Item.ingotAdamantium && hasIngredients(inputs, Item.adamantiumNugget)) return true;
				if (result.getItem() == MITEITEItemRegistryInit.VIBRANIUM_INGOT && hasIngredients(inputs, MITEITEItemRegistryInit.VIBRANIUM_NUGGET)) return true;
				return false;
			});
			
			event.getShapeless().removeIf(recipe -> {
				ItemStack result = recipe.result;
				Object[] inputs = recipe.inputs;
				if (result == null) return false;
				if (result.getItem() == Items.nickelIngot && hasIngredients(inputs, Items.nickelNugget)) {
					return true;
				}
				if (result.getItem() == Items.tungstenIngot && hasIngredients(inputs, Items.tungstenNugget)) {
					return true;
				}
				if (result.getItem() == Items.forgingNote) {
					return true;
				}
				return false;
			});
		}

		event.registerShapedRecipe(
				new ItemStack(Item.bootsAncientMetal), true,
				"ANA",
				"A A",
				'A', Item.ingotAncientMetal,
				'N', Items.nickelBoots).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.helmetAncientMetal), true,
				"AAA",
				"ANA",
				'A', Item.ingotAncientMetal,
				'N', Items.nickelHelmet).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.plateAncientMetal), true,
				"ANA",
				"AAA",
				"AAA",
				'A', Item.ingotAncientMetal,
				'N', Items.nickelChestplate).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.legsAncientMetal), true,
				"AAA",
				"ANA",
				"A A",
				'A', Item.ingotAncientMetal,
				'N', Items.nickelLeggings).extendsNBT();

		event.registerShapedRecipe(
				new ItemStack(Items.tungstenBoots), true,
				"NBA",
				"A A",
				'A', Items.tungstenIngot,
				'N', Item.bootsMithril,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Items.tungstenHelmet), true,
				"ANA",
				"ABA",
				'A', Items.tungstenIngot,
				'N', Item.helmetMithril,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Items.tungstenChestplate), true,
				"ABA",
				"ANA",
				"AAA",
				'A', Items.tungstenIngot,
				'N', Item.plateMithril,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Items.tungstenLeggings), true,
				"ANA",
				"ABA",
				"A A",
				'A', Items.tungstenIngot,
				'N', Item.legsMithril,
				'B', Items.forgingNote).extendsNBT();

		event.registerShapedRecipe(
				new ItemStack(Item.bootsAdamantium), true,
				"N A",
				"ABA",
				'A', Item.ingotAdamantium,
				'N', Items.tungstenBoots,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.helmetAdamantium), true,
				"ANA",
				"ABA",
				'A', Item.ingotAdamantium,
				'N', Items.tungstenHelmet,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.plateAdamantium), true,
				"ABA",
				"ANA",
				"AAA",
				'A', Item.ingotAdamantium,
				'N', Items.tungstenChestplate,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(Item.legsAdamantium), true,
				"ANA",
				"ABA",
				"A A",
				'A', Item.ingotAdamantium,
				'N', Items.tungstenLeggings,
				'B', Items.forgingNote).extendsNBT();

		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_BOOTS), true,
				"N U",
				"ABA",
				'A', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'N', Item.bootsAdamantium,
				'U', Items.uruIngot,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_HELMET), true,
				"UNA",
				"ABA",
				'A', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'N', Item.helmetAdamantium,
				'U', Items.uruIngot,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE), true,
				"ABU",
				"ANA",
				"AAA",
				'A', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'N', Item.plateAdamantium,
				'U', Items.uruIngot,
				'B', Items.forgingNote).extendsNBT();
		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS), true,
				"ANU",
				"ABA",
				"A A",
				'A', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'N', Item.legsAdamantium,
				'U', Items.uruIngot,
				'B', Items.forgingNote).extendsNBT();

		event.registerShapelessRecipe(
				new ItemStack(Items.forgingNote, 2), true,
				Items.forgingNote,
				MITEITEItemRegistryInit.UNIVERSAL_ENHANCE_STONE);
		
		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.IRON_ENHANCE_STONE), true,
				" C ",
				"SIS",
				" C ",
				'I', Items.nickelIngot,
				'C', Item.ingotCopper,
				'S', Item.ingotSilver);
		event.registerShapedRecipe
				(new ItemStack(MITEITEItemRegistryInit.MITHRIL_ENHANCE_STONE), true,
				" I ",
				"GMG",
				" I ",
				'I', Items.nickelIngot,
				'G', Item.ingotGold,
				'M', Item.ingotMithril);
		event.registerShapedRecipe(
				new ItemStack(MITEITEItemRegistryInit.ADAMANTIUM_ENHANCE_STONE), true,
				" D ",
				"MAM",
				" R ",
				'R', EXItemsRegistryInit.fancyRed,
				'D', Items.tungstenIngot,
				'M', Item.ingotMithril,
				'A', Item.ingotAdamantium);

		if (LinkerConfigs.CustomNuggetRecipes.getBooleanValue()) {
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotIron), true,
					new ItemStack(Item.dyePowder, 1, 4),
					Item.ironNugget, Item.ironNugget, Item.ironNugget, Item.ironNugget,
					Item.ironNugget, Item.ironNugget, Item.ironNugget, Item.ironNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotGold), true,
					new ItemStack(Item.dyePowder, 1, 4),
					Item.goldNugget, Item.goldNugget, Item.goldNugget, Item.goldNugget,
					Item.goldNugget, Item.goldNugget, Item.goldNugget, Item.goldNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotCopper), true,
					new ItemStack(Item.dyePowder, 1, 4),
					Item.copperNugget, Item.copperNugget, Item.copperNugget, Item.copperNugget,
					Item.copperNugget, Item.copperNugget, Item.copperNugget, Item.copperNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotSilver), true,
					new ItemStack(Item.dyePowder, 1, 4),
					Item.silverNugget, Item.silverNugget, Item.silverNugget, Item.silverNugget,
					Item.silverNugget, Item.silverNugget, Item.silverNugget, Item.silverNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotAncientMetal), true,
					Item.emerald,
					Item.ancientMetalNugget, Item.ancientMetalNugget, Item.ancientMetalNugget, Item.ancientMetalNugget,
					Item.ancientMetalNugget, Item.ancientMetalNugget, Item.ancientMetalNugget, Item.ancientMetalNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotMithril), true,
					Item.diamond,
					Item.mithrilNugget, Item.mithrilNugget, Item.mithrilNugget, Item.mithrilNugget,
					Item.mithrilNugget, Item.mithrilNugget, Item.mithrilNugget, Item.mithrilNugget);
			event.registerShapelessRecipe(
					new ItemStack(Item.ingotAdamantium), true,
					EXItemsRegistryInit.fancyRed,
					Item.adamantiumNugget, Item.adamantiumNugget, Item.adamantiumNugget, Item.adamantiumNugget,
					Item.adamantiumNugget, Item.adamantiumNugget, Item.adamantiumNugget, Item.adamantiumNugget);
//			event.registerShapelessRecipe(
//					new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_INGOT), true,
//					EXItemsRegistryInit.fancyRed,
//					MITEITEItemRegistryInit.VIBRANIUM_NUGGET, MITEITEItemRegistryInit.VIBRANIUM_NUGGET,
//					MITEITEItemRegistryInit.VIBRANIUM_NUGGET, MITEITEItemRegistryInit.VIBRANIUM_NUGGET,
//					MITEITEItemRegistryInit.VIBRANIUM_NUGGET, MITEITEItemRegistryInit.VIBRANIUM_NUGGET,
//					MITEITEItemRegistryInit.VIBRANIUM_NUGGET, MITEITEItemRegistryInit.VIBRANIUM_NUGGET);
			
			event.registerShapelessRecipe(
					new ItemStack(Items.nickelIngot), true,
					new ItemStack(Item.dyePowder, 1, 4),
					Items.nickelNugget, Items.nickelNugget, Items.nickelNugget, Items.nickelNugget,
					Items.nickelNugget, Items.nickelNugget, Items.nickelNugget, Items.nickelNugget);
			event.registerShapelessRecipe(
					new ItemStack(Items.tungstenIngot), true,
					Item.diamond,
					Items.tungstenNugget, Items.tungstenNugget, Items.tungstenNugget, Items.tungstenNugget,
					Items.tungstenNugget, Items.tungstenNugget, Items.tungstenNugget, Items.tungstenNugget);
		}

		//club
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubCopper, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.copperNugget,
				'*', Item.ingotCopper);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubSilver, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.silverNugget,
				'*', Item.ingotSilver);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubGold, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.goldNugget,
				'*', Item.ingotGold);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubIron, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.ironNugget,
				'*', Item.ingotIron);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubAncientMetal, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.ancientMetalNugget,
				'*', Item.ingotAncientMetal);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubMithril, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.mithrilNugget,
				'*', Item.ingotMithril);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubAdamantium, 1), true,
				"#*#",
				"#*#",
				" # ",
				'#', Item.adamantiumNugget,
				'*', Item.ingotAdamantium);
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubVibranium, 1), true,
				"#*#",
				"#*#",
				" A ",
				'#', MITEITEItemRegistryInit.VIBRANIUM_NUGGET,
				'*', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'A', EXItemsRegistryInit.voucherClubCore);

		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubMithril, 1), true,
				"#*#",
				"#*#",
				" A ",
				'#', Item.mithrilNugget,
				'*', Item.ingotMithril,
				'A', EXItemsRegistryInit.clubSilver).extendsNBT().keepQuality();
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubAdamantium, 1), true,
				"#*#",
				"#*#",
				" A ",
				'#', Item.adamantiumNugget,
				'*', Item.ingotAdamantium,
				'A', EXItemsRegistryInit.clubIron).extendsNBT().keepQuality();
		event.registerShapedRecipe(
				new ItemStack(EXItemsRegistryInit.clubVibranium, 1), true,
				"#B#",
				"#*#",
				" A ",
				'#', MITEITEItemRegistryInit.VIBRANIUM_NUGGET,
				'*', MITEITEItemRegistryInit.VIBRANIUM_INGOT,
				'A', EXItemsRegistryInit.voucherClubCore,
				'B', EXItemsRegistryInit.clubAncientMetal).extendsNBT().keepQuality();
		event.registerShapedRecipe(
				new ItemStack(BEXItems.clubEnchant, 1), true,
				"#B#",
				"#*#",
				" A ",
				'#', BEXItems.enchantNugget ,
				'*', BEXItems.enchantIngot,
				'A', EXItemsRegistryInit.voucherClubCore,
				'B', EXItemsRegistryInit.clubMithril).extendsNBT().keepQuality();
	}

	private static boolean hasIngredients(Object[] inputs, Item ing) {
		for (Object input : inputs) {
			if (input instanceof Item && input == ing) return true;
			if (input instanceof ItemStack && ((ItemStack) input).getItem() == ing) return true;
		}
		return false;
	}
}
