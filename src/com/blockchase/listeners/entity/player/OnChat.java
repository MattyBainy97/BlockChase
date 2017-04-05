package com.blockchase.listeners.entity.player;

import com.blockchase.BlockChase;
import com.blockchase.listeners.BCListener;
import com.blockchase.utils.ChatUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat extends BCListener {
    
    public OnChat(BlockChase pl){
        
        super(pl);
        
    }
    
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent pc) {

        pc.setCancelled(true);
        ChatUtilities.chat(pc.getMessage(), pc.getPlayer());
        
    }
    
}
