package org.moddedmite.modslinker.mixin.bex;

import net.minecraft.EntityArachnid;
import net.minecraft.World;
import net.moddedmite.mitemod.bex.entity.EntitySpiderQueen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntitySpiderQueen.class)
public abstract class EntitySpiderQueenMixin extends EntityArachnid {
	public EntitySpiderQueenMixin(World par1World, float scaling) {
		super(par1World, scaling);
	}

	@Override
	public boolean isHarmedByFire() {
		return false;
	}
}
