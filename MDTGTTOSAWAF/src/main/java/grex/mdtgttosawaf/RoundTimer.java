package grex.mdtgttosawaf;

import decathlonmanager.decathlonmanager.DecathlonManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.FileNotFoundException;

public class RoundTimer
{
    private final JavaPlugin plugin;
    BukkitScheduler PreRoundScheduler;
    BukkitScheduler RoundScheduler;

    public RoundTimer(JavaPlugin plugin, MDTGTTOSAWAF pr, DecathlonManager man)
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
            }
        }
        RoundsSetup(pr, man);

        PreRoundScheduler = plugin.getServer().getScheduler();
        Bukkit.broadcastMessage(ChatColor.BLUE + "Round STARTS IN 10 SECONDS");

        PreRoundScheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                {
                    for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                    {
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getServer().getConsoleSender()
                                , "give " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " minecraft:sandstone 256");
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getServer().getConsoleSender()
                                , "give " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " minecraft:diamond_pickaxe");
                    }
                }
                RemBarriers(pr);
                Bukkit.broadcastMessage(ChatColor.RED + "GO! PVP IS ON");
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "rg flag __global__ pvp allow");

                for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                {
                    for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                    {
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getServer().getConsoleSender()
                                , "gamemode survival " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j));
                    }
                }

                RT(pr);
            }
        }, 200L);
    }
    public void RT(MDTGTTOSAWAF pr)
    {
        RoundScheduler=plugin.getServer().getScheduler();
        RoundScheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pr.end();
                } catch (FileNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
            }
        },6000L);
    }

    public void RoundsSetup(MDTGTTOSAWAF pr, DecathlonManager man)
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
    }

    public void RemBarriers(MDTGTTOSAWAF pr)
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
    }
}
