package org.naosh.mdbedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Kit
{
    private Player p;
    private Material Kardas=Material.STONE_SWORD;
    private Material Salmas;
    private Material Chest;
    private Material Legs;
    private Material Feet;
    private List<Material> Kitas=new ArrayList<>();

    public Kit(Player ow)
    {
        p = ow;
        p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
    }

    public void UpgradeKardas()
    {
        if(Kardas==Material.STONE_SWORD)
        {
            Kardas=Material.IRON_SWORD;
        }
        p.getInventory().clear(p.getInventory().first(Material.STONE_SWORD));
    }
}
