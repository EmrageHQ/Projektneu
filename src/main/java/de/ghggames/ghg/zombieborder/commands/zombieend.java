package de.ghggames.ghg.zombieborder.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class zombieend implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        player.sendMessage("Du hast das Chaos gestartet.");
        Bukkit.broadcastMessage(player.getDisplayName() + " hat das Chaos beendet!");

        return false;
    }
}
