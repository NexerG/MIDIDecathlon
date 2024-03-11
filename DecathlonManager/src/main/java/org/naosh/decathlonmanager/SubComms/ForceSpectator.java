package org.naosh.decathlonmanager.SubComms;

import org.naosh.decathlonmanager.DecathlonManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ForceSpectator extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "forcespec";
    }

    @Override
    public String GetDescpription()
    {
        return "puts a player into a spectator team";
    }

    @Override
    public String GetSyntax()
    {
        return "/manager forcespec";
    }

    @Override
    public void Perform(Player player, String[] args, DecathlonManager pl)
    {
        if(pl.spectators.contains(Bukkit.getPlayer(args[args.length-1])))
            pl.spectators.add(Bukkit.getPlayer(args[args.length-1]));
    }
}
