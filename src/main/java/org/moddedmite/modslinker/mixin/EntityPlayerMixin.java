package org.moddedmite.modslinker.mixin;

import net.minecraft.DamageSource;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.World;
import org.moddedmite.modslinker.register.LinkerModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {
    public EntityPlayerMixin(World par1World) {
        super(par1World);
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
}
