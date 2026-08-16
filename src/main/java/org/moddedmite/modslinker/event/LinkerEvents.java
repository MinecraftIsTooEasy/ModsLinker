package org.moddedmite.modslinker.event;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.entity.EntityMirrorSkeleton;
import cn.wensc.mitemod.extreme.entity.EntityZombieDoor;
import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import net.minecraft.EntityGhast;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.config.ITFConfig;
import net.xiaoyu233.mitemod.miteite.entity.EntityZombieLord;
import org.moddedmite.modslinker.LinkerConfigs;
import org.moddedmite.modslinker.register.LinkerRecipeRegister;
import com.google.common.eventbus.Subscribe;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.listener.IEntityEventListener;
import moddedmite.rustedironcore.api.event.listener.IInitializationListener;
import moddedmite.rustedironcore.api.util.BiomeSpawnUtil;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenEnd;
import net.minecraft.BiomeGenHell;
import net.minecraft.BiomeGenUnderworld;
import net.minecraft.DamageSource;
import net.minecraft.EntityAncientBoneLord;
import net.minecraft.EntityEnderman;
import net.minecraft.EntityInfernalCreeper;
import net.minecraft.EntityLivingBase;
import net.minecraft.EnumCreatureType;
import net.minecraft.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.moddedmite.mitemod.bex.entity.EntityBedrockElemental;
import net.moddedmite.mitemod.bex.entity.EntitySpiderQueen;
import net.moddedmite.mitemod.bex.entity.EntityZombieDoorDeep;
import net.moddedmite.mitemod.bex.entity.EntityZombieDoorLord;
import net.moddedmite.mitemod.bex.entity.EntityZombieMiner;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.oilcake.mitelros.entity.mob.EntityPigmanLord;
import net.oilcake.mitelros.entity.mob.EntityRetinueZombie;
import net.oilcake.mitelros.entity.mob.EntitySpiderKing;
import net.oilcake.mitelros.entity.mob.EntitySpirit;
import net.oilcake.mitelros.entity.mob.EntityWitherBoneLord;
import net.oilcake.mitelros.registry.block.Blocks;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import net.xiaoyu233.fml.reload.event.SoundsRegisterEvent;
import net.xiaoyu233.mitemod.miteite.entity.EntityAnnihilationSkeleton;
import net.xiaoyu233.mitemod.miteite.entity.EntityWanderingWitch;
import net.xiaoyu233.mitemod.miteite.entity.EntityZombiePigmanLord;

import java.util.ArrayList;
import java.util.List;

public class LinkerEvents extends Handlers {

