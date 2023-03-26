package tntrun.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Tntrun extends JavaPlugin
{
    private List<List<Player>> positions=new ArrayList<>();
    private List<List<Player>> PlayerCount=new ArrayList<>();
    private int matches=1;
    private int currentRound=0;
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
        ++currentRound;
        //TODO: clone a world for this tnt run
        //TODO: teleport all valid players to arena
        //TODO: handle points
        List<Player> RoundPos = new ArrayList<>();

        List<Player> ActiveRoundPlayers=new ArrayList<>();
        //TODO: not all players- ONLY active players
        ActiveRoundPlayers= (List<Player>) Bukkit.getOnlinePlayers();
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
        match = new RemBlocksScheduler(this,ps);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        positions.get(currentRound).add(e.getEntity().getPlayer());
        match.playerDied(e.getEntity().getPlayer());
        if(positions.get(currentRound).size()==PlayerCount.get(currentRound).size())
        {
            stopgame();
            //TODO: isvesti rezultatus
        }
    }
}
