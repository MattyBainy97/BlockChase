package com.blockchase.listeners.entity;

import com.blockchase.BlockChase;
import com.blockchase.GameState;
import com.blockchase.handlers.player.PlayerHandler;
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

            Player p = ((Player) e.getEntity()).getPlayer();

            if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION || e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK || e.getCause() == EntityDamageEvent.DamageCause.FALL) {

                e.setDamage(5);

            }

            if(PlayerHandler.isSpec(p)){

                e.setCancelled(true);

            }

            if(GameState.isState(GameState.IN_LOBBY)){

                e.setCancelled(true);

            }

        }

    }

}
