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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
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
                Bukkit.getPlayer(komandos.get(i).Players.get(j)).sendTitle(ChatColor.BLUE+"ILIUZIJOS","30 minutes to build",5,200,10);
            }
        }
        //TODO:set timer to 30 minutes
        SchedulerBuild SCB=new SchedulerBuild(this,this);
    }

    public boolean isBuildOff()
    {
        return IsBuildOff;
    }

    public void End()
    {
        IsBuildOff=false;
        //IsVoting=true;
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
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("TnTRun");
    }
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e)
    {
        if(IsBuildOff)
        {
            int indexas=999;
            if(e.getBlock().getType()!=Material.BEDROCK)
            {
                for(int i=0;i<komandos.size();i++)
                {
                    for(int j=0;j<komandos.get(i).Players.size();j++)
                    {
                        //block placed
                        if(e.getPlayer().getName().equalsIgnoreCase(komandos.get(i).Players.get(j)))
                        {
                            indexas=i;
                        }
                    }
                }
                if(indexas*32<e.getBlock().getLocation().getBlockX() && (indexas+1)*32-1>e.getBlock().getLocation().getBlockX())
                {
                    if(0<e.getBlock().getLocation().getBlockZ() && e.getBlock().getLocation().getBlockZ()<32)
                    {

                    }
                    else
                    {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "INVALID PLACE COORDINATE Z");
                    }
                }
                else
                {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED +"INVALID PLACE COORDINATE X");
                }

            }
            else {
                e.setCancelled(true);
                e.getPlayer().sendMessage("CANNOT PLACE OR BREAK BEDROCK");
            }
        }
    }
    @EventHandler
    public void onBlockBroken(BlockBreakEvent e)
    {
        if(IsBuildOff)
        {
            int indexas=999;
            if(e.getBlock().getType()!=Material.BEDROCK)
            {
                for(int i=0;i<komandos.size();i++)
                {
                    for(int j=0;j<komandos.get(i).Players.size();j++)
                    {
                        //block placed
                        if(e.getPlayer().getName().equalsIgnoreCase(komandos.get(i).Players.get(j)))
                        {
                            indexas=i;
                        }
                    }
                }
                if(indexas*32<e.getBlock().getLocation().getBlockX() && (indexas+1)*32-1>e.getBlock().getLocation().getBlockX())
                {
                    if(0<e.getBlock().getLocation().getBlockZ() && e.getBlock().getLocation().getBlockZ()<32)
                    {

                    }
                    else
                    {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "INVALID PLACE COORDINATE Z");
                    }
                }
                else
                {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED +"INVALID PLACE COORDINATE X");
                }

            }
            else {
                e.setCancelled(true);
                e.getPlayer().sendMessage("CANNOT PLACE OR BREAK BEDROCK");
            }
        }
    }

    @EventHandler
    public void TNTSpawned(ExplosionPrimeEvent e)
    {
        if(isBuildOff())
        {
            e.setCancelled(true);
        }
    }
}

