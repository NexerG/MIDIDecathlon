package org.naosh.mdbuildoff.SubComms;
import org.naosh.mdbuildoff.MDBuildOff;
import org.bukkit.entity.Player;

public abstract class SubCommandMaster
{
    public abstract String GetName();
    public abstract String GetDescpription();
    public abstract String GetSyntax();
    public abstract  void Perform(Player player, String[] args, MDBuildOff par);
}
