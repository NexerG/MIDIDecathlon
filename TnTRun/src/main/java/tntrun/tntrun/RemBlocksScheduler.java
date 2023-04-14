package tntrun.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RemBlocksScheduler
{
    private final JavaPlugin plugin;
    private List<Location> Loc=new ArrayList<>();
    private List<Integer> Lifetime=new ArrayList<>();

    private List<Player> pls;

    BukkitScheduler scheduler;

    public RemBlocksScheduler(JavaPlugin plugin, List<Player> p, Tntrun parent)
    {
        this.plugin = plugin;
        pls=p;
        Bukkit.broadcastMessage(ChatColor.BLUE + "BLOCKS START DISSAPEARING IN 10 SECONDS");
        scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                //if player is in play area
                for(int i=0;i<pls.size();i++)
                {
                    if(pls.get(i).getLocation().getY()<20)
                    {
                        //pls.get(i).teleport(new Location(pls.get(i).getWorld(),100,50,100));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode spectator "+ pls.get(i).getName());
                        try
                        {
                            pls.get(i).sendTitle("YOU DIED", "",1,7,3);
                            parent.Died(pls.get(i));
                        } catch (FileNotFoundException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
                //kill block
                for(int i=0;i<Loc.size();i++)
                {
                    if(Lifetime.get(i)>5)
                    {
                        Loc.get(i).getBlock().setType(Material.AIR);
                        Loc.remove(i);
                        Lifetime.remove(i);
                        --i;
                    }
                }
                //game mechanic
                for(int i=0;i< pls.size();i++)
                {
                    //TODO: more accurate player approximation for better block detection under. usually eats two blocks instead of one
                    Location lcStandart=new Location(pls.get(i).getWorld(),Math.round(pls.get(i).getLocation().getX()),pls.get(i).getLocation().getY()-1,Math.round(pls.get(i).getLocation().getZ()));
                    Location lcX=new Location(pls.get(i).getWorld(),Math.round(pls.get(i).getLocation().getX()-0.8),pls.get(i).getLocation().getY()-1,Math.round(pls.get(i).getLocation().getZ()));
                    Location lcZ=new Location(pls.get(i).getWorld(),Math.round(pls.get(i).getLocation().getX()),pls.get(i).getLocation().getY()-1,Math.round(pls.get(i).getLocation().getZ()-0.8));
                    Location lcXZ=new Location(pls.get(i).getWorld(),Math.round(pls.get(i).getLocation().getX()-0.8),pls.get(i).getLocation().getY()-1,Math.round(pls.get(i).getLocation().getZ()-0.8));
                    //TODO: THIS IS FUCKING STUPID WHY CANT I JUST HAVE "STANDING ON THIS [SHIT]" BUT NOOO :((
                    if(!Loc.contains(lcStandart))
                    {
                        if(lcStandart.getBlock().getType()==Material.SANDSTONE)
                        {
                            lcStandart.getBlock().setType(Material.RED_SANDSTONE);
                            Loc.add(lcStandart);
                            Lifetime.add(0);
                            //Bukkit.getLogger().info(lcStanart.toString());
                        }
                    }
                    if(!Loc.contains(lcX))
                    {
                        if(lcX.getBlock().getType()==Material.SANDSTONE)
                        {
                            lcX.getBlock().setType(Material.RED_SANDSTONE);
                            Loc.add(lcX);
                            Lifetime.add(0);
                        }
                    }
                    if(!Loc.contains(lcZ))
                    {
                        if(lcZ.getBlock().getType()==Material.SANDSTONE)
                        {
                            lcZ.getBlock().setType(Material.RED_SANDSTONE);
                            Loc.add(lcZ);
                            Lifetime.add(0);
                        }
                    }
                    if(!Loc.contains(lcXZ))
                    {
                        if(lcXZ.getBlock().getType()==Material.SANDSTONE)
                        {
                            lcXZ.getBlock().setType(Material.RED_SANDSTONE);
                            Loc.add(lcXZ);
                            Lifetime.add(0);
                        }
                    }
                }
                //blocks that have been stepped on lifetime ++
                for(int i=0;i<Lifetime.size();i++)
                {
                    Lifetime.set(i,Lifetime.get(i)+1);
                }

            }
        },200L,2L);
    }
}
