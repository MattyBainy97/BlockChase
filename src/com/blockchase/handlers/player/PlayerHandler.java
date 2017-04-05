package com.blockchase.handlers.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.blockchase.GameState;
import org.bukkit.entity.Player;

public class PlayerHandler {

    private static ArrayList<UUID> online = new ArrayList<UUID>();
    private static ArrayList<UUID> playerList = new ArrayList<UUID>();
    private static ArrayList<UUID> alive = new ArrayList<UUID>();
    private static ArrayList<UUID> spec = new ArrayList<UUID>();

    public static void playerJoin(Player p){

        UUID uuid = p.getUniqueId();

        online.add(uuid);

        if(GameState.isState(GameState.IN_GAME)){

            spec.add(uuid);

        }else{

            playerList.add(uuid);

        }

    }

    public static void playerLeave(Player p){

        UUID uuid = p.getUniqueId();

        online.remove(uuid);

        if(GameState.isState(GameState.IN_GAME)){

            if(alive.contains(uuid)){

                alive.remove(uuid);

            }else{

                spec.remove(uuid);

            }

        }else{

            playerList.remove(uuid);

        }

    }

    public static void playerDeath(Player p){

        UUID uuid = p.getUniqueId();

        alive.remove(uuid);
        spec.add(uuid);

    }

    public static void gameStart(Player p){

        UUID uuid = p.getUniqueId();

        alive.add(uuid);

    }

    public static ArrayList<UUID> getPlayerList(){

        return playerList;

    }

    public static ArrayList<UUID> getAliveList(){

        return alive;

    }

    public static void clearLists(){

        online.clear();
        playerList.clear();
        alive.clear();
        spec.clear();

    }

    public static byte getColor(Player p){

        int playerNum = 0;
        byte color = 0;

        for(UUID uuid : PlayerHandler.getPlayerList()){

            if(uuid.equals(p.getUniqueId())){

                break;

            }else{

                playerNum++;

            }

        }

        switch (playerNum){

            case 0:
                color = 14;
                break;
            case 1:
                color = 11;
                break;
            case 2:
                color = 9;
                break;
            case 3:
                color = 2;
                break;
            case 4:
                color = 1;
                break;
            case 5:
                color = 4;
                break;
            case 6:
                color = 6;
                break;
            case 7:
                color = 5;
                break;
            case 8:
                color = 13;
                break;
            case 9:
                color = 3;
                break;
            case 10:
                color = 12;
                break;
            case 11:
                color = 7;
                break;
            default:
                color = 0;
                break;

        }

        return color;

    }

    public static boolean isSpec(Player p) {

        UUID uuid = p.getUniqueId();

        if(spec.contains(uuid)){

            return true;

        }else{

            return false;

        }

    }
}
