package org.naosh.mdparkourrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.naosh.decathlonmanager.DecathlonManager;
import org.naosh.mdteams.Komanda;
import org.naosh.mdteams.MDTeams;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MDParkourRace extends JavaPlugin implements Listener {
    private boolean isPlaying=false;
    List<Player> ActiveRoundPlayers=new ArrayList<>();
    private MDTeams tm;
    ArrayList<Location> checkpoints = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -738, 4, -768));
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -800, 4, -768));
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -866, 7, -768));
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -929, 11, -768));
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -994, 13, -770));
        checkpoints.add(new Location(Bukkit.getWorld("swagkour"), -1060, 13, -770));
        getServer().getPluginManager().registerEvents(this,this);
    }

    public void startgame()
    {
        isPlaying=true;

        tm=(MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
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
        MatchInit(ActiveRoundPlayers);
    }
    private void MatchInit(List<Player> ps)
    {

        for(int i=0;i<tm.getMasterTeam().GetKomandos().size();i++)
        {
            for(int j=0;j<tm.getMasterTeam().GetKomandos().get(i).Players.size(); j++)
            {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender()
                        , "mvtp " + tm.getMasterTeam().GetKomandos().get(i).Players.get(j) + " e:tntrun:-734,4,-768");

            }
        }

        /*for(int i=0;i<ActiveRoundPlayers.size();i++)
        {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender()
                    , "mvtp " + ActiveRoundPlayers.get(i).getName() + " e:tntrun"+String.valueOf(currentRound)+":"+vietos.get(i%3));
        }*/
        Bukkit.broadcastMessage(ChatColor.RED + "ROUND START");
        //match = new RemBlocksScheduler(this, ps, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void Over()
    {
        //TODO: teleport everyone to the lobby
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamemode adventure @a");
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("HG");
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
    public void onPlayerJoin(PlayerJoinEvent ev){
        Player player = ev.getPlayer();
        player.teleport(checkpoints.get(0));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev){
        if(isPlaying) {
            if (ev.isCancelled() || ev.getFrom().getBlock().getLocation() == ev.getFrom().getBlock().getLocation()) // pagal ideja turetu lag sumazint
                return;

            else {
                Player player = ev.getPlayer();
                tm = (MDTeams) Bukkit.getServer().getPluginManager().getPlugin("MDTeams");
                Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                if (block.getType() == Material.DEEPSLATE) {
                    int closest = 0;
                    for (int i = 0; i < checkpoints.size(); i++) {
                        if (player.getLocation().distance(checkpoints.get(i)) < player.getLocation().distance(checkpoints.get(closest))) {
                            closest = i;
                        }
                    }
                    for (int i = 0; i < tm.getMasterTeam().GetKomandos().size(); i++) {
                        if (tm.getMasterTeam().GetKomandos().get(i).IsInTeam(player)) {
                            tm.getMasterTeam().GetKomandos().get(i).getParkourCheckpoints().putIfAbsent(player.getName(), closest);
                            tm.getMasterTeam().GetKomandos().get(i).getParkourCheckpoints().replace(player.getName(), closest);
                        }
                    }
                }
            }
        }
    }
}
