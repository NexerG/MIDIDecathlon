package tntrun.tntrun;

import com.google.common.collect.ImmutableList;
import decathlonmanager.decathlonmanager.DecathlonManager;
import mdteams.mdteams.MDTeams;
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

    private List<Player> RoundPos1 = new ArrayList<>();
    private List<Player> RoundPos2 = new ArrayList<>();
    private List<Player> RoundPos3 = new ArrayList<>();
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("tr").setExecutor(new CommandManager(this));
        this.getCommand("tr").setTabCompleter(new TNTTabsCompleter());
        getServer().getPluginManager().registerEvents(this,this);
        positions.add(RoundPos1);
        positions.add(RoundPos2);
        positions.add(RoundPos3);
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

        //TODO: not all players- ONLY active players
        MDTeams tm=(MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        ActiveRoundPlayers.clear();
        for(int i=0;i<tm.getMasterTeam().GetKomandos().size();i++)
        {
            for(int j=0;j<tm.getMasterTeam().GetKomandos().get(i).Players.size();j++)
            {
                if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(tm.getMasterTeam().GetKomandos().get(i).Players.get(j))))
                {
                    ActiveRoundPlayers.add(Bukkit.getPlayer(tm.getMasterTeam().GetKomandos().get(i).Players.get(j)));
                }
            }
        }
        PlayerCount.add(ActiveRoundPlayers);
        MatchInit(ActiveRoundPlayers);
    }

    public void stopgame()
    {
        match.scheduler.cancelTasks(this);
    }

    private void MatchInit(List<Player> ps)
    {
        List<String> vietos= new ArrayList<>();
        vietos.add("-2,42,3");
        vietos.add("-5,42,3");
        vietos.add("-8,42,3");
        vietos.add("-11,42,3");

        vietos.add("-14,42,3");
        vietos.add("-17,42,3");
        vietos.add("-20,42,3");
        vietos.add("-22,42,3");
        for(int i=0;i<ActiveRoundPlayers.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + ActiveRoundPlayers.get(i).getName() + " e:tntrun"+String.valueOf(currentRound)+":"+vietos.get(i%8));
        }
        Bukkit.broadcastMessage(ChatColor.RED + "ROUND START");
        match = new RemBlocksScheduler(this, ps, this);
    }

    public void Died(Player p) throws FileNotFoundException
    {
        positions.get(currentRound).add(p);
        if (positions.get(currentRound).size() == PlayerCount.get(currentRound).size())
        {
            stopgame();
            //isvedam rez
            PrintWriter out= new PrintWriter("rez_TNT_"+String.valueOf(currentRound)+".txt");
            for (int i = positions.get(currentRound).size()-1; i >= 0; i--)
            {
                out.println(positions.get(currentRound).get(i).getName());
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
                Bukkit.broadcastMessage(ChatColor.BLUE +"BACK TO LOBBY");
                Over();
            }
        }
    }
    public void Over()
    {
        //TODO: teleport everyone to the lobby
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("uhc");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        if(isPlaying)
        {
            ActiveRoundPlayers.remove(e.getPlayer());
        }
    }
}