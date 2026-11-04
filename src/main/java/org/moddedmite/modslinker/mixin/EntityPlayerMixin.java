package org.moddedmite.modslinker.mixin;

import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {
    @Unique private int inRainCounterL;
    
    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }
    
    @Inject(method = "readEntityFromNBT",at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        this.inRainCounterL = par1NBTTagCompound.getInteger("inRainCounterL");
    }
    
    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound,CallbackInfo callback) {
        par1NBTTagCompound.setInteger("inRainCounterL", this.inRainCounterL);
    }

    @Inject(method = "dealDamageToInventory(Lnet/minecraft/DamageSource;FFZ)Z", at = @At("HEAD"), cancellable = true)
    private void antiCorrosion(DamageSource damage_source, float chance_per_item, float amount, boolean include_worn_items, CallbackInfoReturnable<Boolean> cir) {
        if ((damage_source == DamageSource.pepsin || damage_source == DamageSource.acid) && hasAntiCorrosion(this.getHelmet())
                && hasAntiCorrosion(this.getCuirass())
                && hasAntiCorrosion(this.getLeggings())
                && hasAntiCorrosion(this.getBoots())) {
            cir.setReturnValue(false);
        }
    }

    private static boolean hasAntiCorrosion(ItemStack stack) {
        return stack != null && LinkerModifierTypes.ANTI_CORROSION.getModifierLevel(stack.getTagCompound()) > 0;
    }
    
    @Inject(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;onLivingUpdate()V", shift = At.Shift.AFTER))
    private void rainHunger(CallbackInfo ci) {
        if (this.isInRain()) {
            if (this.inRainCounterL < (Configs.GameMechanics.IN_RAIN_DEBUFF_TIME.get())) {
                ++this.inRainCounterL;
            } else {
                this.addPotionEffect(new PotionEffect(Potion.hunger.id, 3600, 0));
            }
        } else if (this.inRainCounterL > 0) {
            --this.inRainCounterL;
        }
    }
}
