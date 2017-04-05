package com.blockchase.handlers.game;

import com.blockchase.BlockChase;
import com.blockchase.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

import static com.blockchase.handlers.game.PowerupHandler.PowerupType.*;

public class PowerupHandler {

    private static ItemStack slowStick = new ItemStack(Material.STICK, 1);
    private static ItemStack restoreHearts = new ItemStack(Material.REDSTONE, 1);

    public static void initializePowerups(){

        ItemMeta slowStickMeta = slowStick.getItemMeta();
        slowStickMeta.setDisplayName(ChatColor.GREEN + "Slow Stick");
        slowStickMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        slowStick.setItemMeta(slowStickMeta);

        ItemMeta restoreHeartsMeta = restoreHearts.getItemMeta();
        restoreHeartsMeta.setDisplayName(ChatColor.GREEN + "Restore 2.5 Hearts");
        restoreHearts.setItemMeta(restoreHeartsMeta);

    }

    public static ItemStack getSlowStick() {

        return slowStick;

    }

    public static ItemStack getRestoreHearts() {

        return restoreHearts;

    }

    public static enum PowerupType{

        SLOW_STICK, BLINDNESS, JUMP_BOOST, RESTORE_HEARTS, INVISIBILITY;

    }

    public static void collectPowerup(PowerupType powerup, Player p){

        String start = "You picked up: ";

        switch(powerup){

            case SLOW_STICK:
                p.getInventory().addItem(slowStick);
                ChatUtilities.onePlayer(start + ChatColor.GREEN + "Slow Stick", p);
                ChatUtilities.onePlayer("Swing this stick at someone to slow them for 3 seconds", p);
                ChatUtilities.onePlayer("You only have this powerup for 5 seconds", p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(BlockChase.getPlugin(), new Runnable(){

                    public void run() {

                        if(p.getInventory().contains(slowStick)) {

                            p.getInventory().remove(slowStick);

                        }

                    }

                },100L);
                break;
            case BLINDNESS:
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
                ChatUtilities.onePlayer(start + ChatColor.RED + "Blindness", p);
                ChatUtilities.onePlayer("You're blinded for 5 seconds", p);
                break;
            case JUMP_BOOST:
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 1));
                ChatUtilities.onePlayer(start + ChatColor.RED + "Jump Boost", p);
                ChatUtilities.onePlayer("You can jump higher for 5 seconds", p);
                break;
            case RESTORE_HEARTS:
                p.getInventory().addItem(restoreHearts);
                ChatUtilities.onePlayer(start + ChatColor.GREEN + "Restore Hearts", p);
                ChatUtilities.onePlayer("You can right click this item to restore 2.5 hearts", p);
                ChatUtilities.onePlayer("You only have this powerup for 30 seconds", p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(BlockChase.getPlugin(), new Runnable(){

                    public void run() {

                        if(p.getInventory().contains(restoreHearts)) {

                            p.getInventory().remove(restoreHearts);

                        }

                    }

                },600L);
                break;
            case INVISIBILITY:
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1));
                ChatUtilities.onePlayer(start + ChatColor.GREEN + "Invisibility", p);
                ChatUtilities.onePlayer("You are invisible for 5 seconds", p);
                break;
            default:
                break;

        }

    }

    public static PowerupType getRandomPowerup(){

        Random rand = new Random();
        int random = rand.nextInt(4);

        switch(random){

            case 0:
                return SLOW_STICK;
            case 1:
                return BLINDNESS;
            case 2:
                return JUMP_BOOST;
            case 3:
                return RESTORE_HEARTS;
            case 4:
                return INVISIBILITY;
            default:
                return null;

        }

    }

}
