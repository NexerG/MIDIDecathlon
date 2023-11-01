package grex.mdtgttosawaf.SubComms;

import grex.mdtgttosawaf.MDTGTTOSAWAF;
import org.bukkit.entity.Player;

public class RoundCount extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "round";
    }

    @Override
    public String GetDescpription()
    {
        return "changes the amount of rounds of the Chicken gamemode";
    }

    @Override
    public String GetSyntax()
    {
        return "/chicken rounds <number>";
    }

    @Override
    public void Perform(Player player, String[] args, MDTGTTOSAWAF par)
    {
        par.setRounds(Integer.parseInt(args[1]));
    }
}
