package tntrun.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemBlocksScheduler
{
    private final JavaPlugin plugin;
    private List<Location> Loc=new ArrayList<>();
    private List<Integer> Lifetime=new ArrayList<>();

    private List<Player> pls=new ArrayList<>();

    BukkitScheduler scheduler;

    public RemBlocksScheduler(JavaPlugin plugin, List<Player> p)
    {
        this.plugin = plugin;
        pls=p;
        scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                for(int i=0;i<pls.size();i++)
                {
                    if(pls.get(i).getLocation().getY()<20)
                    {
                        pls.get(i).teleport(new Location(pls.get(i).getWorld(),100,50,100));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode spectator "+ pls.get(i).getName());
                        pls.remove(i);
                        --i;
                    }
                }
                for(int i=0;i<Loc.size();i++)
                {
                    if(Lifetime.get(i)>5)
                    {
                        Loc.get(i).add(0,-1,0).getBlock().setType(Material.AIR);
                        Loc.remove(i);
                        Lifetime.remove(i);
                        --i;
                    }
                }
                for(int i=0;i< pls.size();i++)
                {
                    if(!Loc.contains(pls.get(i).getLocation()))
                    {
                        Loc.add(pls.get(i).getLocation());
                        Lifetime.add(0);
                    }
                }
                for(int i=0;i<Lifetime.size();i++)
                {
                    Lifetime.set(i,Lifetime.get(i)+1);
                }
            }
        },0L,2L);
    }
    public void playerDied(Player p)
    {
        //pls.remove(p);
    }
}
