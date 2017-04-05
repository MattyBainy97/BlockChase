package com.blockchase.listeners.entity;

import com.blockchase.BlockChase;
import com.blockchase.handlers.game.PowerupHandler;
import com.blockchase.listeners.BCListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDamageByEntity extends BCListener{

    public EntityDamageByEntity(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){

        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){

            Player damaged = ((Player) e.getEntity()).getPlayer();
            Player damager = ((Player) e.getDamager()).getPlayer();

            if(damager.getEquipment().getItemInMainHand() == PowerupHandler.getSlowStick()){

                e.setCancelled(true);
                damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
                damager.getInventory().remove(PowerupHandler.getSlowStick());

            }else {

                e.setCancelled(true);

            }

        }

    }

}
