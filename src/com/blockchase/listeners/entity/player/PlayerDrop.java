package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDrop extends BCListener{

    public PlayerDrop(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){

        e.setCancelled(true);

    }

}
