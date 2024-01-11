package grex.mdtgttosawaf;

import decathlonmanager.decathlonmanager.DecathlonManager;
import mdteams.mdteams.MDTeams;
import grex.mdtgttosawaf.SubComms.ChickenTabsCompleter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class MDTGTTOSAWAF extends JavaPlugin implements Listener
{
    private DecathlonManager man;
    Boolean TGTTOSAWAF = false;
    public int rounds=3;
    List<Player> ActiveRoundPlayers = new ArrayList<>();
    public List<Player> Pos = new ArrayList<>();
    private RoundTimer match;
    private DontFall fall;

    @Override
    public void onEnable() {
        man=(DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        this.getCommand("chicken").setExecutor(new CommandManager(this));
        this.getCommand("chicken").setTabCompleter(new ChickenTabsCompleter());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void start()
    {
        //TODO: tp players to arena
        TGTTOSAWAF = true;
        MDTeams tm= (MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        ActiveRoundPlayers.clear();
        Pos.clear();
        for(int i=0; i<tm.getMasterTeam().GetKomandos().size();i++)
        {
            for(int j=0;j<tm.getMasterTeam().GetKomandos().get(i).Players.size();j++)
            {
                if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(tm.getMasterTeam().GetKomandos().get(i).Players.get(j))))
                {
                    ActiveRoundPlayers.add(Bukkit.getPlayer(tm.getMasterTeam().GetKomandos().get(i).Players.get(j)));
                }
            }
        }
        match= new RoundTimer(this,this, man);
        fall = new DontFall(this, ActiveRoundPlayers, this);
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "rg flag __global__ pvp deny");
    }

    public void end() throws FileNotFoundException
    {
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "rg flag __global__ pvp deny");
        PrintWriter out= new PrintWriter("rez_Chicken"+String.valueOf(rounds)+".txt");
        for(int i = 0; i<= Pos.size()-1; i++)
        {
            Bukkit.getLogger().info(String.valueOf(i));
            out.println(Pos.get(i).getName());
        }
        out.close();
        rounds--;
        TGTTOSAWAF = false;
        if (rounds > 0)
        {
            start();
        } else
        {
            Over();
        }
        match.RoundScheduler.cancelTasks(this);
        fall.scheduler.cancelTasks(this);
    }
    public void Over()
    {
        Bukkit.broadcastMessage(ChatColor.RED +"GAMEMODE OVER!!!");
        Bukkit.broadcastMessage(ChatColor.BLUE +"BACK TO LOBBY");
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("TnTRun");
    }

    public void setRounds(int x)
    {
        rounds=x;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) throws FileNotFoundException
    {
        if(TGTTOSAWAF)
        {
            if(e.getEntityType() == EntityType.CHICKEN)
            {
                if(e.getDamager().getType()==EntityType.PLAYER)
                {
                    Player p=(Player)e.getDamager();
                    p.setGameMode(GameMode.SPECTATOR);
                    Pos.add(p);
                    if(Pos.size()==ActiveRoundPlayers.size())
                    {
                        end();
                    }
                }
            }
            if (e.getEntityType() == EntityType.PLAYER)
            {
                e.setCancelled(true);
            }
        }
    }
}
