package buildoff.buildoff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class BuildOffTabsCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1)
        {
            //cia idedami komandu pasiulymai
            List<String> CommandNames=new ArrayList<>();
            CommandNames.add("start");
            return CommandNames;
        }
        return null;
    }
}
