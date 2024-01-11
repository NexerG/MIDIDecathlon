package mdteams.mdteams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Komanda
{
    public String TName;
    public String TCreator;
    public List<String> Players = new ArrayList<>();
    public List<String> Invites = new ArrayList<>();

    private int Kills=0;
    private int Points=0;
    private ChatColor spalva;

    public Komanda(String Pavadinimas, String Creator, ChatColor clr)
    {
        this.TName = Pavadinimas;
        this.TCreator = Creator;
        this.Players.add(Creator);
        spalva = clr;
    }
    public void addPoints(int pts)
    {
        Points=Points+pts;
    }
    public int getPoints()
    {
        return Points;
    }

    public void AddKill()
    {
        ++Kills;
    }
    public int GetKills()
    {
        return Kills;
    }

    public boolean IsInTeam(Player p)
    {
        for(int i=0;i<Players.size();i++)
        {
            if(p.getName().equalsIgnoreCase(Players.get(i)))
                return true;
        }
        return false;
    }

    public void SetChatColor(ChatColor sp)
    {
        spalva = sp;
    }

    public ChatColor GetChatColor()
    {
        return spalva;
    }
}
