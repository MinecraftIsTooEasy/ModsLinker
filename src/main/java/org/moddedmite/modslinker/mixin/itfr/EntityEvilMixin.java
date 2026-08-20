package org.moddedmite.modslinker.mixin.itfr;

import net.minecraft.World;
import net.oilcake.mitelros.entity.mob.EntityEvil;
import net.oilcake.mitelros.entity.mob.EntityGhost;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityEvil.class)
public class EntityEvilMixin extends EntityGhost {
	public EntityEvilMixin(World par1World) {
		super(par1World);
	}

	@Override
	public boolean isHarmedByFire() {
		return false;
	}
}
