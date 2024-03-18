package org.naosh.mdparkourrace.SubComms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.naosh.mdparkourrace.MDParkourRace;
import org.naosh.mdteams.MDTeams;

public class GetPositions extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "positions";
    }

    @Override
    public String GetDescpription()
    {
        return "returns the positions of the race";
    }

    @Override
    public String GetSyntax()
    {
        return "/parkour positions";
    }

    @Override
    public void Perform(Player player, String[] args, MDParkourRace pr)
    {
        MDTeams tm = (MDTeams) Bukkit.getServer().getPluginManager().getPlugin("MDTeams");
        for(int i = 0; i<tm.getMasterTeam().GetKomandos().size();i++)
        {
            Bukkit.getLogger().info(tm.getMasterTeam().GetKomandos().get(i).parkourCheckpoints.get("NexerG").toString());
        }
    }
}
