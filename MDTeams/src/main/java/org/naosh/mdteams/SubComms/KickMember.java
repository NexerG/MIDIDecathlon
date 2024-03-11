package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KickMember extends SubCommandMaster
{
    @Override
    public String GetName() {
        return "kick";
    }

    @Override
    public String GetDescpription() {
        return "kicks a member from the squad";
    }

    @Override
    public String GetSyntax() {
        return "/squad kick <name>";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komandos)
    {
        if (player.getName() != args[2])
            for (int i = 0; i < Komandos.GetKomandos().size(); i++)
            {
                if (Komandos.GetKomandos().get(i).TCreator != args[2])
                {
                    if(Komandos.GetKomandos().get(i).Players.contains(args[2]))
                    {
                        Komandos.KickPlayer(args[2], Komandos.GetKomandos().get(i));
                    }
                    else player.sendMessage(ChatColor.RED + "This player is either not in your squad or does not exist (make sure to write the exact name with capitalization)");
                } else
                {
                    player.sendMessage(ChatColor.RED + "You cannot kick yourself");
                }
            }
    }
}
