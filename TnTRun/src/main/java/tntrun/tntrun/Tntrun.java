package tntrun.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Tntrun extends JavaPlugin
{
    private List<List<Player>> positions = new ArrayList<>();
    private List<List<Player>> PlayerCount = new ArrayList<>();
    private int matches = 3;
    private int currentRound = 0;
    private RemBlocksScheduler match;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("tr").setExecutor(new CommandManager(this));
        this.getCommand("tr").setTabCompleter(new TNTTabsCompleter());
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    public void setMatches(int matches)
    {
        this.matches = matches;
    }
    public int getMatches()
    {
        return matches;
    }

    public void startgame()
    {
        //TODO: clone a world for this tnt run
        //TODO: teleport all valid players to arena
        //TODO: handle points
        List<Player> RoundPos = new ArrayList<>();
        List<Player> ActiveRoundPlayers;
        //TODO: not all players- ONLY active players
        ActiveRoundPlayers = (List<Player>) Bukkit.getOnlinePlayers();
        PlayerCount.add(ActiveRoundPlayers);
        positions.add(RoundPos);
        MatchInit(ActiveRoundPlayers);
    }

    public void stopgame()
    {
        match.scheduler.cancelTasks(this);
    }

    private void MatchInit(List<Player> ps)
    {
        int PauseInSec=5;
        Bukkit.broadcastMessage(ChatColor.RED + "NEXT ROUND IN "+ PauseInSec +" SECONDS");
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        match = new RemBlocksScheduler(this, ps, this);
        Bukkit.broadcastMessage(ChatColor.RED + "ROUND START");
    }

    public void Died(Player p)
    {
        positions.get(currentRound).add(p);
        if (positions.get(currentRound).size() == PlayerCount.get(currentRound).size())
        {
            stopgame();
            //TODO: isvesti rezultatus
            for (int i = 0; i < positions.get(currentRound).size(); i++)
            {
                Bukkit.getLogger().info(positions.get(currentRound).get(i).getName());
                Bukkit.getLogger().info(String.valueOf(currentRound));
            }
            ++currentRound;
            Bukkit.broadcastMessage(ChatColor.RED +"ROUND OVER");
            if(currentRound < matches)
            {
                //TODO: fix getting active players
                startgame();
            }
            else Bukkit.broadcastMessage(ChatColor.RED +"TNT RUN OVER!!!");
        }
    }
}