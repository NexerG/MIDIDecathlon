package tntrun.tntrun.SubComms;

import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class SetMatches extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "setmatches";
    }

    @Override
    public String GetDescpription()
    {
        return "sets the amount of rounds played";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr setmatches <int(round count)>";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        pr.setMatches(Integer.parseInt(args[0]));
    }
}
