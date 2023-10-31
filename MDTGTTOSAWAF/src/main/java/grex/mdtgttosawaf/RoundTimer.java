package grex.mdtgttosawaf;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class RoundTimer
{
    private final JavaPlugin plugin;
    BukkitScheduler scheduler;
    public RoundTimer(JavaPlugin plugin, MDTGTTOSAWAF pr)
    {
        this.plugin=plugin;
        scheduler=plugin.getServer().getScheduler();

        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                //TODO: tiesiog kada baigiasi roundas
                pr.end();
            }
        },6000L);
    }
}
