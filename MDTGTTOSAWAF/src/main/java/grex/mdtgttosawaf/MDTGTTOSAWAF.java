package grex.mdtgttosawaf;

import grex.mdtgttosawaf.SubComms.ChickenTabsCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MDTGTTOSAWAF extends JavaPlugin implements Listener
{

    @Override
    public void onEnable() {
        this.getCommand("chicken").setExecutor(new CommandManager(this));
        this.getCommand("chicken").setTabCompleter(new ChickenTabsCompleter());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void start()
    {

    }
}
