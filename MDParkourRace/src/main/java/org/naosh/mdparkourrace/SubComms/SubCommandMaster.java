package org.naosh.mdparkourrace.SubComms;

import org.bukkit.entity.Player;
import org.naosh.mdparkourrace.MDParkourRace;

public abstract class SubCommandMaster
{
    public abstract String GetName();
    public abstract String GetDescpription();
    public abstract String GetSyntax();
    public abstract  void Perform(Player player, String[] args, MDParkourRace pr);
}
