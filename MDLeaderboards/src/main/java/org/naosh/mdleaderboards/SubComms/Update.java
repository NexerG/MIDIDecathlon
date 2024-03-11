package org.naosh.mdleaderboards.SubComms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.naosh.mdleaderboards.MDLeaderboards;

public class Update extends SubCommandMaster{
    @Override
    public String GetName()
    {
        return "update";
    }

    @Override
    public String GetDescpription()
    {
        return "Updates all leaderboard data";
    }

    @Override
    public String GetSyntax()
    {
        return "/leaderboards update";
    }

    @Override
    public void Perform(Player player, String[] args, MDLeaderboards pl)
    {
        for(int i = 0; i< MDLeaderboards.holos.size(); i++){
            DHAPI.setHologramLines(MDLeaderboards.holos.get(i), 0, pl.generatePage());
        }
    }
    @Override
    public void Perform(String[] args, MDLeaderboards pl)
    {
        for(int i = 0; i< MDLeaderboards.holos.size(); i++){
            DHAPI.setHologramLines(MDLeaderboards.holos.get(i), 0, pl.generatePage());
        }
    }
}
