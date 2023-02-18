package mdteams.mdteams.SubComms;

import mdteams.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class AcceptInvite extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "accept";
    }

    @Override
    public String GetDescpription()
    {
        return "accepts an invite to a team";
    }

    @Override
    public String GetSyntax()
    {
        return "/komanda invite vardas";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
        Komanda.AcceptInvite(player);
    }
}
