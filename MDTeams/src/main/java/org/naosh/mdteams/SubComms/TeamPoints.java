package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamPoints extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "points";
    }

    @Override
    public String GetDescpription()
    {
        return "shows how many points your team has";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad points";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        for(int i=0;i<Komanda.GetKomandos().size();i++)
        {
            for(int j=0;j<Komanda.GetKomandos().get(i).Players.size();j++)
            {
                if(player.getName().equalsIgnoreCase(Komanda.GetKomandos().get(i).Players.get(j)))
                {
                    player.sendMessage(ChatColor.GREEN + "Points: "+ Komanda.GetKomandos().get(i).getPoints());
                    break;
                }
            }
        }
    }
}
