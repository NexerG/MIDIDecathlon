package org.naosh.mdbuildoff;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class SchedulerBuild
{
    private final JavaPlugin plugin;
    BukkitScheduler scheduler;
    MDBuildOff bd;

    public SchedulerBuild(JavaPlugin plugin, MDBuildOff buildOff)
    {
        this.plugin = plugin;
        bd=buildOff;
        scheduler=plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                ChangeMode();
            }
        },36000L);//TODO:delay = 30*60*20 = 36000L || 30 min * 60s *20tp/s
    }

    private void ChangeMode()
    {
        bd.End();
    }
}
