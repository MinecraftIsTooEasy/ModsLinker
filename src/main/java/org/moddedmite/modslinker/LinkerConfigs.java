package org.moddedmite.modslinker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.JsonUtils;
import net.oilcake.mitelros.feat.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class LinkerConfigs extends SimpleConfigs {
	public static final ConfigDouble RateMultiplier = new ConfigDouble("ITFR中怪物成长比率", 1, 0.1, 2.0);
	public static final ConfigDouble LichHealth = new ConfigDouble("巫妖生命值", 75.0D, 1.0D, 10000.0D);
	
	public static final ConfigInteger EndermanSpawn = new ConfigInteger("末影人刷新率", 10, 1, 100);
	public static final ConfigInteger AncientBoneLordSpawn = new ConfigInteger("古代亡骸领主刷新率", 5, 1, 100);
	public static final ConfigInteger InfernalCreeperSpawn = new ConfigInteger("地狱苦力怕刷新率", 10, 1, 100);
	public static final ConfigInteger GhastSpawn = new ConfigInteger("恶魂刷新率", 50, 1, 100);
	
	public static final ConfigInteger ZombieLordSpawn = new ConfigInteger("僵尸领主刷新率", 1, 1, 100);
	public static final ConfigInteger ZombiePigmanLordSpawn = new ConfigInteger("僵尸猪人领主刷新率", 1, 1, 100);
	public static final ConfigInteger WanderingWitchSpawn = new ConfigInteger("流浪巫女刷新率", 1, 1, 100);
	public static final ConfigInteger AnnihilationSkeletonSpawn = new ConfigInteger("湮灭骷髅刷新刷新率", 1, 1, 100);

	public static final ConfigInteger ExchangerSpawn = new ConfigInteger("转移骷髅刷新率", 2, 1, 100);
	public static final ConfigInteger ZombieDoorSpawn = new ConfigInteger("僵尸盗贼刷新率", 2, 1, 100);
	public static final ConfigInteger MirrorSkeletonSpawn = new ConfigInteger("镜像骷髅刷新率", 5, 1, 100);

	public static final ConfigInteger ZombieDoorDeepSpawn = new ConfigInteger("深层僵尸盗贼刷新率", 1, 1, 100);
	public static final ConfigInteger SpiderQueenSpawn = new ConfigInteger("蛛后刷新率", 6, 1, 100);
	public static final ConfigInteger ZombieMinerSpawn = new ConfigInteger("僵尸矿工刷新率", 6, 1, 100);
	public static final ConfigInteger ZombieDoorLordSpawn = new ConfigInteger("僵尸盗贼领主刷新率", 1, 1, 100);
	public static final ConfigInteger BedrockElementalSpawn = new ConfigInteger("基岩元素刷新率", 1, 1, 100);

	public static final ConfigInteger SpiderKingSpawn = new ConfigInteger("国王蜘蛛刷新率", 2, 1, 100);
	public static final ConfigInteger RetinueZombieSpawn = new ConfigInteger("僵尸扈从刷新率", 10, 1, 100);
	public static final ConfigInteger WitherBoneLordSpawn = new ConfigInteger("凋零骷髅领主刷新率", 1, 1, 100);
	public static final ConfigInteger PigmanLordSpawn = new ConfigInteger("猪人领主刷新率", 1, 1, 100);
	public static final ConfigInteger SpiritSpawn = new ConfigInteger("阴魂刷新率", 5, 1, 100);
	
	public static final ConfigInteger OverworldNickelFrequency = new ConfigInteger("主世界镍矿刷新率", 15, 1, 100);
	public static final ConfigInteger UnderworldNickelFrequency = new ConfigInteger("地下世界镍矿刷新率", 25, 1, 100);
	public static final ConfigInteger UnderworldTungstenFrequency = new ConfigInteger("地下世界钨矿刷新率", 5, 1, 100);

	public static final ConfigInteger FishVoucherFrequency = new ConfigInteger("钓鱼凭证权重", 2, 1, 100);
//	public static final ConfigInteger VillagerVoucherFrequency = new ConfigInteger("挖掘绿宝石掉落交易凭证概率", 50, 1, 100);

	public static final ConfigBoolean CustomNuggetRecipes = new ConfigBoolean("自定义粒合成锭配方", true);

	public static final List<ConfigBase<?>> general;
	public static final List<ConfigBase<?>> values;

	public LinkerConfigs(String name, List<ConfigHotkey> hotkeys, List<?> values) {
		super(name, hotkeys, values);
	}
	
	public static final List<ConfigTab> configTabs = new ArrayList<>();
	public static final LinkerConfigs Instance;
	
	static {
		general = List.of(RateMultiplier, LichHealth,
				EndermanSpawn, AncientBoneLordSpawn, InfernalCreeperSpawn, GhastSpawn,
				ZombieLordSpawn, ZombiePigmanLordSpawn, WanderingWitchSpawn, AnnihilationSkeletonSpawn,
				ExchangerSpawn, ZombieDoorSpawn, MirrorSkeletonSpawn,
				ZombieDoorDeepSpawn, SpiderQueenSpawn, ZombieMinerSpawn, ZombieDoorLordSpawn, BedrockElementalSpawn,
				SpiderKingSpawn, RetinueZombieSpawn, WitherBoneLordSpawn, PigmanLordSpawn, SpiritSpawn,
				OverworldNickelFrequency, UnderworldNickelFrequency, UnderworldTungstenFrequency,
				FishVoucherFrequency,
				CustomNuggetRecipes);

		values = new ArrayList<>();
		values.addAll(general);

		Instance = new LinkerConfigs(ModsLinker.MOD_ID, null, values);
		
		Difficulty.ultimateDifficulty = Difficulty.calculateUltimateDifficulty();
		
		
		configTabs.add(new ConfigTab("general", general));
	}
	
	public static LinkerConfigs getInstance() {
		return Instance;
	}
	
	@Override
	public List<ConfigTab> getConfigTabs() {
		return configTabs;
	}
	
	@Override
	public void save() {
		JsonObject root = new JsonObject();
		ConfigUtils.writeConfigBase(root, "general", general);
		JsonUtils.writeJsonToFile(root, this.optionsFile);
	}
	
	@Override
	public void load() {
		if (!this.optionsFile.exists()) {
			this.save();
		} else {
			JsonElement jsonElement = JsonUtils.parseJsonFile(this.optionsFile);
			if (jsonElement != null && jsonElement.isJsonObject()) {
				JsonObject root = jsonElement.getAsJsonObject();
				ConfigUtils.readConfigBase(root, "general", general);
			}
		}
	}
}
