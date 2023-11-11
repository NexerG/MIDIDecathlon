package decathlonmanager.decathlonmanager.SubComms;

import decathlonmanager.decathlonmanager.DecathlonManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GenTNT extends SubCommandMaster
{
    @Override
    public String GetName()
    {
        return "gentntworld";
    }

    @Override
    public String GetDescpription()
    {
        return "clones the tnt worlds";
    }

    @Override
    public String GetSyntax()
    {
        return "/manager gentntworld";
    }

    @Override
    public void Perform(Player player, String[] args, DecathlonManager pl)
    {
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvdelete tntrun1");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvconfirm");
        //--------//
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvdelete tntrun2");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvconfirm");
        //--------//
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvclone tntrun tntrun0");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvclone tntrun tntrun1");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getServer().getConsoleSender()
                , "mvclone tntrun tntrun2");
    }
}
