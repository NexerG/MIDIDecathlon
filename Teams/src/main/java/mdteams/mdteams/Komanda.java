package mdteams.mdteams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Komanda
{
    public String TName;
    public String TCreator;
    public List<String> Players=new ArrayList<>();
    public List<String> Invites=new ArrayList<>();

    public Komanda(String Pavadinimas, String Creator)
    {
        this.TName=Pavadinimas;
        this.TCreator=Creator;
        this.Players.add(Creator);
    }
}
