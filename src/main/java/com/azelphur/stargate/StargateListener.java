package com.azelphur.stargate;

import com.dumptruckman.minecraft.pluginbase.plugin.BukkitPlugin;
import com.dumptruckman.minecraft.pluginbase.util.Logging;
import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.utils.PortalManager;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Lever;

import java.io.IOException;
import java.util.logging.Logger;

import static org.bukkit.block.BlockFace.*;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 06/04/12
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class StargateListener implements Listener {
    private StargatePlugin stargatePlugin;
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void playerInteract(PlayerInteractEvent event) throws MaxChangedBlocksException {
        Block block = event.getClickedBlock();
        if (block.getType() == Material.LEVER) {
            Player player = event.getPlayer();
            if (stargatePlugin.getCreateMode(player)) {
                WorldEditPlugin worldEditPlugin = stargatePlugin.getWEAPI();
                CuboidClipboard clipboard = null;
                try {
                    clipboard = CuboidClipboard.loadSchematic(new java.io.File(stargatePlugin.getDataFolder(), "/schematics/gate1.schematic"));
                } catch (IOException e) {
                    Logging.severe("Shit broke");
                } catch (DataException e) {
                    Logging.severe("Shit broke");
                }
                Location location = block.getLocation();
                Logging.info(String.valueOf(location.getBlockX()));
                Vector vector = new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
                EditSession editSession = worldEditPlugin.createEditSession(player);
                try {
                    clipboard.paste(editSession, vector, false);
                } catch (MaxChangedBlocksException e) {
                    Logging.severe("Shit broke");
                }
                editSession.flushQueue();
            }
            else {
                Lever lever = (Lever) block.getState().getData();
                PortalManager portalManager;
                portalManager = stargatePlugin.getMVP().getPortalManager();
                MVPortal sourcePortal = null;
                switch (lever.getAttachedFace()) {
                    case SOUTH:
                        sourcePortal = portalManager.getPortal(block.getLocation().add(4.0, 0, -2));
                }
                Logging.info(sourcePortal.getName());
                stargatePlugin.setSelectedPortal(event.getPlayer(), sourcePortal);
            }
        }
    }

    public StargateListener(StargatePlugin plugin) {
        stargatePlugin = plugin;
    }
}