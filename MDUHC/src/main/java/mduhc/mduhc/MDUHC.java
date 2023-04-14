package mduhc.mduhc;

import decathlonmanager.decathlonmanager.DecathlonManager;
import mdteams.mdteams.Komanda;
import mdteams.mdteams.MDTeams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class MDUHC extends JavaPlugin implements Listener
{

    private boolean isUHC = false;
    private boolean LeftTeams=true;
    List<Komanda> komandos = new ArrayList<>();
    List<Player> alivePs = new ArrayList<>();
    List<Player> DeathSeq=new ArrayList<>();
    private SchedulerBorderPVP match;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("uhc").setExecutor(new CommandManager(this));
        this.getCommand("uhc").setTabCompleter(new UHCTabsCompleter());
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    public boolean getisUHC()
    {
        return isUHC;
    }

    public void start()
    {
        isUHC = true;
        MDTeams BaseTeam = (MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        komandos = BaseTeam.getMasterTeam().GetKomandos();
        for (int i = 0; i < komandos.size(); i++)
        {
            for (int j = 0; j < komandos.get(i).Players.size(); j++)
            {
                //TODO: TP everyone to the UHC world (DO IT IN THE MANAGER PLUGIN)
                if (Bukkit.getPlayer(komandos.get(i).Players.get(j)).isOnline())
                {
                    alivePs.add(Bukkit.getPlayer(komandos.get(i).Players.get(j)));
                    //survival
                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getServer().getConsoleSender()
                            , "gamemode survival " + komandos.get(i).Players.get(j));
                }
            }
        }
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender(),"worldborder set 500");
        //TODO:set delay for border shrink and pvp
        match=new SchedulerBorderPVP(this,this);
        Bukkit.broadcastMessage(ChatColor.RED + "PVP on in 5 minutes. Border Shrink in 5 minutes");
    }

    public void DeathMatch()
    {
        for(int i=0;i<alivePs.size();i++)
        {
            //TODO: tp alivers to the arena
        }
    }

    public void generate(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            //TODO: generate worlds for UHC
        }
    }

    private void RoundEnd()
    {
        //TODO: add multiple round support
        GameEnd();
        isUHC=false;
        DecathlonManager man= (DecathlonManager) getServer().getPluginManager().getPlugin("");
        man.Next("DecathlonManager");
        //TODO:stop pvp
    }

    private void GameEnd()
    {
        try
        {
            PrintWriter out= new PrintWriter("UHCrez.txt");
            for(int i=0;i<DeathSeq.size();i++)
            {
                out.println(DeathSeq.get(i).getName());
            }
            out.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        if (isUHC)
        {
            DeathSeq.add(e.getEntity().getPlayer());
            alivePs.remove(e.getEntity().getPlayer());
            for (int i = 0; i < komandos.size(); i++)
            {
                for (int j = 0; j < komandos.get(i).Players.size(); j++)
                {
                    if (e.getEntity().getPlayer() == Bukkit.getPlayer(komandos.get(i).Players.get(j)))
                    {
                        //TODO: output player death into txt
                        for (int k = 0; k < komandos.size(); k++)
                        {
                            for (int h = 0; h < komandos.get(k).Players.size(); h++)
                            {
                                if (Bukkit.getPlayer(komandos.get(k).Players.get(h)) == e.getEntity().getPlayer().getKiller())
                                {
                                    komandos.get(k).AddKill();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if (alivePs.size() < 4)
            {
                LeftTeams=false;
                Komanda dummy = null;
                for (int i = 0; i < komandos.size(); i++)
                {
                    for (int j = 0; j < komandos.get(i).Players.size(); j++)
                    {
                        if (alivePs.get(0) == Bukkit.getPlayer(komandos.get(i).Players.get(j)))
                        {
                            dummy = komandos.get(i);
                            break;
                        }
                    }
                }
                for(int i=1;i<alivePs.size();i++)
                {
                    for(int k=0;k<komandos.size();k++)
                    {
                        for(int j=0;j<komandos.get(k).Players.size();j++)
                        {
                            if(alivePs.get(i)==Bukkit.getPlayer(komandos.get(k).Players.get(j)))
                            {
                                if(komandos.get(k)!=dummy)
                                {
                                    LeftTeams=true;
                                }
                            }
                        }
                    }
                }
                if(!LeftTeams)
                {
                    RoundEnd();
                }
            }
        }
    }
}
