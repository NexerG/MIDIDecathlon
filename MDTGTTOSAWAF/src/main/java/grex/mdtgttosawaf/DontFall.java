package grex.mdtgttosawaf;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.FileNotFoundException;
import java.util.List;

public class DontFall
{
    private final JavaPlugin plugin;
    BukkitScheduler scheduler;

    public DontFall(JavaPlugin plugin, List<Player> pls, MDTGTTOSAWAF par)
    {
        this.plugin = plugin;
        scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                //if player is in play area
                for (int i = 0; i < pls.size(); i++)
                {
                    if(par.rounds == 1)
                        if (pls.get(i).getLocation().getY() < -46)
                        {
                            if (par.rounds == 1)
                            {
                                Bukkit.getServer().dispatchCommand(
                                        Bukkit.getServer().getConsoleSender()
                                        , "mvtp " + pls.get(i).getName() + " badlands");
                            }
                        }
                    if(par.rounds == 2)
                        if (pls.get(i).getLocation().getY() < -13)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + pls.get(i).getName() + " pits");
                        }
                    if(par.rounds == 3)
                    {
                        if (pls.get(i).getLocation().getY() < -33)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + pls.get(i).getName() + " walls");
                        }
                    }
                }
            }
        }, 200L, 2L);
    }
}
