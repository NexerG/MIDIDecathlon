package decathlonmanager.decathlonmanager;

import buildoff.buildoff.BuildOff;
import mdteams.mdteams.MDTeams;
import mduhc.mduhc.MDUHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tntrun.tntrun.Tntrun;

import java.util.List;

public final class DecathlonManager extends JavaPlugin implements Listener
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
        getServer().getPluginManager().registerEvents(this, this);
        komandos = (MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        uhc= (MDUHC) getServer().getPluginManager().getPlugin("MDUHC");
        buildOff= (BuildOff) getServer().getPluginManager().getPlugin("BuildOff");
        tntrun=(Tntrun) getServer().getPluginManager().getPlugin("TnTRun");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    public void decastart()
    {
        List<Player> zaidejai= (List<Player>) Bukkit.getOnlinePlayers();
        for(int i=0;i<zaidejai.size();i++)
        {
            boolean dum=false;
            for(int k=0;k<komandos.getMasterTeam().GetKomandos().size();k++)
            {
                for(int j=0;j<komandos.getMasterTeam().GetKomandos().get(k).Players.size();j++)
                {
                    if(zaidejai.get(i).getName().equalsIgnoreCase(komandos.getMasterTeam().GetKomandos().get(k).Players.get(j)))
                    {
                        dum=true;
                    }
                }
            }
            if(!dum)
            {
                zaidejai.get(i).sendMessage(ChatColor.RED+"YOU'RE NOT IN A TEAM. PLAYERS WITHOUT A TEAM WILL NOT TAKE PART IN THE TOURNAMENT. /komanda create <name>");
            }
        }
        Next("build");
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
        List<Player> dummy = (List<Player>) Bukkit.getOnlinePlayers();
        for(int i=0;i<dummy.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + dummy.get(i).getName() + " world");
        }
        //TODO: teleport everybody to the lobby
        if(what!="")
        {
            N=new ScheduleNextGame(this,this, what);
        }
        else Bukkit.broadcastMessage(ChatColor.GREEN+"DECATHLON IS OVER");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(getUhc().getisUHC() || getBuildOff().isBuildOff())
        {
            boolean dum=false;
            for(int i=0;i<komandos.getMasterTeam().GetKomandos().size();i++)
            {
                for(int j=0;j<komandos.getMasterTeam().GetKomandos().get(i).Players.size();j++)
                {
                    if(e.getPlayer().getName().equalsIgnoreCase(komandos.getMasterTeam().GetKomandos().get(i).Players.get(j)))
                    {
                        dum=true;
                    }
                }
            }
            if (!dum)
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "mvtp " + e.getPlayer().getName() + " world");
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "gamemode adventure " + e.getPlayer().getName());
            }
        }
        else {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + e.getPlayer().getName() + " world");
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "gamemode adventure " + e.getPlayer().getName());
        }
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
    public MDTeams getKomandos()
    {
        return komandos;
    }
}
