package tntrun.tntrun.SubComms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class EndTNTRun extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "endTNTRun";
    }

    @Override
    public String GetDescpription()
    {
        return "ends the gamemode";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr endTNTRun";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        pr.Over();
    }
}
