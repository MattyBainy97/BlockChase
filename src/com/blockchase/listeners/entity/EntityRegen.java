package com.blockchase.listeners.entity;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityRegen extends BCListener{

    public EntityRegen(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onEntityRegen(EntityRegainHealthEvent e){

        if (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN) {

            e.setCancelled(true);

        }

    }

}
