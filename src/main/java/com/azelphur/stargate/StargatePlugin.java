package com.azelphur.stargate;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.azelphur.stargate.command.CreateCommand;
import com.azelphur.stargate.command.DialCommand;
import com.azelphur.stargate.util.Language;
import com.dumptruckman.minecraft.pluginbase.commandhandler.Command;
import com.dumptruckman.minecraft.pluginbase.plugin.AbstractBukkitPlugin;
import com.dumptruckman.minecraft.pluginbase.plugin.command.HelpCommand;
import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.MultiversePortals;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 05/04/12
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class StargatePlugin extends AbstractBukkitPlugin<SConfig> {
    private final List<String> cmdPrefixes = Arrays.asList("mvsg");
    protected MultiversePortals portalsAPI;
    protected WorldEditPlugin worldEditPlugin;
    Map<Player, Boolean> inCreateMode = new HashMap<Player, Boolean>();
    Map<Player, MVPortal> activatedGates = new HashMap<Player, MVPortal>();
    @Override
    protected SConfig newConfigInstance() throws IOException {
            return new CommentedConfig(this, true, new File(getDataFolder(), "config.yml"), SConfig.class);
    }

    @Override
    public List<String> getCommandPrefixes() {
        return cmdPrefixes;
    }

    public void preEnable() {
        Language.init();
    }
    @Override
    public final void postEnable() {
        getCommandHandler().registerCommand(new DialCommand(this));
        getCommandHandler().registerCommand(new CreateCommand(this));

        getServer().getPluginManager().registerEvents(new StargateListener(this), this);
        for (Command c : getCommandHandler().getAllCommands()) {
            if (c instanceof HelpCommand) {
                for (String key : getCommandPrefixes()) {
                    c.addKey(key);
                }
            }
        }
        this.checkForWorldEdit();
        this.checkForPortals();

        String sql = "SELECT VERSION() AS VERSION";
        SqlRow row =
                Ebean.createSqlQuery(sql)
                        .findUnique();

        String i = row.getString("count");

        System.out.println("Got "+i+"  - DataSource good.");
    }

    public void setSelectedPortal(Player player, MVPortal portal) {
        activatedGates.put(player, portal);
    }

    public MVPortal getSelectedPortal(Player player) {
        return activatedGates.get(player);
    }

    public void setCreateMode(Player player, Boolean b) {
        inCreateMode.put(player, b);
    }

    public Boolean getCreateMode(Player player) {
        return inCreateMode.get(player);
    }

    private void checkForWorldEdit() {
        if (this.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
            worldEditPlugin = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        }
    }

    private void checkForPortals() {
        if (this.getServer().getPluginManager().getPlugin("Multiverse-Portals") != null) {
            portalsAPI = (MultiversePortals) getServer().getPluginManager().getPlugin("Multiverse-Portals");
        }
    }

    public MultiversePortals getMVP() { return portalsAPI; }

    public WorldEditPlugin getWEAPI() { return this.worldEditPlugin; }
}

