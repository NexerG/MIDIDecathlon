package org.naosh.decathlonmanager.SubComms;

import org.naosh.decathlonmanager.DecathlonManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class abort extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "abort";
    }

    @Override
    public String GetDescpription()
    {
        return "aborts the next game";
    }

    @Override
    public String GetSyntax()
    {
        return "/manager abort";
    }

    @Override
    public void Perform(Player player, String[] args, DecathlonManager pl)
    {
        pl.flipAbort();
        player.sendMessage(ChatColor.BLUE+"abort is now set to " +ChatColor.RED+pl.getAbort());
    }
}
