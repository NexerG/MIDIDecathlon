package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.Komanda;
import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class InvitePlayer extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "invite";
    }

    @Override
    public String GetDescpription()
    {
        return "invites a player to the squad";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad invite <player>";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        if (args.length > 0 && args.length < 3)
        {
            List<Player> zaidejai = (List<Player>) Bukkit.getOnlinePlayers();
            int x;
            if (!player.getName().equalsIgnoreCase(args[1]))
            {
                for (int i = 0; i < zaidejai.size(); i++)
                {
                    if (zaidejai.get(i).getName().equalsIgnoreCase(args[1]))
                    {
                        x = i;
                        break;
                    } else if (i == zaidejai.size() - 1)
                    {
                        player.sendMessage(ChatColor.RED + "This player is offline");
                    }
                }
                boolean isInTeam=false;
                for(int i=0;i<Komanda.GetKomandos().size();i++)
                {
                    for(int j=0;j<Komanda.GetKomandos().get(i).Players.size();j++)
                    {
                        if(Komanda.GetKomandos().get(i).Players.get(j).equalsIgnoreCase(args[1]))
                        {
                            isInTeam=true;
                        }
                    }
                }
                if(!isInTeam)
                {
                    Komanda T = null;
                    for (int i = 0; i < Komanda.GetKomandos().size(); i++)
                    {
                        if (Komanda.GetKomandos().get(i).TCreator.equalsIgnoreCase(player.getName()))
                        {
                            T = Komanda.GetKomandos().get(i);
                            break;
                        }
                    }
                    Komanda.InvitePlayer(args[1], T);
                }
                else {
                    player.sendMessage(ChatColor.RED + "Player is already in a team");
                }
            } else
                player.sendMessage(ChatColor.RED + "You cannot invite yourself to a team");
        }
    }
}
