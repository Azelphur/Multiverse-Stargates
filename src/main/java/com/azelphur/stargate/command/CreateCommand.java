package com.azelphur.stargate.command;

import com.azelphur.stargate.StargatePlugin;
import com.azelphur.stargate.util.Language;
import com.azelphur.stargate.util.Perms;
import com.dumptruckman.minecraft.pluginbase.util.Logging;
import com.onarandombox.MultiversePortals.MVPortal;
import com.onarandombox.MultiversePortals.utils.PortalManager;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.commands.ClipboardCommands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azelphur
 * Date: 07/04/12
 * Time: 03:06
 * To change this template use File | Settings | File Templates.
 */
public class CreateCommand extends SCommand {

    public CreateCommand(StargatePlugin plugin) {
        super(plugin);
        this.setName(messager.getMessage(Language.CMD_CREATE));
        this.setCommandUsage("/create <gate> [password]");
        this.setArgRange(1, 1);
        this.addKey("create");
        for (String prefix : plugin.getCommandPrefixes()) {
            this.addKey(prefix + " create");
        }
        this.addCommandExample("/create MyGate");
        this.addCommandExample("/create MyLockedGate 12345");
        this.setPermission(Perms.CMD_CREATE.getPermission());
    }

    @Override
    public void runCommand(CommandSender commandSender, List<String> strings) {
        Player player = (Player) commandSender;
        plugin.setCreateMode(player, true);
        Logging.info("Create Mode activated");
    }
}