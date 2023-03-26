package tntrun.tntrun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TNTTabsCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1)
        {
            //cia idedami komandu pasiulymai
            List<String> CommandNames=new ArrayList<>();
            CommandNames.add("test");
            CommandNames.add("setmatches");
            CommandNames.add("start");
            CommandNames.add("stop");
            return CommandNames;
        }
        return null;
    }
}
