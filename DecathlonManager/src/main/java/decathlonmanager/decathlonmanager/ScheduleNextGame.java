package decathlonmanager.decathlonmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ScheduleNextGame
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    public ScheduleNextGame(JavaPlugin plugin, DecathlonManager man, String what)
    {
        this.plugin = plugin;
        Bukkit.broadcastMessage(ChatColor.GREEN+"NEXT GAME IN 5 MINUTES");
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(what=="build")
                {
                    man.getUhc().start();
                } else if (what=="tnt")
                {
                    man.getTntrun().startgame();
                }
                else if(what=="uhc")
                {
                    man.getUhc().start();
                }
            }
        },6000L);
    }
}
