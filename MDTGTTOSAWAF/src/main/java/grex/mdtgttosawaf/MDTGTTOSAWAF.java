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
    Boolean TGTTOSAWAF = false;
    int rounds=1;
    List<Player> ActiveRoundPlayers = new ArrayList<>();
    public List<Player> Pos = new ArrayList<>();
    private RoundTimer match;

    @Override
    public void onEnable() {
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
        match= new RoundTimer(this,this);
    }
    public void end() throws FileNotFoundException
    {
        PrintWriter out= new PrintWriter("rez_Chicken"+String.valueOf(rounds)+".txt");
        for (int i=Pos.size()-1;i>=0;i--)
        {
            out.println(Pos.get(i).getName());
        }
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
    }
    public void Over()
    {
        Bukkit.broadcastMessage(ChatColor.RED +"GAMEMODE OVER!!!");
        Bukkit.broadcastMessage(ChatColor.BLUE +"BACK TO LOBBY");
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("TnTRun");
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
                //give points
                //when all players gone start a new round or stop
            }
        }
    }
}
