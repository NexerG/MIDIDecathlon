package grex.mdtgttosawaf;

import grex.mdtgttosawaf.SubComms.Start;
import grex.mdtgttosawaf.SubComms.SubCommandMaster;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    MDTGTTOSAWAF par;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {
        return subcomms;
    }

    public CommandManager(MDTGTTOSAWAF parent)
    {
        par=parent;
        //cia dedamos komandu klases
        subcomms.add(new Start());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            if(command.getName()=="chicken")
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
        }
        return false;
    }
}
