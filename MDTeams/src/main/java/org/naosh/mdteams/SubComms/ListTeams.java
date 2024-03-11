package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class ListTeams extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "list";
    }

    @Override
    public String GetDescpription()
    {
        return "Lists all the squads on the server";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad list";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        Komanda.ListTeams(player);
    }
}
