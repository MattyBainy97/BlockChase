package com.blockchase.listeners.world;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace extends BCListener{

    public BlockPlace(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {

            e.setCancelled(true);

        }

    }

}
