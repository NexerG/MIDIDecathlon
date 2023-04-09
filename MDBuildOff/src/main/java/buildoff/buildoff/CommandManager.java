package buildoff.buildoff;

import buildoff.buildoff.SubComms.SubCommandMaster;
import buildoff.buildoff.SubComms.end;
import buildoff.buildoff.SubComms.start;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    BuildOff par;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {
        return subcomms;
    }

    public CommandManager(BuildOff parent)
    {
        par=parent;
        //cia dedamos komandu klases
        subcomms.add(new start());
        subcomms.add(new end());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            if(command.getName()=="buildoff")
            {
                Player P = (Player) sender;
                if (args.length > 0)
                {
                    for (int i = 0; i < GetSubComms().size(); i++)
                    {
                        if (args[0].equalsIgnoreCase(GetSubComms().get(i).GetName()))
                        {
                            GetSubComms().get(i).Perform(P, args, par);
                        }
                    }
                }
            }
            else if(command.getName()=="vote")
            {

            }
        }
        return false;
    }
}
