package decathlonmanager.decathlonmanager;

import decathlonmanager.decathlonmanager.SubComms.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    DecathlonManager pr;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {

        return subcomms;
    }

    public CommandManager(DecathlonManager parent)
    {
        pr=parent;
        //cia dedamos komandu klases
        subcomms.add(new Initiate());
        subcomms.add(new abort());
        subcomms.add(new GenTNT());
        subcomms.add(new JoinSpectators());
        subcomms.add(new ForceSpectator());
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
                        GetSubComms().get(i).Perform(P, args,pr);
                    }
                }
            }
        }
        return false;
    }
}
