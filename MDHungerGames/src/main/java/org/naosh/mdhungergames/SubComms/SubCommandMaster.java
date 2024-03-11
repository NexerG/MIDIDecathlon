package org.naosh.mdhungergames.SubComms;

import org.bukkit.entity.Player;
import org.naosh.mdhungergames.MDHungerGames;

public abstract class SubCommandMaster {
    public abstract String GetName();
    public abstract String GetDescpription();
    public abstract String GetSyntax();
    public abstract  void Perform(Player player, String args[], MDHungerGames pl);
}
