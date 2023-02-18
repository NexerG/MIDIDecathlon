package mdteams.mdteams.SubComms;

import mdteams.mdteams.Komanda;
import mdteams.mdteams.MasterTeamClass;
import org.bukkit.ChatColor;
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
        return "sets the color of the team (in chat)";
    }

    @Override
    public String GetSyntax()
    {
        return "/komanda color <spalva>";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        Komanda.SetColor(player, (args[1]));
    }
}
