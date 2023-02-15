package mdteams.mdteams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    public static MDTeams plugin;
    public ChatListener(MDTeams instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void OnPlayerChat(AsyncPlayerChatEvent event)
    {
        //TODO: set player name color and add their team name next to their nickname when chatting
        Player player= event.getPlayer();
        String message=event.getMessage();

        event.setFormat(ChatColor.BLUE + player.getDisplayName()+"§8>>" + ChatColor.WHITE + message);
    }

}
