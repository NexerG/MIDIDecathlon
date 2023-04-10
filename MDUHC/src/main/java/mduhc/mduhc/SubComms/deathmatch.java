package mduhc.mduhc.SubComms;

import mduhc.mduhc.MDUHC;
import org.bukkit.entity.Player;

public class deathmatch extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "deathmatch";
    }

    @Override
    public String GetDescpription()
    {
        return "starts the deathmatch for UHC";
    }

    @Override
    public String GetSyntax()
    {
        return "/uhc deathmatch";
    }

    @Override
    public void Perform(Player player, String[] args, MDUHC pl)
    {
        pl.DeathMatch();
    }
}
