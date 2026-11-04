package org.moddedmite.modslinker.render;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderZombieDoor extends RenderBiped {
	public ItemRenderer itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
	
	public RenderZombieDoor() {
		super(new ModelZombie(), 0.5F, 1.0F);
	}
	
	@Override
	protected void func_130005_c(EntityLiving par1EntityLiving, float par2) {
		float var3 = 1.0F;
		GL11.glColor3f(var3, var3, var3);
//        super.c(par1EntityLiving, par2);
		ItemStack var4 = par1EntityLiving.getHeldItemStack();
		ItemStack var5 = par1EntityLiving.func_130225_q(3);
		if (var5 != null) {
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			
			if (var5.getItem().itemID < 256) {
				if (RenderBlocks.renderItemIn3d(Block.blocksList[var5.itemID].getRenderType())) {
					float var6 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var6, -var6, -var6);
				}
				
				this.itemRenderer.renderItem(par1EntityLiving, var5, 0);
			} else if (var5.getItem().itemID == Item.skull.itemID) {
				float var6 = 1.0625F;
				GL11.glScalef(var6, -var6, -var6);
				String var7 = "";
				
				if (var5.hasTagCompound() && var5.getTagCompound().hasKey("SkullOwner")) {
					var7 = var5.getTagCompound().getString("SkullOwner");
				}
				
				TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var5.getItemSubtype(), var7);
			}
			
			GL11.glPopMatrix();
		}
		
		if (var4 != null) {
			GL11.glPushMatrix();
			
			if (this.mainModel.isChild) {
				float var6 = 0.5F;
				GL11.glTranslatef(0.0F, 0.625F, 0.0F);
				GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
				GL11.glScalef(var6, var6, var6);
			}
			
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			
			if (var4.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
				float var6 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				var6 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-var6, -var6, var6);
			} else if (var4.getItem() instanceof ItemBow) {
				float var6 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else if (Item.itemsList[var4.itemID].isFull3D()) {
				float var6 = 0.625F;
				if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}
				
				this.func_82422_c();
				GL11.glScalef(var6, -var6, var6);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			} else {
				float var6 = 1.0F;
				GL11.glTranslatef(0.75F, 0.6F, 0.7F);
				GL11.glScalef(var6, var6, var6);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}
			
			this.itemRenderer.renderItem(par1EntityLiving, var4, 0);
			if (var4.getItem().requiresMultipleRenderPasses()) {
				this.itemRenderer.renderItem(par1EntityLiving, var4, 1);
			}
			
			GL11.glPopMatrix();
		}
	}
	
	@Override
	protected void setTextures() {
		this.setTexture(0, "textures/entity/zombie/zombie");
		this.setTexture(1, "textures/entity/zombie/zombie_villager");
		this.setTexture(2, "textures/entity/zombie_pigman");
		this.setTexture(3, "textures/entity/zombie/revenant");
	}
	
	protected ResourceLocation getZombieTexture(EntityZombie par1EntityZombie) {
		return this.textures[0];
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getZombieTexture((EntityZombie) par1Entity);
	}
}
