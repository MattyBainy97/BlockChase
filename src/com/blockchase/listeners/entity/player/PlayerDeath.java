package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.listeners.BCListener;
import com.blockchase.threads.GameTimer;
import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import com.blockchase.utils.ScoreboardUtilities;
import net.minecraft.server.v1_11_R1.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends BCListener{

    public PlayerDeath(BlockChase pl){

        super(pl);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        e.setDeathMessage("");
        e.getDrops().clear();
        e.setDroppedExp(0);
        Player p = e.getEntity().getPlayer();

        PlayerHandler.playerDeath(p);

        for(Player player : Bukkit.getOnlinePlayers()){

            ScoreboardUtilities.setAlivePlayers(player);

        }

        EntityDamageEvent.DamageCause cause = e.getEntity().getLastDamageCause().getCause();

        if(cause == EntityDamageEvent.DamageCause.FALL){

            ChatUtilities.broadcast(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " went splat");

        } else if(cause == EntityDamageEvent.DamageCause.SUFFOCATION){

            ChatUtilities.broadcast(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " choked on a block");

        } else {

            ChatUtilities.broadcast(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " was squished");

        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BlockChase.getPlugin(), new Runnable() {
            @Override
            public void run() {

                ((CraftPlayer) p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));

            }
        }, 5L);

        if(PlayerHandler.getAliveList().size() <= 1){

            GameTimer.gameOver();
            LocationUtilities.spawnFireworks();

        }

    }

}
