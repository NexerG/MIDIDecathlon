package org.naosh.decathlonmanager.SubComms;

import org.naosh.decathlonmanager.DecathlonManager;
import org.bukkit.entity.Player;

public class JoinSpectators extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "spectator";
    }

    @Override
    public String GetDescpription()
    {
        return "joins the spectators";
    }

    @Override
    public String GetSyntax()
    {
        return "/manager spectator";
    }

    @Override
    public void Perform(Player player, String[] strings, DecathlonManager pl)
    {
        if(!pl.spectators.contains(player))
            pl.spectators.add(player);
    }
}
