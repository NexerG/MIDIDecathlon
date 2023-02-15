package mdteams.mdteams.SubComms;

import mdteams.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class CreateTeam extends SubCommandMaster
{
    @Override
    public String GetName() {
        return "Create team";
    }

    @Override
    public String GetDescpription() {
        return "No description";
    }

    @Override
    public String GetSyntax() {
        return "/Team create <team name>";
    }

    @Override
    public void Perform(Player creator, String[] args, MasterTeamClass koma)
    {
        if(args.length>1)
            koma.CreateTeam(args[1], creator);
    }
}
