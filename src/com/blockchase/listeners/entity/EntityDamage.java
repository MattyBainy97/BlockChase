package com.blockchase.listeners.entity;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import com.blockchase.utils.ChatUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage extends BCListener{

    public EntityDamage(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){

        if(e.getEntity() instanceof Player) {

            if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION || e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK || e.getCause() == EntityDamageEvent.DamageCause.FALL) {

                e.setDamage(5);

            }

        }

    }

}
