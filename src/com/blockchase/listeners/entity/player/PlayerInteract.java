package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.handlers.game.PowerupHandler;
import com.blockchase.listeners.BCListener;
import com.blockchase.utils.ChatUtilities;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract extends BCListener{

    public PlayerInteract(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();

        if(!p.getGameMode().equals(GameMode.CREATIVE)) {

            if(e.getHand() == EquipmentSlot.HAND) {

                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    if (e.getClickedBlock().getType() == Material.BEACON) {

                        e.setCancelled(true);
                        PowerupHandler.collectPowerup(PowerupHandler.getRandomPowerup(), p);
                        p.playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.5F, 2.0F);
                        e.getClickedBlock().setType(Material.AIR);

                    } else if (p.getEquipment().getItemInMainHand().equals(PowerupHandler.getRestoreHearts())) {

                        if (p.getHealth() < 20.0) {

                            p.setHealth(p.getHealth() + 5);
                            p.getInventory().remove(PowerupHandler.getRestoreHearts());
                            ChatUtilities.onePlayer(ChatColor.GREEN + "Health restored", p);

                        } else {

                            ChatUtilities.onePlayer(ChatColor.RED + "No health missing. Can't heal", p);

                        }

                    }

                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

                    if (p.getEquipment().getItemInMainHand().equals(PowerupHandler.getRestoreHearts())) {

                        if (p.getHealth() < 20.0) {

                            p.setHealth(p.getHealth() + 5);
                            p.getInventory().remove(PowerupHandler.getRestoreHearts());
                            ChatUtilities.onePlayer(ChatColor.GREEN + "Health restored", p);

                        } else {

                            ChatUtilities.onePlayer(ChatColor.RED + "No health missing. Can't heal", p);

                        }

                    }

                } else {

                    e.setCancelled(true);

                }

            }

        }

    }

}
