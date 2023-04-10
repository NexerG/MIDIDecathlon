package mduhc.mduhc.SubComms;

import mduhc.mduhc.MDUHC;
import org.bukkit.entity.Player;

public class GenerateWorlds extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "generate";
    }

    @Override
    public String GetDescpription()
    {
        return "generates the specified amount of worlds for the UHC";
    }

    @Override
    public String GetSyntax()
    {
        return "/uhc generate [int]";
    }

    @Override
    public void Perform(Player player, String[] args, MDUHC pl)
    {
        pl.generate(Integer.parseInt(args[1]));
    }
}
