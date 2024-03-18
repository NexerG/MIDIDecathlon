package org.naosh.decathlonmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class ScheduleNextGame
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    public ScheduleNextGame(JavaPlugin plugin, DecathlonManager man, String what)
    {
        BossBar bar = Bukkit.createBossBar(
                ChatColor.BOLD + "Time till next game",
                BarColor.BLUE,
                BarStyle.SOLID);
        this.plugin = plugin;
        for(int i=0;i<Bukkit.getOnlinePlayers().size();i++)
        {
            ((List<? extends Player>) Bukkit.getOnlinePlayers()).get(i).sendTitle(ChatColor.GREEN+"NEXT GAME IN 5 MINUTES","Next game is: "+what,5,200,5);
            bar.addPlayer(((List<? extends Player>) Bukkit.getOnlinePlayers()).get(i));
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "NEXT GAME IN 5 MINUTES");
        scheduler=plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            float timer = 10f;//300f;
            @Override
            public void run()
            {
                if(timer>60)
                    bar.setColor(BarColor.BLUE);
                else
                    bar.setColor(BarColor.RED);
                bar.setTitle(ChatColor.BOLD + "Time till next game " + (int)Math.floor(timer/60) + ":" + (int)(timer - (Math.floor(timer/60)*60)));
                bar.setProgress(timer/300f);

                if(timer <= 0)
                {
                    if (what == "Chicken")
                    {
                      man.getChicken().start();
                    } else if (what == "TnTRun")
                    {
                    /*for(int i=0;i<man.getKomandos().getMasterTeam().GetKomandos().size();i++)
                    {
                        for(int j=0;j<man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size();j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " tntrun");
                        }
                    }*/
                       // man.getTntrun().startgame();
                    } else if (what == "UHC")
                    {
                        for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                        {
                            for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                            {
                                Bukkit.getServer().dispatchCommand(
                                        Bukkit.getServer().getConsoleSender()
                                        , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " uhc");
                            }
                        }
                        //man.getUhc().start();
                    }else if (what == "HG")
                    {
                        for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                        {
                            for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                            {
                                Bukkit.getServer().dispatchCommand(
                                        Bukkit.getServer().getConsoleSender()
                                        , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " uhc");
                            }
                        }
                        man.getHG().start();
                    }else if (what == "Parkour")
                    {
                        for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                        {
                            for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                            {
                                Bukkit.getServer().dispatchCommand(
                                        Bukkit.getServer().getConsoleSender()
                                        , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " SwagKour");
                            }
                        }
                        man.getParkour().startgame();
                    }
                    bar.removeAll();
                    scheduler.cancelTasks(plugin);
                }
                timer--;
            }
        },0L, 20L); //TODO: set delay to 5m*60s*20TicsPerS=6000 || 6000ticks = 5 minutes
    }
}
