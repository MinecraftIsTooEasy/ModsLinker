package org.moddedmite.modslinker.mixin.itfr;

import net.minecraft.EntityMob;
import net.minecraft.World;
import net.oilcake.mitelros.entity.mob.EntitySpirit;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntitySpirit.class)
public class EntitySpiritMixin extends EntityMob {
	
	public EntitySpiritMixin(World par1World) {
		super(par1World);
	}
	
	@Override
	public boolean isHarmedByFire() {
		return false;
	}
}
