package mduhc.mduhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ScheduleDeathMatch
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    Long timer=48000L; //48000L
    Long tOffset=6000L; //6000L

    public ScheduleDeathMatch(JavaPlugin plugin, MDUHC par)
    {
        this.plugin = plugin;
        scheduler=plugin.getServer().getScheduler();
        Bukkit.broadcastMessage(ChatColor.RED + "ARENA IN "+ (int) (timer + tOffset)/1200 +" MINUTES");
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                dmwarning(par);
            }
        },6000);
    }
    public void dmwarning(MDUHC par)
    {
        Bukkit.broadcastMessage(ChatColor.RED + "ARENA IN "+(tOffset.intValue()/20/60)+" MINUTES");
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "rg flag __global__ pvp deny");
                par.DeathMatch();
                Bukkit.broadcastMessage(ChatColor.RED + "PVP ON in 15s");
                dmstart();
            }
        },tOffset);
    }

    public void dmstart()
    {
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "rg flag __global__ pvp allow");
                Bukkit.broadcastMessage(ChatColor.RED + "PVP IS ON");
            }
        },300L); //300L
    }
}
