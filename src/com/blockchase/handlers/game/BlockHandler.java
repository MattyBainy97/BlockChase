package com.blockchase.handlers.game;

import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.threads.GameTimer;
import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

public class BlockHandler implements Runnable{

    public static int fallHeight = 5;
    private boolean beaconSpawned = false;

    public void run() {

        for (Player p : Bukkit.getOnlinePlayers()) {

            double x = roundToHalf(p.getLocation().getX());
            double y = p.getLocation().getY() + fallHeight;
            double z = roundToHalf(p.getLocation().getZ());
            Location spawnLoc = new Location(p.getWorld(), x, y, z);

            byte color = PlayerHandler.getColor(p);

            if(!PlayerHandler.isSpec(p)) {

                if (LocationUtilities.isInside(spawnLoc)) {

                    FallingBlock fb = p.getWorld().spawnFallingBlock(spawnLoc, new MaterialData(Material.WOOL, color));
                    fb.setHurtEntities(true);
                    fb.setDropItem(false);

                } else if (p.getLocation().getY() >= 26 && p.getLocation().getY() <= 28) {

                    double y2 = p.getLocation().getY() + 1;
                    Location spawnLoc2 = new Location(p.getWorld(), x, y2, z);
                    FallingBlock fb = p.getWorld().spawnFallingBlock(spawnLoc2, new MaterialData(Material.WOOL, color));
                    fb.setHurtEntities(true);
                    fb.setDropItem(false);

                }

            }

        }

        if(GameTimer.getTime() % 60 == 0 && GameTimer.getTime() != 0 && GameTimer.getTime() != 300){

            if(!beaconSpawned) {

                LocationUtilities.spawnPowerup();
                ChatUtilities.broadcast(ChatColor.AQUA + "BEACON SPAWNED");
                ChatUtilities.broadcast("Right click it to get a random powerup");
                beaconSpawned = true;

            }else{

                beaconSpawned = false;

            }

        }

    }

    private double roundToHalf(double d) {

        double result = Math.round(d * 2) / 2.0;

        if(result == (int)result){

            result += 0.5;

        }

        return result;

    }

}
