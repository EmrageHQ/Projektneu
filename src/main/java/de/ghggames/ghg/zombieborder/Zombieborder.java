package de.ghggames.ghg.zombieborder;

import de.ghggames.ghg.zombieborder.commands.zombieend;
import de.ghggames.ghg.zombieborder.commands.zombiehitch;
import de.ghggames.ghg.zombieborder.commands.zombiestart;
import de.ghggames.ghg.zombieborder.listeners.hittchListener;
import de.ghggames.ghg.zombieborder.system.BorderManager;
import de.ghggames.ghg.zombieborder.system.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import static de.ghggames.ghg.zombieborder.system.Spawn.spawnZombieWave;

public final class Zombieborder extends JavaPlugin {

    public static int state = 0;
    private static int timer = 60;
    private static int timerset = 60;

    public static void setTimer(int time) {
        timer = time;
        timerset = time;
    }


    @Override
    public void onEnable() {
        Iterator<KeyedBossBar> it = Bukkit.getServer().getBossBars();
        while(it.hasNext()) {
                    ((KeyedBossBar)it.next()).setVisible(false);
                }
        // Plugin startup logic
        this.getCommand("zombiestart").setExecutor(new zombiestart());
        this.getCommand("zombieend").setExecutor(new zombieend());
        this.getCommand("zombiehitch").setExecutor(new zombiehitch());
        //Bukkit.getPluginManager().registerEvents(new hittchListener() , this);

        Bukkit.setSpawnRadius(1);





        final BossBar b = Bukkit.createBossBar("load...", BarColor.GREEN, BarStyle.SOLID, new BarFlag[0]);
        b.setProgress(0.0);

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle("", "Plugin von Elektroblock", 0,10,10);
                }
                if (BorderManager.isInGame()) {
                    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

                    while(var1.hasNext()) {
                        Player player = (Player)var1.next();
                        b.addPlayer(player);
                    }

                    if (timer >= 0) {
                    --timer;
                    b.setVisible(true);
                    int minuten = timer % 3600 / 60;
                    int sekunden = timer % 3600 % 60;
                    b.setTitle("§r§3Welle §l" + state+1 + "§r§3 startet in §l" + minuten + " §r§3Minuten und §l" + sekunden + " §r§3Sekunden");
                    b.setProgress((double)timer / (double)timerset);
                    b.setColor(BarColor.YELLOW);
                    }

                    if (timer == 0) {
                        ++state;
                        spawnZombieWave();

                    }

                    if (timer < 0) {
                        b.setTitle("§r§3in Welle§l " + state + "§r§3 verbleiben noch §l" + Spawn.getZombieCount() + "§r§3 Zombies!");
                        if(Spawn.getZombieCount() <= 0) {
                            BorderManager.addBorder(1);
                            setTimer(60);
                        }
                    }
                } else {
                    b.setVisible(false);
                }



            }
        }, 0L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
