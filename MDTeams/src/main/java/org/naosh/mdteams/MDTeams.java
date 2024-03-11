package org.naosh.mdteams;

import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MDTeams extends JavaPlugin implements TabCompleter
{
    private MasterTeamClass komanda;
    @Override
    public void onEnable()
    {
        //Teams logic
        //Don't @ me for this shit implementation. Man taip liepe dokumentacija daryt
        komanda = new MasterTeamClass();
        //ChatListener for colored text TODO: setup teams colors
        getServer().getPluginManager().registerEvents(new ChatListener(komanda), this);
        this.getCommand("squad").setExecutor(new CommandManager(komanda));
        this.getCommand("squad").setTabCompleter(new TeamsTabCompleter());
        //Komandos = TeamsInitialize();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        //TODO: unload teams
    }

    public MasterTeamClass getMasterTeam()
    {
        return komanda;
    }

    /*public List<MasterTeamClass> TeamsInitialize()
    {
        //TODO: implement loading up a json of all teams and their players
        return Komandos;
    }*/
}
