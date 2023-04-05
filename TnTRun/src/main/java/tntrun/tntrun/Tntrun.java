package tntrun.tntrun;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Tntrun extends JavaPlugin implements Listener
{
    private boolean isPlaying=false;
    private List<List<Player>> positions = new ArrayList<>();
    private List<List<Player>> PlayerCount = new ArrayList<>();
    List<Player> ActiveRoundPlayers=new ArrayList<>();
    private int matches = 3;
    private int currentRound = 0;
    private RemBlocksScheduler match;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("tr").setExecutor(new CommandManager(this));
        this.getCommand("tr").setTabCompleter(new TNTTabsCompleter());
        getServer().getPluginManager().registerEvents(this,this);
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
        isPlaying=true;
        //TODO: clone a world for this tnt run
        //TODO: teleport all valid players to arena
        //TODO: handle points
        List<Player> RoundPos = new ArrayList<>();
        //TODO: not all players- ONLY active players
        ActiveRoundPlayers = ImmutableList.copyOf(Bukkit.getOnlinePlayers());
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

    public void Died(Player p) throws FileNotFoundException
    {
        positions.get(currentRound).add(p);
        if (positions.get(currentRound).size() == PlayerCount.get(currentRound).size())
        {
            stopgame();
            //isvedam rez
            PrintWriter out= new PrintWriter("rez_TNT_"+currentRound+".txt");
            for (int i = positions.get(currentRound).size()-1; i >= 0; i--)
            {
                out.println(positions.get(currentRound).get(i).getName());
                //Bukkit.getLogger().info(positions.get(currentRound).get(i).getName());
                //Bukkit.getLogger().info(String.valueOf(currentRound));
            }
            out.close();
            ++currentRound;
            Bukkit.broadcastMessage(ChatColor.RED +"ROUND OVER");
            if(currentRound < matches)
            {
                //TODO: fix getting active players
                startgame();
            }
            else
            {
                isPlaying=false;
                Bukkit.broadcastMessage(ChatColor.RED +"TNT RUN OVER!!!");
                Bukkit.broadcastMessage(ChatColor.BLUE +"BACK TO LOBBY IN 10 SECONDS!!!");
                try{
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                Over();
            }
        }
    }
    public void Over()
    {
        //TODO: teleport everyone to the lobby
        //TODO: suskaiciuoti rezultatus ir isvesti juos in Lobby
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp to lobby");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        if(isPlaying)
        {
            ActiveRoundPlayers.remove(e.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(isPlaying)
        {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure " + e.getPlayer().getName());
        }
        else
        {
            //TODO: teleport player to lobby. DO THIS IN MAIN MENU PLUGIN
        }
    }
}