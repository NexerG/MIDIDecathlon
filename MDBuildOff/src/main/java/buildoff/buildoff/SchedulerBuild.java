package buildoff.buildoff;

import decathlonmanager.decathlonmanager.DecathlonManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public class SchedulerBuild
{
    private final JavaPlugin plugin;
    BukkitScheduler scheduler;

    public SchedulerBuild(JavaPlugin plugin)
    {
        this.plugin = plugin;
        scheduler=plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                ChangeMode();
            }
        },300L);//TODO:delay = 30*60*20 = 36000L || 30 min * 60s *20tp/s
    }

    private void ChangeMode()
    {
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("tnt");
    }
}
