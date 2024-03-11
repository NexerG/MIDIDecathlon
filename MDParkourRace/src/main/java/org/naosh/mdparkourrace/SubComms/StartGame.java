package org.naosh.mdparkourrace.SubComms;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.naosh.mdparkourrace.MDParkourRace;

public class StartGame extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "start";
    }

    @Override
    public String GetDescpription()
    {
        return "starts the game with set amount of rounds";
    }

    @Override
    public String GetSyntax()
    {
        return "/parkour start";
    }

    @Override
    public void Perform(Player player, String[] args, MDParkourRace pr)
    {
        pr.startgame();
    }
}
