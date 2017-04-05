package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.GameState;
import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.listeners.BCListener;
import java.lang.reflect.Field;

import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoin extends BCListener {
    
    public PlayerJoin(BlockChase pl) {

        super(pl);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        e.setJoinMessage("");
        final Player p = e.getPlayer();

        CraftPlayer craftplayer = (CraftPlayer) p;

        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = ChatSerializer.a("{\"text\": \"   §9BlockChase §cv1   \"}");
        IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"   §4Development Test   \"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        }

        connection.sendPacket(packet);

        if (GameState.isState(GameState.IN_LOBBY)) {

            ChatUtilities.broadcast(ChatColor.DARK_AQUA + e.getPlayer().getName() + ChatColor.GOLD + " joined the game");
            LocationUtilities.spawnLobby(p);
            PlayerHandler.playerJoin(p);
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.setHealth(20.0);
            p.setFoodLevel(40);
            p.setExp(0);
            p.setLevel(0);
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.setGameMode(GameMode.ADVENTURE);

        } else {
            
            //todo

        }

    }
    
}