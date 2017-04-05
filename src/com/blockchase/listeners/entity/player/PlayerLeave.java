package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.listeners.BCListener;
import com.blockchase.threads.GameTimer;
import com.blockchase.utils.LocationUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave extends BCListener{

    public PlayerLeave(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();

        e.setQuitMessage("");
        PlayerHandler.playerLeave(p);

        if(PlayerHandler.getAliveList().size() == 1){

            GameTimer.gameOver();
            LocationUtilities.spawnFireworks();

        }

    }

}
