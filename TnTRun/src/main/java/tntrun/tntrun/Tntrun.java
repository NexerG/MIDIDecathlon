package tntrun.tntrun;

import decathlonmanager.decathlonmanager.DecathlonManager;
import mdteams.mdteams.MDTeams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Tntrun extends JavaPlugin implements Listener
{
    private boolean isPlaying=false;
    private List<List<Player>> PlayerCount = new ArrayList<>();
    List<Player> ActiveRoundPlayers=new ArrayList<>();
    private int matches = 3;
    private int currentRound = 0;
    private RemBlocksScheduler match;
    private MDTeams tm;

    public List<Player> Pos = new ArrayList<>();
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

        tm=(MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        ActiveRoundPlayers.clear();
        Pos.clear();
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
        MatchInit(ActiveRoundPlayers);
    }

    public void stopgame()
    {
        match.scheduler.cancelTasks(this);
    }

    private void MatchInit(List<Player> ps)
    {
        List<String> vietos= new ArrayList<>();
        vietos.add("-11,117,12");
        vietos.add("-11,117,71");
        vietos.add("-11,117,126");

        for(int i=0;i<tm.getMasterTeam().GetKomandos().size();i++)
        {
            for(int j=0;j<tm.getMasterTeam().GetKomandos().get(i).Players.size(); j++)
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "mvtp " + tm.getMasterTeam().GetKomandos().get(i).Players.get(j) + " e:tntrun"+String.valueOf(currentRound)+":"+vietos.get(j));
            }
        }

        /*for(int i=0;i<ActiveRoundPlayers.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + ActiveRoundPlayers.get(i).getName() + " e:tntrun"+String.valueOf(currentRound)+":"+vietos.get(i%3));
        }*/
        Bukkit.broadcastMessage(ChatColor.RED + "ROUND START");
        match = new RemBlocksScheduler(this, ps, this);
    }

    public void Died(Player p) throws FileNotFoundException
    {
        Pos.add(p);
        if (Pos.size() == ActiveRoundPlayers.size())
        {
            //Bukkit.getLogger().info(String.valueOf(Pos.size()));
            //Bukkit.getLogger().info(String.valueOf(ActiveRoundPlayers.size()));
            stopgame();
            //isvedam rez
            PrintWriter out= new PrintWriter("rez_TNT_"+String.valueOf(currentRound)+".txt");
            for (int i = Pos.size()-1; i >= 0; i--)
            {
                out.println(Pos.get(i).getName());
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
                for(int i=0;i<Bukkit.getOnlinePlayers().size();i++)
                {
                    ((List<? extends Player>) Bukkit.getOnlinePlayers()).get(i).sendTitle(ChatColor.GREEN+"TNT run OVER!","Back to Lobby",5,200,5);
                }
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
        man.Next("UHC");
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