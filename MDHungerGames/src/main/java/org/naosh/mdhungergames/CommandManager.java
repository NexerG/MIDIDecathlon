package org.naosh.mdhungergames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.naosh.mdhungergames.SubComms.*;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {
    MDHungerGames pr;
    private ArrayList<SubCommandMaster> subcomms = new ArrayList<>();

    public ArrayList<SubCommandMaster> GetSubComms() {

        return subcomms;
    }

    public CommandManager(MDHungerGames parent) {
        pr = parent;
        subcomms.add(new Spawn());
        subcomms.add(new Start());
        //cia dedamos komandu klases

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player P = (Player) sender;
            if (args.length > 0) {
                for (int i = 0; i < GetSubComms().size(); i++) {
                    if (args[0].equalsIgnoreCase(GetSubComms().get(i).GetName())) {
                        GetSubComms().get(i).Perform(P, args, pr);
                    }
                }
            }
        }
        return false;
    }
}

