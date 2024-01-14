package mdteams.mdteams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TeamsTabCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 1)
        {
            //cia idedami komandu pasiulymai
            List<String> CommandNames = new ArrayList<>();
            CommandNames.add("create");
            //CommandNames.add("rename NOT IMPLEMENTED");
            CommandNames.add("delete");
            CommandNames.add("invite");
            CommandNames.add("kick");
            CommandNames.add("list");
            //CommandNames.add("color");
            CommandNames.add("accept");
            CommandNames.add("decline");
            CommandNames.add("points");
            return CommandNames;
        }
        return null;
    }
}
