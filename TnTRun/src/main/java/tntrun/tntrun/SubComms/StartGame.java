package tntrun.tntrun.SubComms;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class StartGame extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "start";
    }

    @Override
    public String GetDescpription()
    {
        return "starts the game with set amount of rounds";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr start";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        if(pr.getMatches()>0)
            pr.startgame();
        else player.sendMessage(ChatColor.RED+"invalid match count. currently set to: "+pr.getMatches()+". has to be >0");
        player.sendMessage("issued command");
    }
}
