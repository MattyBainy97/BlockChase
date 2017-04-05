package com.blockchase.utils;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class ChatUtilities {

    public static void broadcast(String msg) {

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.sendMessage(starter() + msg);

        }

    }

    public static void broadcastServer(String msg) {

            Bukkit.broadcastMessage(serverStarter() + msg);

    }

    public static void broadcastNoStarter(String msg) {

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.sendMessage(msg);

        }

    }

    public static void infoBar(String msg) {

        for (Player p : Bukkit.getOnlinePlayers()) {

            CraftPlayer craftplayer = (CraftPlayer) p;
            PlayerConnection connection = craftplayer.getHandle().playerConnection;
            IChatBaseComponent warning = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
            PacketPlayOutChat packet = new PacketPlayOutChat();

            try {
                Field field = packet.getClass().getDeclaredField("a");
                field.setAccessible(true);
                field.set(packet, warning);
                field.setAccessible(!field.isAccessible());

                Field Field2 = packet.getClass().getDeclaredField("b");
                Field2.setAccessible(true);
                Field2.set(packet, (byte) 2);
                Field2.setAccessible(!Field2.isAccessible());

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            connection.sendPacket(packet);

        }

    }

    public static void showList(Player p) {



    }

    public static void onePlayer(String msg, Player player) {

        player.sendMessage(starter() + msg);

    }

    public static void onePlayerServer(String msg, Player player) {

        player.sendMessage(serverStarter() + msg);

    }

    public static void chat(String msg, Player player) {

        broadcastNoStarter(chatStarter(player) + DARK_AQUA + player.getName() + GRAY + " » " + WHITE + msg);

    }

    private static String chatStarter(Player p) {

        return "";

    }

    private static String starter() {

        return GRAY + "[" + AQUA + "BlockChase" + GRAY + "] " + GOLD;

    }

    private static String serverStarter() {

        return GRAY + "[" + RED + "Red" + GREEN + "Apple" + RED + "Core" + GRAY + "] " + GOLD;

    }

}
