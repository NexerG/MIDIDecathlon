package mdteams.mdteams.SubComms;

import mdteams.mdteams.Komanda;
import mdteams.mdteams.MasterTeamClass;
import org.bukkit.ChatColor;
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
        return "Lists all the teams on the server";
    }

    @Override
    public String GetSyntax()
    {
        return "/komanda list";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        Komanda.ListTeams(player);
    }
}
