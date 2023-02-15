package mdteams.mdteams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.K;

import javax.print.attribute.standard.Finishings;
import java.util.List;

public class MasterTeamClass implements CommandExecutor {
    private static List<Komanda> Komandos;

    public List<Komanda> GetKomandos()
    {
        return Komandos;
    }
    public static void CreateTeam(String name, Player creator)
    {
        //TODO: add a team to the json with its players
        Komanda koma=new Komanda(name, creator.getName());
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

    public void InvitePlayer()
    {
        //TODO: invite player functionality
    }

    public void KickPlayer(String toKick, Komanda koma) {
        //TODO: kick a player from the team functionality
        koma.Players.remove(toKick);
    }
}
