package buildoff.buildoff.SubComms;

import buildoff.buildoff.BuildOff;
import org.bukkit.entity.Player;

public class end extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "end";
    }

    @Override
    public String GetDescpription()
    {
        return "ends the buildoff competition";
    }

    @Override
    public String GetSyntax()
    {
        return "/buildoff end";
    }

    @Override
    public void Perform(Player player, String[] args, BuildOff par)
    {
        par.End();
    }
}
