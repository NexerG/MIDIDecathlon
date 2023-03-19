package mdteams.mdteams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatListener implements Listener
{
    public static MDTeams plugin;

    public ChatListener(MDTeams instance)
    {
        plugin = instance;
    }

    MasterTeamClass master = null;
    Map<String, Komanda> PlayerKomandaMap = new HashMap<>();

    public ChatListener(MasterTeamClass ms)
    {
        master = ms;
        for (int i = 0; i < ms.GetKomandos().size(); i++)
        {
            for (int j = 0; j < ms.GetKomandos().get(i).Players.size(); j++)
            {
                PlayerKomandaMap.put(ms.GetKomandos().get(i).Players.get(j), ms.GetKomandos().get(i));
            }
        }
    }

    @EventHandler
    public void OnPlayerChat(AsyncPlayerChatEvent event)
    {
        //TODO: set player name color and add their team name next to their nickname when chatting
        Player player = event.getPlayer();
        String message = event.getMessage();

        ChatColor spalva = PlayerKomandaMap.get(player.getName()).GetChatColor();
        Komanda km = PlayerKomandaMap.get(player.getName());
        event.setFormat(spalva + "<" + km.TName + "> " + ChatColor.WHITE + player.getDisplayName() + "§8>> " + message);
    }
}
