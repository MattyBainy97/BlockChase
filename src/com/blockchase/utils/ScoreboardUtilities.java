package com.blockchase.utils;

import com.blockchase.handlers.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardUtilities {

    private static HashMap<UUID, Scoreboard> scoreboardList = new HashMap<UUID, Scoreboard>();

    public static void initialisePlayerScoreboard(Player p) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "     BlockChase     ");

        Score empty = objective.getScore("");
        empty.setScore(9);
        Score name = objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + p.getName());
        name.setScore(8);
        Score empty2 = objective.getScore(" ");
        empty2.setScore(7);
        Score timerTitle = objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Timer");
        timerTitle.setScore(6);
        Score timer = objective.getScore(ChatColor.WHITE + "00:00");
        timer.setScore(5);
        Score empty3 = objective.getScore("  ");
        empty3.setScore(4);
        Score remainTitle = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Players Remaining");
        remainTitle.setScore(3);
        Score remain = objective.getScore(ChatColor.WHITE + "" + PlayerHandler.getAliveList().size());
        remain.setScore(2);
        Score empty4 = objective.getScore( "   ");
        empty4.setScore(1);

        scoreboardList.put(p.getUniqueId(), scoreboard);
        p.setScoreboard(scoreboard);

    }

    public static void setTime(Player p, int newTime){

        int currentTime = newTime - 1;

        String currentTimeString = String.format("%02d:%02d", currentTime / 60, currentTime % 60);

        String newTimeString = String.format("%02d:%02d", newTime / 60, newTime % 60);

        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.WHITE + currentTimeString);
        Score timer = objective.getScore(ChatColor.WHITE + newTimeString);
        timer.setScore(5);

    }

    public static void setAlivePlayers(Player p){

        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.WHITE + "" + (PlayerHandler.getAliveList().size() + 1));
        Score timer = objective.getScore(ChatColor.WHITE + "" + PlayerHandler.getAliveList().size());
        timer.setScore(2);

    }

}
