package mduhc.mduhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ScheduleDeathMatch
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    public ScheduleDeathMatch(JavaPlugin plugin, MDUHC par)
    {
        this.plugin = plugin;
        scheduler=plugin.getServer().getScheduler();
        Bukkit.broadcastMessage(ChatColor.RED + "Arena in 40 minutes");
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                par.DeathMatch();
            }
        },48000L);
    }
}
