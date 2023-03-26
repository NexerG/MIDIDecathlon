package tntrun.tntrun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tntrun.tntrun.SubComms.*;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    Tntrun pr;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {
        return subcomms;
    }

    public CommandManager(Tntrun parent)
    {
        pr=parent;
        //cia dedamos komandu klases
        subcomms.add(new BlockRemTest());
        subcomms.add(new SetMatches());
        subcomms.add(new StartGame());
        subcomms.add(new StopGame());
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
