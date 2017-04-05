package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.GameState;
import com.blockchase.listeners.BCListener;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AsyncPlayerPreLogin extends BCListener {

    public AsyncPlayerPreLogin(BlockChase pl) {
        super(pl);
    }

    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent e) {

        if (GameState.isState(GameState.IN_LOBBY)) {

            e.allow();

        } else {

            e.disallow(Result.KICK_OTHER, ChatColor.RED + "Game is in progress! Try joining later!");
            
        }

    }
}