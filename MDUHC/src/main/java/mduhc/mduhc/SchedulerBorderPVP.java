package mduhc.mduhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class SchedulerBorderPVP
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;
    ScheduleDeathMatch dm;

    public SchedulerBorderPVP(JavaPlugin plugin, MDUHC par)
    {
        this.plugin = plugin;
        scheduler=plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender(),"worldborder set 32 2400");
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "rg flag __global__ pvp allow");
                Bukkit.broadcastMessage(ChatColor.RED + "PVP is on. Border is shrinking. Shrinking until 32 blocks wide in 40 minutes");
                dm=new ScheduleDeathMatch(plugin,par);
            }
        },200L); //6000L
    }
}
