package mdteams.mdteams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamsTabCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1)
        {
            List<String> CommandNames=new ArrayList<>();
            CommandNames.add("create");
            CommandNames.add("delete");
            CommandNames.add("invite NOT FULLY IMPLEMENTED");
            CommandNames.add("kick");
            CommandNames.add("list");
            CommandNames.add("color NOT IMPLEMENTED");
            CommandNames.add("accept NOT IMPLEMENTED");
            CommandNames.add("decline NOT IMPLEMENTED");
            return CommandNames;
        }
        return null;
    }
}
