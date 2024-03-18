package org.naosh.mdleaderboards;

import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.naosh.mdteams.MDTeams;

import java.util.ArrayList;
import java.util.List;

import static org.naosh.mdleaderboards.utils.fixFontSize.fixFontSize;

public final class MDLeaderboards extends JavaPlugin {


    public MDTeams komandos;
    public static ArrayList<Hologram> holos = new ArrayList<>();
    public static int id = 0;

    @Override
    public void onEnable() {
        this.getCommand("leaderboards").setExecutor(new CommandManager(this));
        this.getCommand("leaderboards").setTabCompleter(new LeaderboardsTabsCompleter());
        komandos = (MDTeams) Bukkit.getServer().getPluginManager().getPlugin("MDTeams");

    }

    public List<String> generatePage(){
        List<String> lines = new ArrayList<String>();
        lines.add(ChatColor.BOLD + "___________KOMANDŲ SĄRAŠAS___________");
        for(int i = 0; i < komandos.getMasterTeam().GetKomandos().size(); i++){
            lines.add(komandos.getMasterTeam().GetKomandos().get(i).GetChatColor() + fixFontSize(komandos.getMasterTeam().GetKomandos().get(i).TName, 25) + "Points:" +fixFontSize(String.valueOf(komandos.getMasterTeam().GetKomandos().get(i).getPoints()), 1));
        }
        return lines;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
