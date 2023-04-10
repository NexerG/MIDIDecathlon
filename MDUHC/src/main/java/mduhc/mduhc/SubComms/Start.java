package mduhc.mduhc.SubComms;

import mduhc.mduhc.MDUHC;
import org.bukkit.entity.Player;

public class Start extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "start";
    }

    @Override
    public String GetDescpription()
    {
        return "starts the UHC";
    }

    @Override
    public String GetSyntax()
    {
        return "/uhc start";
    }

    @Override
    public void Perform(Player player, String[] args, MDUHC pl)
    {
        pl.start();
    }
}
