package org.naosh.mdparkourrace.SubComms;

import org.bukkit.entity.Player;
import org.naosh.mdparkourrace.MDParkourRace;

public class EndGame extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "end";
    }

    @Override
    public String GetDescpription()
    {
        return "ends the parkour race";
    }

    @Override
    public String GetSyntax()
    {
        return "/parkour end";
    }

    @Override
    public void Perform(Player player, String[] args, MDParkourRace pr)
    {
        pr.end();
    }
}
