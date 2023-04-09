package buildoff.buildoff.SubComms;

import buildoff.buildoff.BuildOff;
import org.bukkit.entity.Player;

public class start extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "start";
    }

    @Override
    public String GetDescpription()
    {
        return "Starts the BuildOff competition";
    }

    @Override
    public String GetSyntax()
    {
        return "/buildoff start";
    }

    @Override
    public void Perform(Player player, String[] args, BuildOff par)
    {
        par.start();
    }
}
