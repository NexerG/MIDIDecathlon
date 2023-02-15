package mdteams.mdteams;

import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MDTeams extends JavaPlugin implements TabCompleter
{
    private List<MasterTeamClass> Komandos;
    @Override
    public void onEnable()
    {
        //ChatListener for colored text TODO: setup teams colors
        getServer().getPluginManager().registerEvents(new ChatListener(this),this);

        //Teams logic
        //Don't @ me for this shit implementation. Man taip liepe dokumentacija daryt
        MasterTeamClass komanda=new MasterTeamClass();
        this.getCommand("Komanda").setExecutor(new CommandManager(komanda));
        this.getCommand("Komanda").setTabCompleter(new TeamsTabCompleter());

        //Komandos = TeamsInitialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //TODO: unload teams
    }

    public List<MasterTeamClass> TeamsInitialize()
    {
        //TODO: implement loading up a json of all teams and their players
        return Komandos;
    }
}
