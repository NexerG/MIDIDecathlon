package mdteams.mdteams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterTeamClass
{
    private static List<Komanda> Komandos = new ArrayList<>();
    private Map<String,String> PlayerTeaminvite=new HashMap<>();

    public static void setKomandos(List<Komanda> komandos)
    {
        Komandos = komandos;
    }

    public List<Komanda> GetKomandos()
    {
        return Komandos;
    }

    public static void CreateTeam(String name, Player creator)
    {
        //TODO: add a team to the json with its players
        //Bukkit.broadcastMessage(creator.getName());
        Komanda koma = new Komanda(name, creator.getName());
        Komandos.add(koma);
    }

    public void RenameTeam()
    {
        //TODO: add renaming functionality
    }

    public void DeleteTeam(Komanda koma)
    {
        //removing a team
        Komandos.remove(koma);
    }

    public void InvitePlayer(String arg, Komanda T)
    {
        //TODO: invite player functionality
        List<Player> players= (List<Player>) Bukkit.getOnlinePlayers();
        for(int i=0;i< players.size();i++)
        {
            if(players.get(i).getName().equalsIgnoreCase(arg))
            {
                Bukkit.getPlayer(players.get(i).getName()).sendMessage(ChatColor.GREEN+"Jūs kviečiamas į komandą: " + T.TName);
            }
        }
        PlayerTeaminvite.put(arg,T.TName);
    }

    public void AcceptInvite()
    {
        //TODO: accept invites
    }
    public void DeclineInvite()
    {
        //TODO: decline invites
    }

    public void KickPlayer(String toKick, Komanda koma)
    {
        //TODO: kick a player from the team functionality
        koma.Players.remove(toKick);
    }

    public void ListTeams(Player player)
    {
        for(int i=0;i<GetKomandos().size();i++)
        {
            player.sendMessage(GetKomandos().get(i).TName);
            for(int j=0;j<GetKomandos().get(i).Players.size();j++)
            {
                player.sendMessage("   "+GetKomandos().get(i).Players.get(j));
            }
        }

    }

    public void SetColor()
    {

    }

}
