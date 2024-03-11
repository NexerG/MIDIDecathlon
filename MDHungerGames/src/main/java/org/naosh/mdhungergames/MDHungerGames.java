package org.naosh.mdhungergames;

import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.loot.Lootable;
import org.bukkit.plugin.java.JavaPlugin;
import org.naosh.decathlonmanager.DecathlonManager;
import org.naosh.mdteams.Komanda;
import org.naosh.mdteams.MDTeams;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public final class MDHungerGames extends JavaPlugin implements Listener {
    private List<Location> ChestLocs = new ArrayList<>();
    private boolean isHG = false;
    private boolean LeftTeams = true;
    List<Komanda> komandos = new ArrayList<>();
    List<Player> alivePs = new ArrayList<>();
    List<Player> DeathSeq = new ArrayList<>();

    @Override
    public void onEnable() {
        this.getCommand("hungergames").setExecutor(new CommandManager(this));
        this.getCommand("hungergames").setTabCompleter(new HGTabsCompleter());
        getServer().getPluginManager().registerEvents(this, this);

    }
    public boolean getisHG()
    {
        return isHG;
    }

    public void start()
    {
        isHG = true;
        MDTeams BaseTeam = (MDTeams) getServer().getPluginManager().getPlugin("MDTeams");
        komandos = BaseTeam.getMasterTeam().GetKomandos();
        for (int i = 0; i < komandos.size(); i++)
        {
            for (int j = 0; j < komandos.get(i).Players.size(); j++)
            {
                //TODO: TP everyone to the HG world (DO IT IN THE MANAGER PLUGIN)
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
    }
    private void GameEnd()
    {
        try
        {
            PrintWriter out = new PrintWriter("HGrez.txt");
            for (int i = DeathSeq.size() - 1; i >= 0; i--)
            {
                out.println(DeathSeq.get(i).getName());
            }
            out.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        if (isHG)
        {
            if (alivePs.contains(e.getEntity().getPlayer()))
            {
                DeathSeq.add(e.getEntity().getPlayer());
                alivePs.remove(e.getEntity().getPlayer());
                for (int k = 0; k < komandos.size(); k++)
                {
                    for (int h = 0; h < komandos.get(k).Players.size(); h++)
                    {
                        if (Bukkit.getPlayer(komandos.get(k).Players.get(h).toString()) == e.getEntity().getPlayer().getKiller())
                        {
                            komandos.get(k).AddKill();
                            break;
                        }
                    }
                }
            }
            if (alivePs.size() < 4)
            {
                LeftTeams = false;
                Komanda dummy = null;
                for (int i = 0; i < komandos.size(); i++)
                {
                    for (int j = 0; j < komandos.get(i).Players.size(); j++)
                    {
                        if (alivePs.get(0) == Bukkit.getPlayer(komandos.get(i).Players.get(j).toString()) && dummy == null)
                        {
                            dummy = komandos.get(i);
                            break;
                        }
                    }
                    if (dummy != null)
                        break;
                }
                for (int i = 1; i < alivePs.size(); i++)
                {
                    for (int k = 0; k < komandos.size(); k++)
                    {
                        for (int j = 0; j < komandos.get(k).Players.size(); j++)
                        {
                            if (alivePs.get(i) == Bukkit.getPlayer(komandos.get(k).Players.get(j).toString()))
                            {
                                if (komandos.get(k) != dummy)
                                {
                                    LeftTeams = true;
                                }
                            }
                        }
                    }
                }
                if (!LeftTeams)
                {
                    RoundEnd();
                }
            }
        }
    }

    @EventHandler
    public void NoBuild(BlockPlaceEvent e)
    {
        if (isHG)
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Cannot build in The Hunger Games");
        }
    }

    @EventHandler
    public void NoBreak(BlockBreakEvent e)
    {
        if (isHG)
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Cannot mine in The Hunger Games");
        }
    }
    private void RoundEnd()
    {
        //TODO: add multiple round support
        GameEnd();
        isHG = false;
        DecathlonManager man = (DecathlonManager) getServer().getPluginManager().getPlugin("DecathlonManager");
        man.Next("");
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!isHG) {
            if (!ChestLocs.contains(e.getClickedBlock().getLocation()))
                if (e.hasBlock())
                    if (e.getClickedBlock().getType() == Material.CHEST) {
                        Chest chest = (Chest) e.getClickedBlock().getState();
                        if (chest instanceof Lootable) {
                            chest.setLootTable(Bukkit.getLootTable(new NamespacedKey(this, "hgcStart")));
                            chest.update();
                            ChestLocs.add(chest.getLocation());
                        }
                    }
        }
    }
}
