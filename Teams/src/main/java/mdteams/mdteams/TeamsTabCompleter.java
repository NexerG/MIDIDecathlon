package mdteams.mdteams;

import org.bukkit.Bukkit;
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
            CommandNames.add("delete NOT IMPLEMENTED");
            CommandNames.add("invite NOT IMPLEMENTED");
            CommandNames.add("kick");
            return CommandNames;
        }
        return null;
    }
}
