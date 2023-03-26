package tntrun.tntrun.SubComms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import tntrun.tntrun.Tntrun;

public class BlockRemTest extends SubCommandMaster
{

    @Override
    public String GetName()
    {
        return "test";
    }

    @Override
    public String GetDescpription()
    {
        return "removes one block under player";
    }

    @Override
    public String GetSyntax()
    {
        return "/tr test";
    }

    @Override
    public void Perform(Player player, String[] args, Tntrun pr)
    {
        //test logic
        /*Location blockas= player.getLocation().add(0,-1,0);
        blockas.getBlock().setType(Material.AIR);*/
    }
}
