package grex.mdtgttosawaf.SubComms;

import grex.mdtgttosawaf.MDTGTTOSAWAF;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;

public class End extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "end";
    }

    @Override
    public String GetDescpription()
    {
        return "ends the competition";
    }

    @Override
    public String GetSyntax()
    {
        return "/chicken end";
    }

    @Override
    public void Perform(Player player, String[] args, MDTGTTOSAWAF par)
    {
        try
        {
            par.end();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}