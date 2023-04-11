package decathlonmanager.decathlonmanager;

import buildoff.buildoff.BuildOff;
import mdteams.mdteams.MDTeams;
import mduhc.mduhc.MDUHC;
import org.bukkit.plugin.java.JavaPlugin;
import tntrun.tntrun.Tntrun;

public final class DecathlonManager extends JavaPlugin
{
    private MDTeams komandos;
    private MDUHC uhc;
    private BuildOff buildOff;
    private Tntrun tntrun;

    private  ScheduleNextGame N;
    private boolean abort=false;
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("manager").setExecutor(new CommandManager(this));
        this.getCommand("manager").setTabCompleter(new ManagerTabsCompleter());

        //TODO: tell the other plugins that "I AM THE MANAGER". nes sitas pluginas uzsiloadins i serveri paskutinis
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    public void flipAbort()
    {
        if (abort)
            abort=false;
        else abort=true;
    }
    public String getAbort()
    {
        return String.valueOf(abort);
    }

    public void Next(String what)
    {
        //TODO: teleport everybody to the lobby
        N=new ScheduleNextGame(this,this,what);
    }

    public MDUHC getUhc()
    {
        return uhc;
    }
    public BuildOff getBuildOff()
    {
        return buildOff;
    }

    public Tntrun getTntrun()
    {
        return tntrun;
    }
}
