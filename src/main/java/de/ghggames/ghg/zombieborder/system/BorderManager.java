package de.ghggames.ghg.zombieborder.system;


import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.StructureType;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.ArrayList;







public class BorderManager {
    private static boolean inGame = false;

    public static boolean isInGame() {
        return inGame;
    }





    public static void startGame(Player player) {
        if (getStrongholdLocation(player.getLocation()) == null) return;
        player.sendMessage("§3Bitte warte kurz...");
        inGame = true;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {

            p.teleport(getStrongholdLocation(null));
            p.getInventory().clear();
            if (p.getUniqueId() != player.getUniqueId()) {
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(player.getDisplayName() + " hat das Chaos gestartet!");
            }
        }
        player.sendMessage("§2Es kann los gehen!");
        Bukkit.getWorld("world").getWorldBorder().setCenter(getStrongholdLocation(null));
        Bukkit.getWorld("world").getWorldBorder().setSize(1);
    }

    private static Location sLocation;
    public static Location getStrongholdLocation (Location location) {
        if (location == null || isInGame()) {
            if (sLocation != null) {
                return sLocation;
            }
        }
        if (location == null) {
            Bukkit.broadcastMessage("§4Es ist ein Fehler aufgetreten! Mehr Infos:");
            Bukkit.broadcastMessage("§4Das Spiel wurde nicht ordnungsgemäß gestartet! Bitte versuche es erneut!");
            return null;
        }
        Location temploc = location.getWorld().locateNearestStructure(location, StructureType.STRONGHOLD, 100, false);
        if (temploc == null) {
            Bukkit.broadcastMessage("§4Es ist ein Fehler aufgetreten! Mehr Infos:");
            Bukkit.broadcastMessage("§4Structure wurde nicht gefunden! Versuche dich in die nähe eines Strongholds zu begeben!");

            return null;
        }


        sLocation = new Location(temploc.getWorld(), temploc.getX() + 0.5, temploc.getWorld().getHighestBlockYAt(temploc), temploc.getZ() + 0.5);
        return sLocation;

    }
}
