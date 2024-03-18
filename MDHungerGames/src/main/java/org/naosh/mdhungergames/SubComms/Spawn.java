package org.naosh.mdhungergames.SubComms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.naosh.mdhungergames.MDHungerGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spawn extends SubCommandMaster{
    public String GetName(){return "spawn";}
    public String GetDescpription(){return "generates loot chests";}
    public String GetSyntax(){return "/hungergames spawn";}
    public void Perform(Player player, String args[], MDHungerGames pl){
        ArrayList<Location> listLocations = new ArrayList<Location>();

        List<String> list = pl.getConfig().getStringList("Locations");
        for (int i = 0; i < list.size(); i++) {
            String[] arg = list.get(i).split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++) {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }
            Location loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
            listLocations.add(loc);
        }

        Collections.shuffle(listLocations);

        for(int i = 0; i <= 60; i++){
            Location chestLoc = new Location(Bukkit.getWorld("Hungergames"), listLocations.get(i).getX(), listLocations.get(i).getY(), listLocations.get(i).getZ());
            chestLoc.getBlock().setType(Material.CHEST);
        }
    }
}
