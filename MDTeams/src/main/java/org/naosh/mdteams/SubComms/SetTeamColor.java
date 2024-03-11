package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class SetTeamColor extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "color";
    }

    @Override
    public String GetDescpription()
    {
        return "sets the color of the squad (in chat)";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad color <color>";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        Komanda.SetColor(player, (args[1]));
    }
}
