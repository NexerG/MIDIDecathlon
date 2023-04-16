package mdteams.mdteams.SubComms;

import mdteams.mdteams.MasterTeamClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateTeam extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "create";
    }

    @Override
    public String GetDescpription()
    {
        return "creates a squad";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad create <name>";
    }

    @Override
    public void Perform(Player creator, String[] args, MasterTeamClass koma)
    {
        Boolean isInATeam = false;
        if (args.length > 1)
        {
            for (int i = 0; i < koma.GetKomandos().size(); i++)
            {
                for (int j = 0; j < koma.GetKomandos().get(i).Players.size(); j++)
                {
                    if (koma.GetKomandos().get(i).Players.get(j).equalsIgnoreCase(creator.getName()))
                    {
                        isInATeam = true;
                        creator.sendMessage(ChatColor.RED+"You already have a squad");
                    }
                }
            }
            if (!isInATeam)
            {
                koma.CreateTeam(args[1], creator);
                creator.sendMessage(ChatColor.GREEN+"Created a squad named: " + args[1]);
            }
        } else
            creator.sendMessage( ChatColor.RED +"Didn't specify the name of the squad");
    }
}
