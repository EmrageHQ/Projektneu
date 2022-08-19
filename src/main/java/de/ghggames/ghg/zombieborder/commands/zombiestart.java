package de.ghggames.ghg.zombieborder.commands;

import de.ghggames.ghg.zombieborder.system.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class zombiestart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        Player player = (Player) sender;
        player.sendMessage("Du hast das Chaos gestartet.");
        
        

        BorderManager.startGame(player);

        return false;
    }
}
