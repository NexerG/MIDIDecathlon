package mdbedwars.mdbedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import sun.text.resources.ext.CollationData_lt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public final class MDBedWars extends JavaPlugin implements Listener
{
    private List<LootSpawner> LS=new ArrayList<>();
    private HashMap<Player,Kit> inventorius=new HashMap<>();
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this,this);
        // Plugin startup logic
    }

    public void BeginGame()
    {
        List<Player> OnlineP= (List<Player>) getServer().getOnlinePlayers();
        Bukkit.getLogger().info(String.valueOf(OnlineP.size()));
        Bukkit.getLogger().info("beginning of begin game");
        //TODO: get playing teams
        //TODO: set item spawner coordinates
        for(int i=0;i<12;i++)
        {
            //LS.add(new LootSpawner("owner",new Location(getServer().getWorld("world"),0,0,0)));
        }
        for(int i=0;i<OnlineP.size();i++)
        {
            Bukkit.getLogger().info(OnlineP.get(i).getLocation().toString());
        }
        Bukkit.getLogger().info("after for");
        LS.add(new LootSpawner("owner", OnlineP.get(0).getLocation()));

        Bukkit.getLogger().info("pre get all online players");
        for(int i=0;i<Bukkit.getOnlinePlayers().size();i++)
        {
            Bukkit.getLogger().info("pre give");
            inventorius.put(OnlineP.get(i),new Kit(OnlineP.get(i)));
            Bukkit.getLogger().info("post give");
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        BeginGame();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
