package org.moddedmite.modslinker.register;

import net.minecraft.EnumChatFormatting;
import net.minecraft.ItemArmor;
import net.minecraft.ItemBoots;
import net.minecraft.ItemCuirass;
import net.minecraft.ItemHelmet;
import net.minecraft.ItemLeggings;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;

public class LinkerModifierTypes {
    public static final ArmorModifierTypes ANTI_CORROSION = new ArmorModifierTypes(
            1.0f, "anti_corrosion", EnumChatFormatting.GRAY, 5, 1,
            stack -> stack.getItem() instanceof ItemArmor);

    public static final ArmorModifierTypes ATMOSPHERIC_SHIELD = new ArmorModifierTypes(
            0.10f, "atmospheric_shield", EnumChatFormatting.AQUA, 5, 4,
            stack -> stack.getItem() instanceof ItemHelmet);

    public static final ArmorModifierTypes STONE_SKIN = new ArmorModifierTypes(
            2.0f, "stone_skin", EnumChatFormatting.DARK_GREEN, 5, 4,
            stack -> stack.getItem() instanceof ItemCuirass);

    public static final ArmorModifierTypes HOLY_SPIRIT = new ArmorModifierTypes(
            0.025f, "holy_spirit", EnumChatFormatting.BLUE, 5, 4,
            stack -> stack.getItem() instanceof ItemLeggings);

    public static final ArmorModifierTypes FLAME_SHIELD = new ArmorModifierTypes(
            0.025f, "flame_shield", EnumChatFormatting.GOLD, 5, 4,
            stack -> stack.getItem() instanceof ItemBoots);

    public static final ToolModifierTypes SLAUGHTER = new ToolModifierTypes(
            0.05f, "slaughter", EnumChatFormatting.RED, 5, ToolModifierTypes::isWeapon, 4);

    public static final ToolModifierTypes DISINTEGRATION = new ToolModifierTypes(
            1.0f, "disintegration", EnumChatFormatting.AQUA, 5, ToolModifierTypes::isWeapon, 4);
}
