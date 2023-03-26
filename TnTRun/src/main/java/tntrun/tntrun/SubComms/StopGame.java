package tntrun.tntrun.SubComms;

import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class StopGame extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "stop";
    }

    @Override
    public String GetDescpription()
    {
        return "stops the current match";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr stop";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        pr.stopgame();
    }
}
