package org.naosh.mdteams.SubComms;

import org.naosh.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class DeclineInvite extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "decline";
    }

    @Override
    public String GetDescpription()
    {
        return "declines an invite to a squad (if you have one)";
    }

    @Override
    public String GetSyntax()
    {
        return "/squad decline";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda)
    {
            Komanda.DeclineInvite(player);
    }
}
