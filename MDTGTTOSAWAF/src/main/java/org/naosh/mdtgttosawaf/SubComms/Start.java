package org.naosh.mdtgttosawaf.SubComms;

import org.naosh.mdtgttosawaf.MDTGTTOSAWAF;
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
        return "starts the competition";
    }

    @Override
    public String GetSyntax()
    {
        return "/chicken start";
    }

    @Override
    public void Perform(Player player, String[] args, MDTGTTOSAWAF par)
    {
        par.start();
    }
}
