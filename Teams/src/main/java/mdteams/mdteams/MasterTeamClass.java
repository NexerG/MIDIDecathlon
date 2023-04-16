package mdteams.mdteams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterTeamClass implements Listener
{
    ActivePlayers AP=new ActivePlayers();
    private static List<ChatColor> spalvos=new ArrayList<>();
    private static List<Komanda> Komandos = new ArrayList<>();
    private Map<String,Komanda> PlayerTeaminvite=new HashMap<>();

    public MasterTeamClass()
    {
        spalvos.add(ChatColor.RED);
        spalvos.add(ChatColor.WHITE);
        spalvos.add(ChatColor.GREEN);
        spalvos.add(ChatColor.BLUE);
        spalvos.add(ChatColor.DARK_BLUE);
        spalvos.add(ChatColor.LIGHT_PURPLE);
        spalvos.add(ChatColor.DARK_PURPLE);
        spalvos.add(ChatColor.GOLD);
        spalvos.add(ChatColor.AQUA);
        spalvos.add(ChatColor.DARK_AQUA);
        spalvos.add(ChatColor.DARK_GRAY);
        spalvos.add(ChatColor.DARK_GREEN);
        spalvos.add(ChatColor.DARK_RED);
        spalvos.add(ChatColor.GRAY);
        spalvos.add(ChatColor.YELLOW);
    }

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
        Komanda koma = new Komanda(name, creator.getName(),spalvos.get(((int)(Math.random()*1000)) %15));
        Komandos.add(koma);
    }

    public void RenameTeam()
    {
        //TODO: add renaming functionality
    }

    public void DeleteTeam(Komanda koma)
    {
        //removing a team
        for(int i=0;i<koma.Players.size();i++)
        {
            AP.LeftTeam(Bukkit.getPlayer(koma.Players.get(i)));
        }
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
                Bukkit.getPlayer(players.get(i).getName()).sendMessage(ChatColor.GREEN+"You have been invited to a team: " + T.TName);
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
            player.sendMessage(ChatColor.GREEN + "You have joined the team");
            AP.JoinedTeam(player);
        }
        else player.sendMessage(ChatColor.RED+"You have no invites");
    }
    public void DeclineInvite(Player player)
    {
        if(PlayerTeaminvite.containsKey(player.getName()))
        {
            PlayerTeaminvite.remove(player.getName());
            player.sendMessage(ChatColor.GREEN + "You have declined the invite");
        }
        else player.sendMessage(ChatColor.RED+"You have no invites");
    }

    public void KickPlayer(String toKick, Komanda koma)
    {
        //TODO: kick a player from the team functionality
        koma.Players.remove(toKick);
        AP.LeftTeam(Bukkit.getPlayer(toKick));
    }

    public void ListTeams(Player player)
    {
        for(int i=0;i<GetKomandos().size();i++)
        {
            player.sendMessage(GetKomandos().get(i).GetChatColor()+ GetKomandos().get(i).TName);
            for(int j=0;j<GetKomandos().get(i).Players.size();j++)
            {
                player.sendMessage("   "+ GetKomandos().get(i).GetChatColor() + GetKomandos().get(i).Players.get(j));
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
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        AP.Joined(e.getPlayer());
    }
}
