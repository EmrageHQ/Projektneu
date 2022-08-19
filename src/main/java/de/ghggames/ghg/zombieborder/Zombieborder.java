package de.ghggames.ghg.zombieborder;

import de.ghggames.ghg.zombieborder.commands.zombieend;
import de.ghggames.ghg.zombieborder.commands.zombiehitch;
import de.ghggames.ghg.zombieborder.commands.zombiestart;
import de.ghggames.ghg.zombieborder.listeners.hittchListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Zombieborder extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("zombiestart").setExecutor(new zombiestart());
        this.getCommand("zombieend").setExecutor(new zombieend());
        this.getCommand("zombiehitch").setExecutor(new zombiehitch());
        //Bukkit.getPluginManager().registerEvents(new hittchListener() , this);

        Bukkit.setSpawnRadius(1);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
