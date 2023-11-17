package decathlonmanager.decathlonmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class ScheduleNextGame
{
    BukkitScheduler scheduler;
    private final JavaPlugin plugin;

    public ScheduleNextGame(JavaPlugin plugin, DecathlonManager man, String what)
    {
        /*if(what=="uhc")
        {
            //TODO: if "what"=="uhc" then pregen the next world
            //TODO: next iteration of this plugin. aka next year lmao
            //generation args=normal
        }
        else if(what=="tnt")
        {
            //TODO: if "what"=="tnt", then clone tnt world
            //TODO: next iteration of this plugin. aka next year lmao
            //generation args=flat
        }
        else if(what=="build")
        {
            //TODO: if "what"=="build", then generate flat world
            //TODO: next iteration of this plugin. aka next year lmao
            //generation args=flat
        }*/
        this.plugin = plugin;
        for(int i=0;i<Bukkit.getOnlinePlayers().size();i++)
        {
            ((List<? extends Player>) Bukkit.getOnlinePlayers()).get(i).sendTitle(ChatColor.GREEN+"NEXT GAME IN 5 MINUTES","Next game is: "+what,5,200,5);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "NEXT GAME IN 5 MINUTES");
        scheduler=plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(what=="Chicken")
                {
                    /*for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                    {
                        for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " e:buildoff:"+String.valueOf(i*32+16)+",-59,16");
                        }
                    }*/
                    man.getChicken().start();
                } else if (what=="TnTRun")
                {
                    /*for(int i=0;i<man.getKomandos().getMasterTeam().GetKomandos().size();i++)
                    {
                        for(int j=0;j<man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size();j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " tntrun");
                        }
                    }*/
                    man.getTntrun().startgame();
                }
                else if(what=="UHC")
                {
                    for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                    {
                        for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " uhc");
                        }
                    }
                    man.getUhc().start();
                }
            }
        },200L); //TODO: set delay to 5*60*20=6000 || 6000ticks = 5 minutes
    }
}
