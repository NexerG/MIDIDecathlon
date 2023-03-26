package tntrun.tntrun.SubComms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class EndTNTRun extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "endTNTRun";
    }

    @Override
    public String GetDescpription()
    {
        return "ends the gamemode";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr endTNTRun";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        //TODO: teleport everyone to the lobby
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp to lobby");
    }
}
