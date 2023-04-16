package mdteams.mdteams.SubComms;

import mdteams.mdteams.MasterTeamClass;
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
        return "deletes your squad";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad delete";
    }

    @Override
    public void Perform(Player creator, String[] args, MasterTeamClass Komanda)
    {
        for (int i = 0; i < Komanda.GetKomandos().size(); i++)
        {
            if (Komanda.GetKomandos().get(i).TCreator.equalsIgnoreCase(creator.getName()))
            {
                Komanda.DeleteTeam(Komanda.GetKomandos().get(i));
                creator.sendMessage(ChatColor.GREEN+"Squad deleted");
            } else
            {
                //you are not in a team
                creator.sendMessage(ChatColor.RED +"You don't own this Squad");
            }
        }
    }
}