    public static void register() {
        Handlers.Crafting.registerPost(new LinkerRecipeRegister());

        Handlers.Smelting.register(event -> {
	        event.register(new ItemStack(Blocks.blockNickel).getItem(), new ItemStack(EXItemsRegistryInit.itemGemShard, 1, 0), 3);
	        event.register(new ItemStack(Blocks.blockTungsten).getItem(), new ItemStack(EXItemsRegistryInit.itemGemShard, 1, 2), 4);
        });
        
        Handlers.EntityEvent.register(new IEntityEventListener() {
            @Override
            public void onLoot(EntityLivingBase entity, DamageSource damageSource) {
                if (!damageSource.wasCausedByPlayer()) return;

                if (entity instanceof EntitySpiderKing) {
                    entity.dropItem(BEXItems.voucherSpiderQueen);
                }
//                if (entity instanceof EntityRetinueZombie) {
//                    entity.dropItem(BEXItems.voucherZombieMiner);
//                }
                if (entity instanceof EntityWitherBoneLord) {
                    entity.dropItem(EXItemsRegistryInit.voucherAnnihilationSkeleton);
                }
                if (entity instanceof EntityPigmanLord) {
                    entity.dropItem(EXItemsRegistryInit.voucherPigman);
                }
//                if (entity instanceof EntitySpirit) {
//                    entity.dropItem(EXItemsRegistryInit.voucherWitch);
//                }
            }
        });
        
        Handlers.Initialization.register(new IInitializationListener() {
            @Override
            public void onServerStarted(MinecraftServer server) {
                List<BiomeGenBase> overworldBiomes = new ArrayList<>();
                List<BiomeGenBase> underworldBiomes = new ArrayList<>();
                List<BiomeGenBase> allBiome = new ArrayList<>();
                List<BiomeGenBase> hellBiomes = new ArrayList<>();
                for (BiomeGenBase biome : BiomeGenBase.biomeList) {
                    if (biome == null || biome instanceof BiomeGenEnd) continue;
                    allBiome.add(biome);
                    if (biome instanceof BiomeGenHell) {
                        hellBiomes.add(biome);
                        continue;
                    }
                    if (biome instanceof BiomeGenUnderworld) {
                        underworldBiomes.add(biome);
                    } else {
                        overworldBiomes.add(biome);
                    }
                }
                for (BiomeGenBase biome : allBiome) {
                    BiomeSpawnUtil.removeSpawn(EntityEnderman.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityAncientBoneLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityInfernalCreeper.class, EnumCreatureType.monster, biome);

                    BiomeSpawnUtil.removeSpawn(EntityZombieLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityZombiePigmanLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityWanderingWitch.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityAnnihilationSkeleton.class, EnumCreatureType.monster, biome);

                    BiomeSpawnUtil.removeSpawn(EntityExchanger.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityZombieDoor.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityMirrorSkeleton.class, EnumCreatureType.monster, biome);

                    BiomeSpawnUtil.removeSpawn(EntityZombieDoorDeep.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntitySpiderQueen.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityZombieMiner.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityZombieDoorLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityBedrockElemental.class, EnumCreatureType.monster, biome);

                    BiomeSpawnUtil.removeSpawn(EntitySpiderKing.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityRetinueZombie.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityWitherBoneLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityPigmanLord.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntitySpirit.class, EnumCreatureType.monster, biome);
                }
                for (BiomeGenBase biome : hellBiomes) {
                    BiomeSpawnUtil.removeSpawn(EntityGhast.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityBedrockElemental.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityExchanger.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntityMirrorSkeleton.class, EnumCreatureType.monster, biome);
                    BiomeSpawnUtil.removeSpawn(EntitySpiderQueen.class, EnumCreatureType.monster, biome);
                }

                if (ITFConfig.TagDimensionInvade.getBooleanValue()) {
                    for (BiomeGenBase biome : allBiome) {
                        BiomeSpawnUtil.addSpawn(EntityEnderman.class, LinkerConfigs.EndermanSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityAncientBoneLord.class, LinkerConfigs.AncientBoneLordSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityInfernalCreeper.class, LinkerConfigs.InfernalCreeperSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityGhast.class, LinkerConfigs.GhastSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);

                        BiomeSpawnUtil.addSpawn(EntityZombieLord.class, LinkerConfigs.ZombieLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombiePigmanLord.class, LinkerConfigs.ZombiePigmanLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityWanderingWitch.class, LinkerConfigs.WanderingWitchSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityAnnihilationSkeleton.class, LinkerConfigs.AnnihilationSkeletonSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);

                        BiomeSpawnUtil.addSpawn(EntityExchanger.class, LinkerConfigs.ExchangerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieDoor.class, LinkerConfigs.ZombieDoorSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityMirrorSkeleton.class, LinkerConfigs.MirrorSkeletonSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);

                        BiomeSpawnUtil.addSpawn(EntityZombieDoorDeep.class, LinkerConfigs.ZombieDoorDeepSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpiderQueen.class, LinkerConfigs.SpiderQueenSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieMiner.class, LinkerConfigs.ZombieMinerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieDoorLord.class, LinkerConfigs.ZombieDoorLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityBedrockElemental.class, LinkerConfigs.BedrockElementalSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);

                        BiomeSpawnUtil.addSpawn(EntitySpiderKing.class, LinkerConfigs.SpiderKingSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityRetinueZombie.class, LinkerConfigs.RetinueZombieSpawn.getIntegerValue(), 4, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityWitherBoneLord.class, LinkerConfigs.WitherBoneLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityPigmanLord.class, LinkerConfigs.PigmanLordSpawn.getIntegerValue(), 1, 2, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpirit.class, LinkerConfigs.SpiritSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        
                        BiomeSpawnUtil.addSpawn(EntityZombiePigmanLord.class, LinkerConfigs.ZombiePigmanLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityBedrockElemental.class, LinkerConfigs.BedrockElementalSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityExchanger.class, LinkerConfigs.ExchangerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityWitherBoneLord.class, LinkerConfigs.WitherBoneLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityPigmanLord.class, LinkerConfigs.PigmanLordSpawn.getIntegerValue(), 1, 2, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpirit.class, LinkerConfigs.SpiritSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                    }
                } else {
                    for (BiomeGenBase biome : overworldBiomes) {
                        BiomeSpawnUtil.addSpawn(EntityEnderman.class, LinkerConfigs.EndermanSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        
                        BiomeSpawnUtil.addSpawn(EntityZombieDoor.class, LinkerConfigs.ZombieDoorSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieLord.class, LinkerConfigs.ZombieLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityWanderingWitch.class, LinkerConfigs.WanderingWitchSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityRetinueZombie.class, LinkerConfigs.RetinueZombieSpawn.getIntegerValue(), 4, 4, EnumCreatureType.monster, biome);
                    }

                    for (BiomeGenBase biome : allBiome) {
                        BiomeSpawnUtil.addSpawn(EntityAnnihilationSkeleton.class, LinkerConfigs.AnnihilationSkeletonSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityExchanger.class, LinkerConfigs.ExchangerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityMirrorSkeleton.class, LinkerConfigs.MirrorSkeletonSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpiderQueen.class, LinkerConfigs.SpiderQueenSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                    }

                    for (BiomeGenBase biome : underworldBiomes) {
                        BiomeSpawnUtil.addSpawn(EntityAncientBoneLord.class, LinkerConfigs.AncientBoneLordSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieDoorDeep.class, LinkerConfigs.ZombieDoorDeepSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpiderQueen.class, LinkerConfigs.SpiderQueenSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieMiner.class, LinkerConfigs.ZombieMinerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityZombieDoorLord.class, LinkerConfigs.ZombieDoorLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityBedrockElemental.class, LinkerConfigs.BedrockElementalSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityGhast.class, LinkerConfigs.GhastSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                    }
                    for (BiomeGenBase biome : hellBiomes) {
                        BiomeSpawnUtil.addSpawn(EntityZombiePigmanLord.class, LinkerConfigs.ZombiePigmanLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityBedrockElemental.class, LinkerConfigs.BedrockElementalSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityExchanger.class, LinkerConfigs.ExchangerSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityWitherBoneLord.class, LinkerConfigs.WitherBoneLordSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityPigmanLord.class, LinkerConfigs.PigmanLordSpawn.getIntegerValue(), 1, 2, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntitySpirit.class, LinkerConfigs.SpiritSpawn.getIntegerValue(), 1, 1, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityGhast.class, LinkerConfigs.GhastSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                        BiomeSpawnUtil.addSpawn(EntityInfernalCreeper.class, LinkerConfigs.InfernalCreeperSpawn.getIntegerValue(), 1, 4, EnumCreatureType.monster, biome);
                    }
                }
            }
        });
        
//        Trading.register(new Consumer<TradingRegisterEvent>() {
//            @Override
//            public void accept(TradingRegisterEvent event) {
//                event.getForProfession()
//            }
//        });
        
    }
}
