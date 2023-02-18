package mdteams.mdteams.SubComms;

import mdteams.mdteams.Komanda;
import mdteams.mdteams.MasterTeamClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DeleteTeam extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "delete";
    }

    @Override
    public String GetDescpription()
    {
        return "deletes your team";
    }

    @Override
    public String GetSyntax()
    {
        return "/komanda delete";
    }

    @Override
    public void Perform(Player creator, String[] args, MasterTeamClass Komanda)
    {
        for (int i = 0; i < Komanda.GetKomandos().size(); i++)
        {
            if (Komanda.GetKomandos().get(i).TCreator.equalsIgnoreCase(creator.getName()))
            {
                Komanda.DeleteTeam(Komanda.GetKomandos().get(i));
                creator.sendMessage(ChatColor.GREEN+"Komanda ištrinta");
            } else
            {
                //you are not in a team
                creator.sendMessage(ChatColor.RED +"Tu nesi komandos savininkas");
            }
        }
    }
}
