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
        }

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
                if(pr.rounds==1)
                {
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "execute in minecraft:badlands run fill 12 -37 41 12 -42 20 minecraft:air");
                }
                Bukkit.broadcastMessage(ChatColor.RED + "GO!");

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
}
