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
                //TODO: pvp on
                Bukkit.broadcastMessage(ChatColor.RED + "PVP is on. Border is shrinking");
                dm=new ScheduleDeathMatch(plugin,par);
            }
        },200L); //6000L
    }
}
