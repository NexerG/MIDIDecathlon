package decathlonmanager.decathlonmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

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
        Bukkit.broadcastMessage(ChatColor.GREEN+"NEXT GAME IN 5 MINUTES");
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(what=="build")
                {
                    for (int i = 0; i < man.getKomandos().getMasterTeam().GetKomandos().size(); i++)
                    {
                        for (int j = 0; j < man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size(); j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " buildoff "+String.valueOf(i*32+16)+" 10"+" 16");
                        }
                    }
                    man.getBuildOff().start();
                } else if (what=="tnt")
                {
                    for(int i=0;i<man.getKomandos().getMasterTeam().GetKomandos().size();i++)
                    {
                        for(int j=0;j<man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.size();j++)
                        {
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getServer().getConsoleSender()
                                    , "mvtp " + man.getKomandos().getMasterTeam().GetKomandos().get(i).Players.get(j) + " tntrun"/*+coordinates*/);
                        }
                    }
                    man.getTntrun().startgame();
                }
                else if(what=="uhc")
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
        },6000L);
    }
}
