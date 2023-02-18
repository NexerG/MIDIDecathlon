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
    private Map<String,Komanda> PlayerTeaminvite=new HashMap<>();

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

    public void InvitePlayer(String invitee, Komanda T)
    {
        //TODO: invite player functionality
        List<Player> players= (List<Player>) Bukkit.getOnlinePlayers();
        for(int i=0;i< players.size();i++)
        {
            if(players.get(i).getName().equalsIgnoreCase(invitee))
            {
                Bukkit.getPlayer(players.get(i).getName()).sendMessage(ChatColor.GREEN+"Jūs kviečiamas į komandą: " + T.TName);
            }
        }
        if(!PlayerTeaminvite.containsKey(invitee))
            PlayerTeaminvite.put(invitee,T);
        //TODO: else padaryt kad rodytu jog zmogus jau invited
    }

    public void AcceptInvite(Player player)
    {
        if(PlayerTeaminvite.containsKey(player.getName()))
        {
            PlayerTeaminvite.get(player.getName()).Players.add(player.getName());
            player.sendMessage(ChatColor.GREEN + "Jūs prisijungėte į komandą");
        }
        else player.sendMessage(ChatColor.RED+"Jūs neturite kvietimų į komandą");
    }
    public void DeclineInvite(Player player)
    {
        if(PlayerTeaminvite.containsKey(player.getName()))
        {
            PlayerTeaminvite.remove(player.getName());
            player.sendMessage(ChatColor.GREEN + "Jūs atmetėte kvietimą");
        }
        else player.sendMessage(ChatColor.RED+"Jūs neturite kvietimų į komandą");
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

    public void SetColor(Player owner, String arg)
    {
        for(int i=0;i<Komandos.size();i++)
        {
            if(owner.getName().equalsIgnoreCase(Komandos.get(i).TCreator))
            {
                ChatColor dummy = ChatColor.valueOf(arg);
                Komandos.get(i).SetChatColor(dummy);
                break;
            }
        }
    }

}
