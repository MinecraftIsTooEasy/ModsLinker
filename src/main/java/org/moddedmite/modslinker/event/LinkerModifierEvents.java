package org.moddedmite.modslinker.event;

import cn.wensc.mitemod.extreme.entity.EntityZombieBoss;
import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.AttributeInstance;
import net.minecraft.AttributeModifier;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.oilcake.mitelros.mixin.interfaces.ITFEntityPlayer;
import net.oilcake.mitelros.mixin.interfaces.ITFFoodStats;
import org.moddedmite.modslinker.register.LinkerModifierTypes;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class LinkerModifierEvents implements ICombatListener, ITickListener {
    private static final int STONE_SKIN_COOLDOWN = 2400;
    private static final UUID FLAME_SHIELD_SPEED_UUID = UUID.fromString("9e5a2c8d-3f4b-4e7a-9c1f-5d8b2a6e0f3a");
    private static final Map<EntityPlayer, Long> STONE_SKIN_COOLDOWNS = new WeakHashMap<>();

    @Override
    public void onPlayerReceiveDamageModify(EntityPlayer player, Damage damage) {
        ItemStack helmet = player.getHelmet();
        if (helmet != null) {
            int level = LinkerModifierTypes.ATMOSPHERIC_SHIELD.getModifierLevel(helmet.getTagCompound());
            if (level > 0 && damage.getSource() == DamageSource.divine_lightning) {
                damage.scaleAmount(1.0f - 0.10f * level);
            }
        }

        if (player.getHealth() < player.getHealthLimit() / 2) {
            ItemStack cuirass = player.getCuirass();
            if (cuirass != null) {
                int level = LinkerModifierTypes.STONE_SKIN.getModifierLevel(cuirass.getTagCompound());
                if (level > 0 && isStoneSkinReady(player)) {
                    player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 200, level - 1));
                    setStoneSkinCooldown(player);
                }
            }
        }
    }

    @Override
    public float onPlayerRawMeleeDamageModify(EntityPlayer player, Entity target, boolean critical, boolean suspended_in_liquid, float original) {
        ItemStack leggings = player.getLeggings();
        if (leggings != null) {
            int level = LinkerModifierTypes.HOLY_SPIRIT.getModifierLevel(leggings.getTagCompound());
            if (level > 0) {
                int water = ITFEntityPlayer.cast(player).itf$GetWater();
                if (water > ((ITFFoodStats) player.getFoodStats()).itf$GetWaterLimit() * 0.8f) {
                    original *= 1.0f + 0.025f * level;
                }
            }
        }

        ItemStack weapon = player.getHeldItemStack();
        if (weapon != null && target != null) {
            int level = LinkerModifierTypes.SLAUGHTER.getModifierLevel(weapon.getTagCompound());
            if (level > 0 && target instanceof EntityZombieBoss) {
                original *= 1.0f + 0.05f * level;
            }
        }
        return original;
    }

    @Override
    public void onEntityPlayerTick(EntityPlayer player) {
        ItemStack boots = player.getBoots();
        int level = boots != null ? LinkerModifierTypes.FLAME_SHIELD.getModifierLevel(boots.getTagCompound()) : 0;
        if (level > 0) {
            applyFlameShieldSpeed(player, level);
        } else {
            removeFlameShieldSpeed(player);
        }
    }

    private void applyFlameShieldSpeed(EntityPlayer player, int level) {
        AttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        AttributeModifier modifier = attribute.getModifier(FLAME_SHIELD_SPEED_UUID);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
        attribute.applyModifier(new AttributeModifier(FLAME_SHIELD_SPEED_UUID, "flame_shield_speed", 0.025 * level, 1));
    }

    private void removeFlameShieldSpeed(EntityPlayer player) {
        AttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        AttributeModifier modifier = attribute.getModifier(FLAME_SHIELD_SPEED_UUID);
        if (modifier != null) {
            attribute.removeModifier(modifier);
        }
    }

    private boolean isStoneSkinReady(EntityPlayer player) {
        return !STONE_SKIN_COOLDOWNS.containsKey(player)
                || STONE_SKIN_COOLDOWNS.get(player) + STONE_SKIN_COOLDOWN < player.worldObj.getTotalWorldTime();
    }

    private void setStoneSkinCooldown(EntityPlayer player) {
        STONE_SKIN_COOLDOWNS.put(player, player.worldObj.getTotalWorldTime());
    }
}
