package com.blockchase.listeners.world;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak extends BCListener{

    public BlockBreak(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {

            e.setCancelled(true);

        }

    }

}
