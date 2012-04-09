package com.azelphur.stargate.command;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 05/04/12
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */

import com.azelphur.stargate.StargatePlugin;
import com.azelphur.stargate.util.Language;
import com.azelphur.stargate.util.Perms;
import com.dumptruckman.minecraft.pluginbase.util.Logging;
import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.MultiversePortals;
import com.onarandombox.MultiversePortals.utils.PortalManager;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;
import java.util.logging.Logger;

public class DialCommand extends SCommand {

    public DialCommand(StargatePlugin plugin) {
        super(plugin);
        this.setName(messager.getMessage(Language.CMD_DIAL));
        this.setCommandUsage("/dial <gate> [password]");
        this.setArgRange(1, 1);
        this.addKey("dial");
        this.addKey("d");
        for (String prefix : plugin.getCommandPrefixes()) {
            this.addKey(prefix + " dial");
        }
        this.addCommandExample("/dial MyGate");
        this.addCommandExample("/dial MyLockedGate 12345");
        this.setPermission(Perms.CMD_DIAL.getPermission());
    }

    @Override
    public void runCommand(CommandSender commandSender, List<String> strings) {
        Player player = (Player) commandSender;

        MVPortal sourcePortal, destPortal;
        PortalManager portalManager;
        portalManager = plugin.getMVP().getPortalManager();
        sourcePortal = plugin.getSelectedPortal(player);
        destPortal = portalManager.getPortal(strings.get(0));
        sourcePortal.setDestination("p:" + strings.get(0));
        destPortal.setDestination("p:" + plugin.getSelectedPortal(player).getName());
    }
}