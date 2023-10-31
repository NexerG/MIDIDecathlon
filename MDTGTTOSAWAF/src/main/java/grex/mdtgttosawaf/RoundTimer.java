package grex.mdtgttosawaf;

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

    public RoundTimer(JavaPlugin plugin, MDTGTTOSAWAF pr)
    {
        this.plugin = plugin;
        PreRoundScheduler = plugin.getServer().getScheduler();
        Bukkit.broadcastMessage(ChatColor.BLUE + "Round STARTS IN 10 SECONDS");

        PreRoundScheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                //TODO: remove blocks
                Bukkit.broadcastMessage(ChatColor.RED + "GO!");
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
