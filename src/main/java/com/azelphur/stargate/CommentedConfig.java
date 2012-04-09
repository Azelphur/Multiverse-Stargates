package com.azelphur.stargate;

import com.dumptruckman.minecraft.pluginbase.config.AbstractYamlConfig;
import com.dumptruckman.minecraft.pluginbase.plugin.BukkitPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 05/04/12
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class CommentedConfig extends AbstractYamlConfig<SConfig> implements SConfig {

    public CommentedConfig(BukkitPlugin plugin, boolean doComments, File configFile, Class<? extends SConfig>... configClasses) throws IOException {
        super(plugin, doComments, configFile, configClasses);
    }

    @Override
    protected String getHeader() {
        return "";
    }
}