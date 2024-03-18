package org.naosh.mdparkourrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.naosh.decathlonmanager.DecathlonManager;

import java.io.FileNotFoundException;

public class RoundTimer
{
    private final JavaPlugin plugin;
    BukkitScheduler PreRoundScheduler;
    BukkitScheduler RoundScheduler;
    BossBar bar = Bukkit.createBossBar(
            ChatColor.BOLD + "RUN IN 0:25",
            BarColor.RED,
            BarStyle.SOLID);

    public RoundTimer(JavaPlugin plugin, MDParkourRace pr, DecathlonManager man)
    {
        this.plugin = plugin;


        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "clear @a");

        for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
        {
            for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "gamemode adventure " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j));
                bar.addPlayer(Bukkit.getPlayer(man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j)));
            }
        }
        //TODO: Barriers
        //RoundsSetup(pr, man);

        PreRoundScheduler = plugin.getServer().getScheduler();
        //Bukkit.broadcastMessage(ChatColor.BLUE + "Round STARTS IN 25 SECONDS");

        PreRoundScheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            //TODO: set the timer to 25f for 25 seconds.
            float timer = 0f;
            @Override
            public void run()
            {
                bar.setProgress(timer/25f);
                if(timer <=0)
                {
                    //TODO: Barriers
                    //RemBarriers(pr);
                    RT(pr);
                }
                timer--;
            }
        }, 0L,20L);
    }
    public void RT(MDParkourRace pr)
    {
        PreRoundScheduler.cancelTasks(plugin);

        bar.setTitle(ChatColor.BOLD + "END IN 5:00");
        bar.setColor(BarColor.BLUE);

        RoundScheduler=plugin.getServer().getScheduler();
        RoundScheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            float timer = 300f;
            @Override
            public void run()
            {
                bar.setProgress(timer/300f);
                bar.setTitle(ChatColor.BOLD + "END IN " + (int)Math.floor(timer/60) + ":" + (int)(timer - (Math.floor(timer/60)*60)));
                if(timer<=0)
                {
                    RoundScheduler.cancelTasks(pr);
                }
                timer--;
            }
        },0L, 20L);
        pr.end();
    }

    /*public void RoundsSetup(MDParkourRace pr, DecathlonManager man)
    {
        if(pr.rounds==1)
        {
            for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
            {
                for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                {
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " badlands");
                }
            }
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:badlands run fill 12 -37 41 12 -42 20 minecraft:barrier");
        }
        else if(pr.rounds == 2)
        {
            for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
            {
                for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                {
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " pits");
                }
            }
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:pits run fill 12 -37 41 12 -42 20 minecraft:barrier");
        }
        else
        {
            for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
            {
                for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                {
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " walls");
                }
            }
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:walls run fill 12 -37 41 12 -42 20 minecraft:barrier");
        }
        TeleportSpectators(man);
    }*/

    /*public void RemBarriers(MDParkourRace pr)
    {
        if(pr.rounds==1)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:badlands run fill 12 -37 41 12 -42 20 minecraft:air");
        }
        if(pr.rounds==2)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:pits run fill 17 1 25 17 -2 46 minecraft:air");
        }
        if(pr.rounds==3)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "execute in minecraft:walls run fill 18 -19 58 18 -22 81 minecraft:air");
        }
    }*/
    private void TeleportSpectators(DecathlonManager man)
    {
        for(int i=0;i<man.spectators.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + man.spectators.get(i).getName() + " pits");
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "gamemode spectator " + man.spectators.get(i).getName());

        }
    }
}
