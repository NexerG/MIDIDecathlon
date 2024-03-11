package org.naosh.mdleaderboards.SubComms;

import org.bukkit.entity.Player;
import org.naosh.mdleaderboards.MDLeaderboards;

public abstract class SubCommandMaster {
    public abstract String GetName();
    public abstract String GetDescpription();
    public abstract String GetSyntax();
    public abstract  void Perform(Player player, String args[], MDLeaderboards pl);
    public abstract  void Perform(String args[], MDLeaderboards pl);
}
