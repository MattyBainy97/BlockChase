package com.blockchase;

import com.blockchase.handlers.game.BlockHandler;
import com.blockchase.handlers.game.PowerupHandler;
import com.blockchase.handlers.player.PlayerHandler;
import com.blockchase.listeners.entity.EntityDamage;
import com.blockchase.listeners.entity.EntityDamageByEntity;
import com.blockchase.listeners.entity.EntityRegen;
import com.blockchase.listeners.entity.player.*;
import com.blockchase.listeners.world.BlockBreak;
import com.blockchase.listeners.world.BlockPlace;
import com.blockchase.threads.GameTimer;
import com.blockchase.utils.ChatUtilities;
import com.blockchase.utils.LocationUtilities;
import com.blockchase.utils.ScoreboardUtilities;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockChase extends JavaPlugin {

    private static Plugin plugin;

    private int task = 0;

    @Override
    public void onEnable() {

        plugin = this;

        GameState.setState(GameState.IN_LOBBY);
        LocationUtilities.clearField();

        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        World w = Bukkit.getServer().getWorld("BlockChase");
        w.setAutoSave(false);
        w.setTime(0);
        w.setStorm(false);
        w.setWeatherDuration(9999999);
        w.setDifficulty(Difficulty.PEACEFUL);
        w.setGameRuleValue("doDaylightCycle", "false");
        for (Entity e : w.getEntities()) {

            e.remove();

        }

        PowerupHandler.initializePowerups();
        LocationUtilities.initializeSpawns();
        registerListeners();

    }

    @Override
    public void onDisable() {

        GameState.setState(GameState.IN_LOBBY);

        Bukkit.getScheduler().cancelTask(task);
        GameTimer.stopTimer();
        PlayerHandler.clearLists();

        for (Player p : Bukkit.getOnlinePlayers()) {

            p.kickPlayer("Rejoin.");

        }

        Bukkit.getScheduler().cancelTasks(plugin);

        plugin = null;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (commandLabel.equalsIgnoreCase("start")) {

                if(GameState.isState(GameState.IN_LOBBY)) {

                    GameState.setState(GameState.IN_GAME);

                    ChatUtilities.onePlayer("Test commenced", player);

                    for (Player p : Bukkit.getOnlinePlayers()) {

                        LocationUtilities.spawnPlayer(p);
                        PlayerHandler.gameStart(p);

                    }

                    for (Player p : Bukkit.getOnlinePlayers()) {

                        ChatUtilities.broadcast(ChatColor.GREEN + "THE GAME HAS BEGUN");
                        ChatUtilities.broadcast("Blocks are falling from the sky behind you");
                        ChatUtilities.broadcast("Don't get hit by any blocks or take any fall damage");
                        ChatUtilities.broadcast("If you take 4 hits from anything, you will die");
                        ScoreboardUtilities.initialisePlayerScoreboard(p);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.5F, 1.5F);

                    }

                    new Thread(new GameTimer()).start();
                    task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BlockHandler(), 20L, 10L);

                }else{

                    ChatUtilities.onePlayer("Game in progress", player);

                }

            }

            if (commandLabel.equalsIgnoreCase("nofall")) {

                Bukkit.getScheduler().cancelTask(task);
                GameState.setState(GameState.IN_LOBBY);

            }

            if (commandLabel.equalsIgnoreCase("clearfield")) {

                LocationUtilities.clearField();

            }

            if(commandLabel.equalsIgnoreCase("powerup")){

                LocationUtilities.spawnPowerup();

            }

        }

        return false;

    }

    public void registerListeners() {

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AsyncPlayerPreLogin(this), this);
        pm.registerEvents(new OnChat(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new EntityRegen(this), this);
        pm.registerEvents(new EntityDamage(this), this);
        pm.registerEvents(new EntityDamageByEntity(this), this);
        pm.registerEvents(new PlayerRespawn(this), this);
        pm.registerEvents(new PlayerDeath(this), this);
        pm.registerEvents(new PlayerLeave(this), this);
        pm.registerEvents(new PlayerInteract(this),this);
        pm.registerEvents(new PlayerDrop(this), this);
        pm.registerEvents(new PlayerSwitchHand(this), this);
        pm.registerEvents(new BlockBreak(this),this);
        pm.registerEvents(new BlockPlace(this), this);

    }

    public static Plugin getPlugin(){

        return plugin;

    }

}
