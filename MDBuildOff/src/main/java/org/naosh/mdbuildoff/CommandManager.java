package org.naosh.mdbuildoff;

import org.naosh.mdbuildoff.SubComms.SubCommandMaster;
import org.naosh.mdbuildoff.SubComms.end;
import org.naosh.mdbuildoff.SubComms.start;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    MDBuildOff par;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms()
    {
        return subcomms;
    }

    public CommandManager(MDBuildOff parent)
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
