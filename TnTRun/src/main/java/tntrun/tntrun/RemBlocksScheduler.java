package tntrun.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
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
        BossBar bar = Bukkit.createBossBar(
                ChatColor.BOLD + "Starts in: 0:25",
                BarColor.RED,
                BarStyle.SOLID);
        for(int i=0;i<pls.size();i++)
            bar.addPlayer(pls.get(i));

        Bukkit.broadcastMessage(ChatColor.BLUE + "BLOCKS START DISSAPEARING IN 10 SECONDS");
        scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            float timer = 250f;
            @Override
            public void run()
            {
                if(timer<=0)
                {
                    bar.removeAll();
                    //if player is in play area
                    for(int i=0;i<pls.size();i++)
                    {
                        if(pls.get(i).getLocation().getY()<20 && !parent.Pos.contains(pls.get(i)))
                        {
                            //pls.get(i).teleport(new Location(pls.get(i).getWorld(),100,50,100));
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode spectator "+ pls.get(i).getName());
                            try
                            {
                                pls.get(i).sendTitle("YOU DIED", "wait for next round",5,100,5);
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
                        if(Lifetime.get(i)>4)
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
                        if(!pls.get(i).getGameMode().name().equalsIgnoreCase("spectator"))
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
                    }
                    //blocks that have been stepped on lifetime ++
                    for(int i=0;i<Lifetime.size();i++)
                    {
                        Lifetime.set(i,Lifetime.get(i)+1);
                    }
                }
                else
                {
                    bar.setTitle(ChatColor.BOLD + "Starts in 0:"+ (int)timer/10);
                    bar.setProgress(timer/250);
                    timer--;
                }
            }
        },0L,2L);
    }
}