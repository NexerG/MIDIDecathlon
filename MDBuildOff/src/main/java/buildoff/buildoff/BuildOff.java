package buildoff.buildoff;

import decathlonmanager.decathlonmanager.DecathlonManager;
import mdteams.mdteams.Komanda;
import mdteams.mdteams.MDTeams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BuildOff extends JavaPlugin implements Listener
{
    private boolean IsBuildOff=false;
    private boolean IsVoting=false;
    private DecathlonManager man;

    List<Player> ps=new ArrayList<>();
    List<Komanda> komandos = new ArrayList<>();

    @Override
    public void onEnable()
    {
        // Plugin startup login
        this.getCommand("buildoff").setExecutor(new CommandManager(this));
        this.getCommand("buildoff").setTabCompleter(new BuildOffTabsCompleter());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
    public void start()
    {
        IsBuildOff=true;
        MDTeams BaseTeam = (MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        komandos=BaseTeam.getMasterTeam().GetKomandos();

        for(int i=0;i<komandos.size();i++)
        {
            for(int j=0;j<komandos.get(i).Players.size();j++)
            {
                //creative
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "gamemode creative "+ komandos.get(i).Players.get(j));
            }
        }
        //TODO:set timer to 30 minutes
        SchedulerBuild SCB=new SchedulerBuild(this);
    }

    public boolean isBuildOff()
    {
        return IsBuildOff;
    }

    public void End()
    {
        IsBuildOff=false;
        IsVoting=true;
        /*Bukkit.broadcastMessage(ChatColor.BLUE + "15 SECONDS TO VOTE WITH /vote 1-10");
        for(int i=0;i< komandos.size();i++)
        {
            //TODO: TELEPORT ALL TO VOTE PLACE
            for(int j=0;j< komandos.size();j++)
            {
                for(int k=0;k< komandos.get(j).Players.size();k++)
                {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp "+komandos.get(j).Players.get(k)+" "+String.valueOf(i*32+16)+" "+String.valueOf(10)+String.valueOf(10));
                }
            }

            try{
                Thread.sleep(15000);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }*/
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("");
        man.Next("tnt");
    }
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e)
    {
        if(IsBuildOff)
        {
            for(int i=0;i<komandos.size();i++)
            {
                for(int j=0;j<komandos.get(i).Players.size();j++)
                {
                    if(e.getPlayer().getName().equalsIgnoreCase(komandos.get(i).Players.get(i)))
                    {
                        if(i*32<e.getBlock().getLocation().getBlockX() && (i+1)*32-1>e.getBlock().getLocation().getBlockX())
                        {
                            if(0<e.getBlock().getLocation().getBlockZ() && e.getBlock().getLocation().getBlockZ()<32)
                            {

                            }
                            else
                            {
                                e.getBlock().setType(Material.AIR);
                                e.getPlayer().sendMessage(ChatColor.RED + "INVALID PLACE COORDINATE Z");
                            }
                        }
                        else
                        {
                            e.getBlock().setType(Material.AIR);
                            e.getPlayer().sendMessage(ChatColor.RED +"INVALID PLACE COORDINATE X");
                        }
                    }
                    else
                    {
                        e.getBlock().setType(Material.AIR);
                        e.getPlayer().sendMessage("INVALID PLACE");
                    }
                }
            }
        }
    }
}
