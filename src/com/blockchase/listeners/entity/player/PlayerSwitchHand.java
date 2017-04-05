package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwitchHand extends BCListener{

    public PlayerSwitchHand(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerSwitchHand(PlayerSwapHandItemsEvent e){

        e.setCancelled(true);

    }

}
