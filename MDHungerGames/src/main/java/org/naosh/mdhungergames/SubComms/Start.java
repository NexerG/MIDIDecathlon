package org.naosh.mdhungergames.SubComms;

import org.bukkit.entity.Player;
import org.naosh.mdhungergames.MDHungerGames;

public class Start extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "start";
    }

    @Override
    public String GetDescpription()
    {
        return "starts the Hunger Games";
    }

    @Override
    public String GetSyntax()
    {
        return "/hungergames start";
    }

    @Override
    public void Perform(Player player, String[] args, MDHungerGames pl)
    {
        pl.start();
    }
}
