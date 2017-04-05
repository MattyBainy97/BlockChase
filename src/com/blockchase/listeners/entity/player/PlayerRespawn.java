package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import com.blockchase.threads.GameTimer;
import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn extends BCListener{

    public PlayerRespawn(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){

        e.setRespawnLocation(LocationUtilities.randomDeathSpawn());

        String currentTimeString = String.format("%02d:%02d", GameTimer.getTime() / 60, GameTimer.getTime() % 60);

        ChatUtilities.onePlayer(ChatColor.RED + "YOU DIED", e.getPlayer());
        ChatUtilities.onePlayer("You survived for " + ChatColor.AQUA + currentTimeString, e.getPlayer());

    }

}
