package org.naosh.mdbedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LootSpawner
{
    private int level=0;
    private List<Material> ItemsSpawner=new ArrayList<>();
    private Location Loc;
    public LootSpawner(String ow,Location loc)
    {
        Bukkit.getLogger().info("LootSpawner init");
        ItemsSpawner.add(Material.IRON_INGOT);
        ItemsSpawner.add(Material.GOLD_INGOT);
        ItemsSpawner.add(Material.DIAMOND);
        Loc=loc;
    }

    public void LevelUpDrop()
    {
        level = level + 1;
    }
    public void disable() throws Throwable
    {
        finalize();
    }
    public Location getLocation()
    {
        return Loc;
    }
}
