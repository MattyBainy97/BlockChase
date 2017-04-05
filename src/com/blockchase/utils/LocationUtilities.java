package com.blockchase.utils;

import com.blockchase.handlers.game.FireworkHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.Random;

public class LocationUtilities {

    private static Location[] deathSpawns = new Location[3];

    public static void initializeSpawns(){

        deathSpawns[0] = new Location(Bukkit.getWorld("BlockChase"), 859.0, 7.1, -432.0, -90, 0);
        deathSpawns[1] = new Location(Bukkit.getWorld("BlockChase"), 880.0, 7.1, -409.5, -180, 0);
        deathSpawns[2] = new Location(Bukkit.getWorld("BlockChase"), 903.5, 7.1, -432.0, 90, 0);

    }

    public static Location randomDeathSpawn(){

        Random rand = new Random();
        int random = rand.nextInt(2);

        return deathSpawns[random];

    }

    public static void spawnPlayer(Player p){

        Random rand = new Random();
        int x = rand.nextInt((892 - 865) + 1) + 865;
        int z = rand.nextInt((-419 + 446) + 1) - 446;

        p.teleport(new Location(p.getWorld(), x, 4, z));

    }

    public static void spawnPowerup(){

        clearOldBeacons();

        for(Player p : Bukkit.getOnlinePlayers()){

            p.getInventory().clear();

        }

        Random rand = new Random();
        double x = roundToHalf(rand.nextInt((892 - 865) + 1) + 865);
        double z = roundToHalf(rand.nextInt((-419 + 446) + 1) - 446);

        Location spawnLoc = new Location(Bukkit.getWorld("BlockChase"), x, 28, z);

        spawnLoc.getWorld().playSound(spawnLoc, Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 1.0F);

        FallingBlock fb = spawnLoc.getWorld().spawnFallingBlock(spawnLoc, new MaterialData(Material.BEACON));
        fb.setHurtEntities(true);
        fb.setDropItem(false);

    }

    private static double roundToHalf(double d) {

        double result = Math.round(d * 2) / 2.0;

        if(result == (int)result){

            result += 0.5;

        }

        return result;

    }

    public static void spawnFireworks(){

        for(int i = 0; i < 10; i++){

            Random rand = new Random();
            int x = rand.nextInt((892 - 865) + 1) + 865;
            int z = rand.nextInt((-419 + 446) + 1) - 446;

            FireworkHandler.newFirework(new Location(Bukkit.getWorld("BlockChase"), x, 4, z));

        }

    }

    public static void spawnLobby(Player p){

        p.teleport(new Location(p.getWorld(), 919.5, 4.1, -391.5, 90,0));

    }

    public static boolean isInside(Location loc) {

        int xMin = 864;
        int xMax = 895;
        int yMin = 4;
        int yMax = 28;
        int zMin = -448;
        int zMax = -417;
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax && z >= zMin && z <= zMax);

    }

    public static void clearField() {

        int xMin = 864;
        int xMax = 895;
        int yMin = 4;
        int yMax = 28;
        int zMin = -448;
        int zMax = -417;

        ArrayList<Block> blocks = new ArrayList<Block>();

        for (int x = xMin; x <= xMax; x++) {

            for (int z = zMin; z <= zMax; z++) {

                for (int y = yMin; y <= yMax; y++) {

                    blocks.add(Bukkit.getWorld("BlockChase").getBlockAt(x, y, z));

                }

            }

        }

        for (Block b : blocks) {

            if (b.getState().getData() instanceof Wool || b.getType().equals(Material.BEACON)) {

                b.setType(Material.AIR);

            }
        }

    }

    public static void clearOldBeacons() {

        int xMin = 864;
        int xMax = 895;
        int yMin = 4;
        int yMax = 28;
        int zMin = -448;
        int zMax = -417;

        ArrayList<Block> blocks = new ArrayList<Block>();

        for (int x = xMin; x <= xMax; x++) {

            for (int z = zMin; z <= zMax; z++) {

                for (int y = yMin; y <= yMax; y++) {

                    blocks.add(Bukkit.getWorld("BlockChase").getBlockAt(x, y, z));

                }

            }

        }

        for (Block b : blocks) {

            if (b.getType().equals(Material.BEACON)) {

                b.setType(Material.AIR);

            }
        }

    }

}
