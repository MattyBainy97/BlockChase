package com.blockchase.threads;

import com.blockchase.BlockChase;
import com.blockchase.GameState;
import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import com.blockchase.utils.ScoreboardUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GameTimer implements Runnable {

    private static boolean stopped = false;
    private static boolean gameWon = false;
    private static int time;

    @Override
    public void run() {
        while (true) {
            if (GameState.isState(GameState.IN_GAME)) {
                for (; time >= 0; time++) {

                    if(stopped == true){

                        break;

                    }

                    if(gameWon == true){

                        GameState.setState(GameState.RESTART);
                        ChatUtilities.broadcast(ChatColor.DARK_RED + "GAME OVER");
                        ChatUtilities.broadcast("The winner is:");
                        for(UUID uuid : PlayerHandler.getAliveList()){

                            Player p = Bukkit.getPlayer(uuid);
                            ChatUtilities.broadcast(ChatColor.DARK_AQUA + p.getName());

                        }
                        ChatUtilities.broadcastServer(ChatColor.RED + "Server will restart in 10 seconds");

                        stopped = true;

                        Bukkit.getScheduler().scheduleSyncDelayedTask(BlockChase.getPlugin(), new Runnable(){

                            public void run() {

                                Bukkit.reload();

                            }

                        },200L);

                    }

                    for(Player p : Bukkit.getOnlinePlayers()){

                        ScoreboardUtilities.setTime(p, time);

                    }

                    if(time == 600){

                        GameState.setState(GameState.RESTART);
                        ChatUtilities.broadcast(ChatColor.DARK_RED + "GAME OVER");
                        if(PlayerHandler.getAliveList().size() > 1){

                            ChatUtilities.broadcast("There was no winner this game");
                            ChatUtilities.broadcast("The players that survived were:");
                            for(UUID uuid : PlayerHandler.getAliveList()){

                                Player p = Bukkit.getPlayer(uuid);
                                ChatUtilities.broadcast(ChatColor.DARK_AQUA + p.getName());

                            }
                            ChatUtilities.broadcastServer(ChatColor.RED + "Server will restart in 10 seconds");

                        }else{

                            ChatUtilities.broadcastServer(ChatColor.RED + "Something went wrong. This should never be called");
                            ChatUtilities.broadcastServer(ChatColor.RED + "Server will restart in 10 seconds");

                        }

                        stopped = true;

                        Bukkit.getScheduler().scheduleSyncDelayedTask(BlockChase.getPlugin(), new Runnable(){

                            public void run() {

                                Bukkit.reload();

                            }

                        },200L);

                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    public static void stopTimer(){

        stopped = true;

    }

    public static int getTime(){

        return time;

    }

    public static void gameOver(){

        gameWon = true;

    }

}
