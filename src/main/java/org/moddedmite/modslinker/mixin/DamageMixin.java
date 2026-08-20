package org.moddedmite.modslinker.mixin;

import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class DamageMixin {
    @Inject(method = "getTotalProtection(Lnet/minecraft/DamageSource;)F", at = @At("RETURN"), cancellable = true)
    private void disintegrationIgnoreArmor(DamageSource source, CallbackInfoReturnable<Float> cir) {
        Entity attacker = source != null ? source.getResponsibleEntity() : null;
        if (attacker instanceof EntityPlayer player) {
            ItemStack held = player.getHeldItemStack();
            if (held != null) {
                int level = LinkerModifierTypes.DISINTEGRATION.getModifierLevel(held.getTagCompound());
                if (level > 0) {
                    cir.setReturnValue(Math.max(cir.getReturnValue() - level, 0.0F));
                }
            }
        }
    }
}
