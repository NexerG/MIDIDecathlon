package decathlonmanager.decathlonmanager.SubComms;

import decathlonmanager.decathlonmanager.DecathlonManager;
import org.bukkit.entity.Player;

public class Initiate extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "initiate";
    }

    @Override
    public String GetDescpription()
    {
        return "initiates the decathlon";
    }

    @Override
    public String GetSyntax()
    {
        return "/manager initiate";
    }

    @Override
    public void Perform(Player player, String[] args, DecathlonManager pl)
    {
        //TODO: DO THE INITIATION
    }
}
