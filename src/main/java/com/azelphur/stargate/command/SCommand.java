package com.azelphur.stargate.command;

import com.azelphur.stargate.StargatePlugin;
import com.dumptruckman.minecraft.pluginbase.plugin.command.PluginCommand;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 05/04/12
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public abstract class SCommand extends PluginCommand<StargatePlugin> {

    public SCommand(StargatePlugin plugin) {
        super(plugin);
    }
}