package mdteams.mdteams;

import mdteams.mdteams.SubComms.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    MasterTeamClass koma;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {
        return subcomms;
    }

    public CommandManager(MasterTeamClass komanda)
    {
        koma = komanda;
        //cia dedamos komandu klases
        subcomms.add(new CreateTeam());
        subcomms.add(new KickMember());
        subcomms.add(new ListTeams());
        subcomms.add(new DeleteTeam());
        subcomms.add(new InvitePlayer());
        subcomms.add(new AcceptInvite());
        subcomms.add(new DeclineInvite());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player P = (Player) sender;
            if (args.length > 0)
            {
                for (int i = 0; i < GetSubComms().size(); i++)
                {
                    if (args[0].equalsIgnoreCase(GetSubComms().get(i).GetName()))
                    {
                        GetSubComms().get(i).Perform(P, args, koma);
                    }
                }
            }
        }
        return false;
    }
}
