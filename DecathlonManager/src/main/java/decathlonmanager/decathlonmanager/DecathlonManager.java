package decathlonmanager.decathlonmanager;

import buildoff.buildoff.BuildOff;
import grex.mdtgttosawaf.MDTGTTOSAWAF;
import mdteams.mdteams.MDTeams;
import mduhc.mduhc.MDUHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import tntrun.tntrun.Tntrun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public final class DecathlonManager extends JavaPlugin implements Listener
{
    private MDTeams komandos;
    private MDUHC uhc;
    private BuildOff buildOff;
    private Tntrun tntrun;
    private MDTGTTOSAWAF chicken;

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
        chicken=(MDTGTTOSAWAF) getServer().getPluginManager().getPlugin("MDTGTTOSAWAF");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "/rg flag __global__ pvp deny");
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
        Next("Chicken");
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
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "clear @a");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "effect clear @a");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "rg flag __global__ pvp deny");

        List<Player> dummy = (List<Player>) Bukkit.getOnlinePlayers();

        BukkitScheduler sc=this.getServer().getScheduler();

        for(int i=0;i<dummy.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + dummy.get(i).getName() + " e:lobby-37:735,24,-1012");
        }

        if(what!="")
        {
            N=new ScheduleNextGame(this,this, what);
        }

        else
        {
            for(int i=0;i<Bukkit.getOnlinePlayers().size();i++)
            {
                ((List<? extends Player>) Bukkit.getOnlinePlayers()).get(i).sendTitle(ChatColor.GREEN+"DECATHLON IS OVER!","THANKS FOR PLAYING",5,200,5);
            }
            Bukkit.broadcastMessage(ChatColor.GREEN+"DECATHLON IS OVER");
            CalcPoints();
        }
    }

    private void CalcPoints()
    {
        try
        {
            //pirmas tnt
            File file = new File("rez_TNT_0.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //antras tnt
            File file = new File("rez_TNT_1.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //trecias tnt
            File file = new File("rez_TNT_2.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //pirmas Chicken
            File file = new File("rez_Chicken_3.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //antras Chicken
            File file = new File("rez_Chicken_2.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //trecias Chicken
            File file = new File("rez_Chicken_1.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            //UHC
            File file = new File("UHCrez.txt");
            Scanner sc = new Scanner(file);
            addPts(sc);
            sc.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < komandos.getMasterTeam().GetKomandos().size(); i++)
        {
            komandos.getMasterTeam().GetKomandos().get(i).addPoints(komandos.getMasterTeam().GetKomandos().get(i).GetKills() * 2);
        }

        //israsom total taskus
        PrintWriter out = null;
        try
        {
            out = new PrintWriter("totalrez.txt");
            for (int i = 0; i < komandos.getMasterTeam().GetKomandos().size(); i++)
            {
                out.println(komandos.getMasterTeam().GetKomandos().get(i).TName + " " + komandos.getMasterTeam().GetKomandos().get(i).getPoints());
            }
            out.close();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void addPts(Scanner sc)
    {
        while(sc.hasNextLine())
        {
            String dum=sc.nextLine();
            for(int i=0;i<komandos.getMasterTeam().GetKomandos().size();i++)
            {
                for(int j=0;j<komandos.getMasterTeam().GetKomandos().get(i).Players.size();j++)
                {
                    Bukkit.getLogger().info(dum);
                    if(Bukkit.getPlayer(dum.toString()).getName().equalsIgnoreCase(komandos.getMasterTeam().GetKomandos().get(i).Players.get(j).toString()));
                    {
                        if(i<=5)
                            komandos.getMasterTeam().GetKomandos().get(i).addPoints(5);
                        else if (i<=10 && i>5)
                        {
                            komandos.getMasterTeam().GetKomandos().get(i).addPoints(4);
                        } else if (i>10 && i<=20)
                        {
                            komandos.getMasterTeam().GetKomandos().get(i).addPoints(2);
                        }
                        else komandos.getMasterTeam().GetKomandos().get(i).addPoints(1);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(!getUhc().getisUHC() || !getBuildOff().isBuildOff())
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
                BukkitScheduler scheduler=this.getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(this, new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getServer().getConsoleSender()
                                , "mvtp " + e.getPlayer().getName() + " e:lobby-37:735,24,-1012");
                    }
                },1L);
            }
        }
        else {
            BukkitScheduler scheduler=this.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "mvtp " + e.getPlayer().getName() + " e:lobby-37:735,24,-1012");
                }
            },2L);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        BukkitScheduler scheduler;
        scheduler=this.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getServer().getConsoleSender()
                                , "mvtp " + e.getPlayer().getName() + " e:lobby-37:735,24,-1012");
                    }
                },1L);
    }
    @EventHandler
    public void onFallAndBreathingDamage(EntityDamageEvent e)
    {
        if(!getUhc().getisUHC())
        {
            if(e.getCause()== EntityDamageEvent.DamageCause.FALL || e.getCause()== EntityDamageEvent.DamageCause.DROWNING)
            {
                e.setCancelled(true);
            }
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
    public MDTGTTOSAWAF getChicken(){return  chicken;};
    public MDTeams getKomandos()
    {
        return komandos;
    }
}
