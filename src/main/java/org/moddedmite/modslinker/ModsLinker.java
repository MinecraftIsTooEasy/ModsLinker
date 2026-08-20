package org.moddedmite.modslinker;

import net.xiaoyu233.fml.reload.event.MITEEvents;
import org.moddedmite.modslinker.event.LinkerEvents;
import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import net.xiaoyu233.fml.ModResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModsLinker implements ModInitializer {
    public static final String MOD_ID = "modslinker";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModResourceManager.addResourcePackDomain(MOD_ID);
        LinkerEvents.register();
        MITEEvents.MITE_EVENT_BUS.register(new LinkerEvents());
        LinkerConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(LinkerConfigs.getInstance());
    }
}
