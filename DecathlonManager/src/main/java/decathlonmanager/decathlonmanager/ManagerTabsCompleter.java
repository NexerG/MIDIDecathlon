package decathlonmanager.decathlonmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ManagerTabsCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 1)
        {
            //cia idedami komandu pasiulymai
            List<String> CommandNames = new ArrayList<>();
            CommandNames.add("initiate");
            CommandNames.add("abort");
            return CommandNames;
        }
        return null;
    }

}
