package org.naosh.mdbuildoff.SubComms;

import org.naosh.mdbuildoff.MDBuildOff;
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
    public void Perform(Player player, String[] args, MDBuildOff par)
    {
        par.End();
    }
}
