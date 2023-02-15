package mdteams.mdteams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.VersionCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MDTeams extends JavaPlugin
{
    private List<Team> Komandos;

    @Override
    public void onEnable()
    {
        //ChatListener for colored text TODO: setup teams colors
        getServer().getPluginManager().registerEvents(new ChatListener(this),this);
        //Teams logic
        //Don't @ me for this shit implementation. Man taip liepe dokumentacija daryt
        this.getCommand("MDTeam").setExecutor(new TeamCreate());
        //Komandos = TeamsInitialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //TODO: unload teams
    }

    public List<Team> TeamsInitialize()
    {
        //TODO: implement loading up a json of all teams and their players
        List<Team> Komandos= null;
        return Komandos;
    }
}
