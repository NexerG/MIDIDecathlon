package org.naosh.mdbuildoff.SubComms;

import org.naosh.mdbuildoff.MDBuildOff;
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
    public void Perform(Player player, String[] args, MDBuildOff par)
    {
        par.start();
    }
}
