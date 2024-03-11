package org.naosh.mdleaderboards.SubComms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.naosh.mdleaderboards.MDLeaderboards;

import java.util.List;

public class Spawn extends SubCommandMaster{
    @Override
    public String GetName()
    {
        return "spawn";
    }

    @Override
    public String GetDescpription()
    {
        return "Spawns a leaderboard";
    }

    @Override
    public String GetSyntax()
    {
        return "/MDLeaderboards Spawn";
    }

    @Override
    public void Perform(Player player, String[] args, MDLeaderboards pl)
    {
        Hologram temp = DHAPI.createHologram(Integer.toString(MDLeaderboards.id), player.getEyeLocation(), true, pl.generatePage());
        MDLeaderboards.holos.add(temp);
        MDLeaderboards.id++;
    }

    @Override
    public void Perform(String[] args, MDLeaderboards pl){}
}
